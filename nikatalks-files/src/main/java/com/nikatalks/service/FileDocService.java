package com.nikatalks.service;

import com.nikatalks.commons.entity.FileDoc;
import com.nikatalks.commons.mapper.GenericMapper;
import com.nikatalks.repository.FileDocRepository;
import com.nikatalks.commons.dto.FileDocDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileDocService {

    private final S3Client s3Client;
    private final FileDocRepository fileDocRepository;

    
    @Value("${digitalocean.spaces.bucket-name}")
    private String bucketName;

    public FileDocService(S3Client s3Client, FileDocRepository fileDocRepository) {
        this.s3Client = s3Client;
        this.fileDocRepository = fileDocRepository;
    }
    
    public List<FileDocDto> getByLanguage(String language) {
        return fileDocRepository.findByLanguage(language)
        		.stream().map(file -> GenericMapper.map(file, FileDocDto.class))
        		.collect(Collectors.toList());
    }

    public List<FileDocDto> getByCompetence(String competency) {
        return fileDocRepository.findBycompetence(competency)
        		.stream().map(file -> GenericMapper.map(file, FileDocDto.class))
        		.collect(Collectors.toList());
    }

    public List<FileDocDto> findByLanguageDestinationLevelOrCompetence(final String language, final String destination, 
    		final String competency, final String level) {
        return fileDocRepository.findByLanguageDestinationLevelOrCompetence(language,destination, competency, level)
        		.stream().map(file -> GenericMapper.map(file, FileDocDto.class))
        		.collect(Collectors.toList());
    }
    
    public List<FileDocDto> findByLanguageAndCompetence(final String language, final String competence ) {
        return fileDocRepository.findByLanguageAndCompetence(language, competence)
        		.stream().map(file -> GenericMapper.map(file, FileDocDto.class))
        		.collect(Collectors.toList());
    }
    
    public List<FileDocDto> findByLanguageCompetenceAndDestination(final String language, final String competence, 
    		final String destination ) {
        return fileDocRepository.findByLanguageCompetenceAndDestination(language, competence, destination)
        		.stream().map(file -> GenericMapper.map(file, FileDocDto.class))
        		.collect(Collectors.toList());
    }



    public FileDocDto uploadFile(MultipartFile file, String language, String competency) throws IOException {
        String key = generateUniqueFileName(file.getOriginalFilename());

        // Subir archivo a DigitalOcean Spaces
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(putRequest, RequestBody.fromBytes(file.getBytes()));

        // Guardar info en BD
        FileDoc fileDoc = new FileDoc();
        fileDoc.setFileName(file.getOriginalFilename());
        fileDoc.setFile_key(key);
        fileDoc.setContentType(file.getContentType());
        fileDoc.setSize(file.getSize());
        fileDoc.setLanguage(language);
        fileDoc.setCompetence(competency);
        FileDoc saved = fileDocRepository.save(fileDoc);

        return GenericMapper.map(saved, FileDocDto.class);
    }

    public FileDocDto deleteFile(Long id) {
        // Buscar el archivo en BD
        FileDoc fileDoc = fileDocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));

        // Eliminar de DigitalOcean Spaces
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileDoc.getFile_key())
                .build();

        s3Client.deleteObject(deleteRequest);

        // Eliminar de BD
        fileDocRepository.delete(fileDoc);
        return GenericMapper.map(fileDoc, FileDocDto.class);
    }
    
    public byte[] downloadFile(Long id) {
        FileDoc fileDoc = fileDocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileDoc.getFile_key())
                .build();

        return s3Client.getObjectAsBytes(getRequest).asByteArray();
    }

    public List<FileDocDto> listFiles() {
        return fileDocRepository.findAll().stream()
                .map(file -> GenericMapper.map(file, FileDocDto.class))
                .collect(Collectors.toList());
    }

    private String generateUniqueFileName(String originalName) {
        return Instant.now().toEpochMilli() + "-" + originalName.replace(" ", "_");
    }


    public String uploadImage(MultipartFile file) throws IOException {
        // Generar nombre único para el archivo
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = UUID.randomUUID().toString() + extension;
        
        // Crear la clave única (category/uniqueFilename)
        String imageKey = uniqueFilename;
        
        try {
            // Crear el request para subir el archivo
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(imageKey)
                    .contentType(file.getContentType())
                    .acl(ObjectCannedACL.PUBLIC_READ) // Hacer el archivo público
                    .contentLength(file.getSize())
                    .build();

            // Subir el archivo
            s3Client.putObject(putObjectRequest, 
                              RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            
            return imageKey;
            
        } catch (S3Exception e) {
            throw new IOException("Error al subir imagen a Digital Ocean Spaces: " + e.getMessage(), e);
        }
    }

    public String getImageUrl(String imageKey) {
        if (imageKey == null || imageKey.isEmpty()) {
            return null;
        }
           
        // Si no, usar la URL directa del Space
        try {
            GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(imageKey)
                    .build();
                    
            return s3Client.utilities().getUrl(getUrlRequest).toString();
        } catch (S3Exception e) {
            return null;
        }
    }

    public void deleteImage(String imageKey) throws IOException {
        if (imageKey != null && !imageKey.isEmpty()) {
            try {
                DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(imageKey)
                        .build();
                        
                s3Client.deleteObject(deleteObjectRequest);
                
            } catch (S3Exception e) {
                throw new IOException("Error al eliminar imagen de Digital Ocean Spaces: " + e.getMessage(), e);
            }
        }
    }
    
    public byte[] getImageBytes(String imageKey) throws IOException {
        if (imageKey == null || imageKey.isEmpty()) {
            return null;
        }
        
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(imageKey)
                    .build();
                    
            return s3Client.getObject(getObjectRequest).readAllBytes();
            
        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                return null; // Archivo no encontrado
            }
            throw new IOException("Error al obtener imagen de Digital Ocean Spaces: " + e.getMessage(), e);
        }
    }
    
    public boolean imageExists(String imageKey) {
        if (imageKey == null || imageKey.isEmpty()) {
            return false;
        }
        
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(imageKey)
                    .build();
                    
            s3Client.headObject(headObjectRequest);
            return true;
            
        } catch (S3Exception e) {
            return false;
        }
    }
}