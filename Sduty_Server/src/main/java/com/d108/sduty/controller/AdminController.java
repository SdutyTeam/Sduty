package com.d108.sduty.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d108.sduty.dto.Admin;
import com.d108.sduty.service.AdminService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@ApiOperation(value = "로그인")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody ObjectNode objectNode){
		JsonNode idNode = objectNode.get("id");
		JsonNode passwordNode = objectNode.get("password");
		if(idNode==null || passwordNode==null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		String id = idNode.asText();
		String password = passwordNode.asText();
		Optional<Admin> adminOp = adminService.getAdmin(id);
		if(adminOp.isPresent()) {
			Admin adminObject = adminOp.get();
			if(password.equals(adminObject.getPassword())) {
				return new ResponseEntity<Admin>(adminObject, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
}
