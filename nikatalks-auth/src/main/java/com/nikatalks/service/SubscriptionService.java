package com.nikatalks.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nikatalks.commons.dto.SubscriptionDto;
import com.nikatalks.commons.entity.Subscription;
import com.nikatalks.commons.mapper.GenericMapper;
import com.nikatalks.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public SubscriptionDto create(SubscriptionDto dto) {
        Subscription entity = GenericMapper.map(dto, Subscription.class);
        Subscription saved = repository.save(entity);
        return GenericMapper.map(saved, SubscriptionDto.class);
    }

    public SubscriptionDto update(Long id, SubscriptionDto dto) {
        Subscription existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        Subscription saved = repository.save(existing);
        return GenericMapper.map(saved, SubscriptionDto.class);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Subscription not found");
        }
        repository.deleteById(id);
    }

    public SubscriptionDto findById(Long id) {
        Subscription entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        return GenericMapper.map(entity, SubscriptionDto.class);
    }

    public List<SubscriptionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(sub -> GenericMapper.map(sub, SubscriptionDto.class))
                .collect(Collectors.toList());
    }
}
