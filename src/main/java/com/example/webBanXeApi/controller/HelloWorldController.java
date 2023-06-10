/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.MyRequestPayload;
import com.example.webBanXeApi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.webBanXeApi.service.JWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author TRUC
 */




@RestController
public class HelloWorldController {
    @Autowired
    private JWTService jwtservice;
    @Autowired
    private AuthenticationManager authenticationManager;
     //@Autowired(required = false)
    //private HttpServletRequest request;
    
    @RequestMapping({ "/hello" })
	public String firstPage() {
		return "dcm cuoc doiiiiiiiiiii";
	}
    @PostMapping({ "/hello2" })
	public String firstPage(@RequestBody User authuser) {
            String password=authuser.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println(hashedPassword);
            /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isMatched = passwordEncoder.matches(rawPassword, encodedPassword);
        if (isMatched) {
            System.out.println("Password is matched!");
        } else {
            System.out.println("Password is not matched!");
        }*/
            return authuser.getPassword();
	}
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody User authuser) {
        //org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authuser.getUsername(), authuser.getPassword()));
        //if (authentication.isAuthenticated()) {
        return jwtservice.generateToken(authuser.getUsername());
        //} else {
        //    throw new UsernameNotFoundException("invalid user request !");
        //}


        
    }
    
     @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/myendpoint")
    public ResponseEntity<String> sendPostRequest(@RequestBody MyRequestPayload request) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MyRequestPayload payload = new MyRequestPayload(request.getText());
        HttpEntity<MyRequestPayload> httpEntity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:5000/sentiment",
                HttpMethod.POST, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        System.out.print(jsonNode.get("sentiment").asText());        

        return response;
    }
}
