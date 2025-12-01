package com.nikatalks.service;

import com.nikatalks.repository.PublicationRepository;
import com.nikatalks.commons.dto.PublicationDto;
import com.nikatalks.commons.entity.Publication;
import com.nikatalks.commons.mapper.GenericMapper;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationService {

    private final PublicationRepository repository;

    public PublicationService(PublicationRepository repository) {
        this.repository = repository;
    }

    public PublicationDto create(PublicationDto dto) {
        Publication entity = GenericMapper.map(dto, Publication.class);
        return GenericMapper.map(repository.save(entity), PublicationDto.class);
    }

    public PublicationDto update(Long id, PublicationDto dto) {
        Optional<Publication> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Publication not found");
        }
        Publication entity = optional.get();
        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());
        entity.setLanguage(dto.getLanguage());
        entity.setLevel(dto.getLevel());
        entity.setCompetency(dto.getCompetency());
        return GenericMapper.map(repository.save(entity), PublicationDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public PublicationDto findById(Long id) {
        return repository.findById(id)
                .map(entity -> GenericMapper.map(entity, PublicationDto.class))
                .orElse(null);
    }

    public List<PublicationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> GenericMapper.map(entity, PublicationDto.class))
                .collect(Collectors.toList());
    }

    public List<PublicationDto> findByLanguage(String language) {
        return repository.findByLanguage(language)
                .stream()
                .map(entity -> GenericMapper.map(entity, PublicationDto.class))
                .collect(Collectors.toList());
    }

    public List<PublicationDto> findByLevel(String level) {
        return repository.findByLevel(level)
                .stream()
                .map(entity -> GenericMapper.map(entity, PublicationDto.class))
                .collect(Collectors.toList());
    }

    public List<PublicationDto> findByCompetency(String competency) {
        return repository.findByCompetency(competency)
                .stream()
                .map(entity -> GenericMapper.map(entity, PublicationDto.class))
                .collect(Collectors.toList());
    }
    
    public List<PublicationDto> findByLanguageDestinationLevelOrCompetency(String language, String destination ,
    		String level, String competency) {
        return repository.findByLanguageDestinationLevelOrCompetency(language, destination,competency, level)
                .stream()
                .map(entity -> GenericMapper.map(entity, PublicationDto.class))
                .collect(Collectors.toList());
    }
}
