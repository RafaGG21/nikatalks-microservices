package com.nikatalks.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikatalks.commons.dto.PaymentIntentDto;
import com.nikatalks.commons.dto.StripeSubscriptionDto;
import com.nikatalks.service.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private StripePaymentService stripeService;
    
    
    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody PaymentIntentDto paymentIntentDto) throws StripeException {
    	System.out.println(paymentIntentDto);
        PaymentIntent paymentIntent = stripeService.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = stripeService.confirm(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = stripeService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
 
    @PostMapping("/create-subscription")
    public Map<String, Object> createSubscription(@RequestBody Map<String, String> request) throws StripeException {
        String email = request.get("email");
        String paymentMethodId = request.get("paymentMethodId");
        String priceId = request.get("priceId");

        return stripeService.createSubscription(email, paymentMethodId, priceId);
    }
    
    @PostMapping("/cancel-subscription")
    public ResponseEntity<StripeSubscriptionDto> cancelSubscription(@RequestParam String email) throws Exception {
        StripeSubscriptionDto dto = stripeService.cancelSubscription(email);
        return ResponseEntity.ok(dto);
    }
}