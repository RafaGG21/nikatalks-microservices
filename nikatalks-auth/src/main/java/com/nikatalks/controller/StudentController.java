package com.nikatalks.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nikatalks.commons.dto.StudentDto;

import com.nikatalks.commons.entity.Student;
import com.nikatalks.service.StudentService;


@RestController
@RequestMapping("/auth/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @PostMapping("/register")
    public ResponseEntity<StudentDto> registerStudent(@RequestBody Student request) {
    	final StudentDto studentDto = studentService.registerStudent(request);
    	return studentDto != null ? ResponseEntity.ok(studentDto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/subscription/{idUser}/{subscriptionId}/{idSubscriptionStripe}")
    public ResponseEntity<StudentDto> assignSubscription(@PathVariable Long idUser, 
    		@PathVariable Long subscriptionId, 
    		@PathVariable String idSubscriptionStripe) {
        return ResponseEntity.ok(studentService.assignSubscription(idUser, subscriptionId, idSubscriptionStripe));
    }
    
    @PutMapping("/cancel-subscription/{idUser}")
    public ResponseEntity<StudentDto> assignSubscription(@PathVariable Long idUser) {
        return ResponseEntity.ok(studentService.cancelSubscription(idUser));
    }
    
    
    @PostMapping("/upload-image")
    public ResponseEntity<Boolean> uploadFile(
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("email") String email) throws IOException {
        Boolean ok = Boolean.valueOf(studentService.uploadImage(file, email));
        
        return ResponseEntity.ok(ok);
		
    }
    
    @GetMapping("/get-image/{imageKey}")
    public ResponseEntity<String> getImageUrl(@PathVariable String imageKey) {
    	String imageUrl = studentService.getImageUrl(imageKey);

    	return StringUtils.isNotEmpty(imageUrl) ? ResponseEntity.ok(imageUrl) : 
			ResponseEntity.badRequest().build();
    }
    
    @GetMapping("/get-user-email/{email}")
    public StudentDto findByEmail(@PathVariable String email) {
    	return studentService.findByEmail(email);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAll(){
    	final List<StudentDto> listStudents = studentService.getAll();
        return ResponseEntity.ok(listStudents);
    }
}