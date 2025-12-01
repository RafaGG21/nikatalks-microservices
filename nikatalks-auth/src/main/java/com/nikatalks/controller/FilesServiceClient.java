package com.nikatalks.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "nikatalks-files", url = "${nikatalks.files.service.url:http://nikatalks-files:8088}")
public interface FilesServiceClient {

    @PostMapping(value = "/files/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestPart("file") MultipartFile file);

    @GetMapping("files/image-url/{imageKey}")
    String getImageUrl(@PathVariable String imageKey);
    
    @GetMapping("/files/image-bytes")
    byte[] getImageBytes(@RequestParam("imageKey") String imageKey);
    
    @DeleteMapping("/files/delete-image")
    void deleteImage(@RequestParam("imageKey") String imageKey);
}