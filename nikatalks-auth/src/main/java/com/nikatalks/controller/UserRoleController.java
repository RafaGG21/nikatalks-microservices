package com.nikatalks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nikatalks.commons.dto.UserDetailsDto;
import com.nikatalks.service.UserRoleService;

@RestController
@RequestMapping("/auth/user")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/role/{email}")
    public ResponseEntity<String> getUserRole(@PathVariable String email) {
        try {
            String role = userRoleService.getUserRole(email);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al verificar el rol del usuario");
        }
    }
    
    @GetMapping("/details/{email}")
    public ResponseEntity<UserDetailsDto> getUserDetails(@PathVariable String email) {
        try {
            UserDetailsDto userDetails = userRoleService.getUserDetails(email);
            return ResponseEntity.ok(userDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}