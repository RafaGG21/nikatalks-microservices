package com.nikatalks.controller;

import com.nikatalks.commons.dto.PublicationDto;
import com.nikatalks.service.PublicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    private final PublicationService service;

    public PublicationController(PublicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PublicationDto> create(@RequestBody PublicationDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDto> update(@PathVariable Long id, @RequestBody PublicationDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PublicationDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<PublicationDto>> findByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(service.findByLanguage(language));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<PublicationDto>> findByLevel(@PathVariable String level) {
        return ResponseEntity.ok(service.findByLevel(level));
    }

    @GetMapping("/competency/{competency}")
    public ResponseEntity<List<PublicationDto>> findByCompetency(@PathVariable String competency) {
        return ResponseEntity.ok(service.findByCompetency(competency));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PublicationDto>> findByLanguageLevelAndCompetency(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String competency
    ) {
        return ResponseEntity.ok(service.findByLanguageDestinationLevelOrCompetency(language, destination, competency, level));
    }

}