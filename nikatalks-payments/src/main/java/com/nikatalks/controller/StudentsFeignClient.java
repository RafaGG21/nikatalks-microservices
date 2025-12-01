package com.nikatalks.controller;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

import com.nikatalks.commons.dto.StudentDto;



@FeignClient(name = "nikatalks-auth", url = "${nikatalks-auth.service.url:http://nikatalks-auth:8085}")
public interface StudentsFeignClient {

	@GetMapping("/auth/students/get-user-email/{email}")
	 public StudentDto findByEmail(@PathVariable String  email);

}
