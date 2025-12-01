package com.nikatalks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nikatalks.commons.dto.PaymentIntentDto;
import com.nikatalks.commons.dto.StudentDto;
import com.nikatalks.commons.entity.Student;
import com.nikatalks.commons.dto.StripeSubscriptionDto;
import com.nikatalks.controller.StudentsFeignClient;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Subscription;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.SubscriptionCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class StripePaymentService {
    
    @Value("${stripe.key.secret}")
    private String secretKey;
    
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
    @Autowired
    private StudentsFeignClient studentsFeignClient;
    
    public PaymentIntent createPaymentIntent(Long amount, String currency, Map<String, Object> metadata) throws StripeException {
        // Convertir Map<String, Object> a Map<String, String>
        Map<String, String> stringMetadata = new HashMap<>();
        if (metadata != null) {
            for (Map.Entry<String, Object> entry : metadata.entrySet()) {
                stringMetadata.put(entry.getKey(), (String) entry.getValue());
            }
        }
        
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency(currency)
            .putAllMetadata(stringMetadata)
            .setAutomaticPaymentMethods(
                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                    .setEnabled(true)
                    .build()
            )
            .build();
            
        return PaymentIntent.create(params);
    }
    
    // Método alternativo con Map<String, String> directamente
    public PaymentIntent createPaymentIntentWithStringMetadata(Long amount, String currency, Map<String, String> metadata) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency(currency)
            .putAllMetadata(metadata != null ? metadata : new HashMap<>())
            .setAutomaticPaymentMethods(
                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                    .setEnabled(true)
                    .build()
            )
            .build();
            
        return PaymentIntent.create(params);
    }
    
    public PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException {
    	Stripe.apiKey = secretKey;
        return PaymentIntent.retrieve(paymentIntentId);
    }
    
    public PaymentIntent paymentIntent(PaymentIntentDto paymentIntentDto) throws StripeException {
        Stripe.apiKey = secretKey;
        final List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntentDto.getAmount());
        params.put("currency", "eur");
        params.put("description", paymentIntentDto.getDescription());
        params.put("payment_method_types", paymentMethodTypes);
        return PaymentIntent.create(params);
    }

    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);
        return paymentIntent;
    }

    public PaymentIntent cancel(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }
    
    public Map<String, Object> createSubscription(String email, String paymentMethodId, String priceId) throws StripeException {
        Stripe.apiKey = secretKey;

        // 1️⃣ Crear el cliente
        CustomerCreateParams customerParams = CustomerCreateParams.builder()
                .setEmail(email)
                .setPaymentMethod(paymentMethodId)
                .setInvoiceSettings(
                        CustomerCreateParams.InvoiceSettings.builder()
                                .setDefaultPaymentMethod(paymentMethodId)
                                .build())
                .build();

        Customer customer = Customer.create(customerParams);

        // 2️⃣ Crear la suscripción
        SubscriptionCreateParams subParams = SubscriptionCreateParams.builder()
                .setCustomer(customer.getId())
                .addItem(
                        SubscriptionCreateParams.Item.builder()
                                .setPrice(priceId) // price_XXXX de tu producto en Stripe
                                .build())
                .setPaymentBehavior(SubscriptionCreateParams.PaymentBehavior.DEFAULT_INCOMPLETE)
                .addAllExpand(List.of("latest_invoice.payment_intent"))
                .build();

        Subscription subscription = Subscription.create(subParams);

        // 3️⃣ Devolver info al frontend
        Map<String, Object> response = new HashMap<>();
        response.put("subscriptionId", subscription.getId());
        response.put("clientSecret", subscription.getLatestInvoiceObject().getPaymentIntentObject().getClientSecret());

        return response;
    }
    
    public StripeSubscriptionDto cancelSubscription(String emailUser) throws Exception {
    	StudentDto student = studentsFeignClient.findByEmail(emailUser);

        // Recuperamos la suscripción de Stripe
        Subscription subscription = Subscription.retrieve(student.getIdSubscriptionStripe());
        // Cancelamos la suscripción inmediatamente
        Subscription canceledSubscription = subscription.cancel();

        return new StripeSubscriptionDto(
                canceledSubscription.getId(),
                canceledSubscription.getStatus()
            );
    }
}
