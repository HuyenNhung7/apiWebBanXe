/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.ResponseObject;
import com.example.webBanXeApi.models.user;
import com.example.webBanXeApi.repositories.userRepository;
import com.example.webBanXeApi.service.jwtService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TRUC
 */
@RestController
@RequestMapping(path = "/api/v1/user")
public class userController {
    @Autowired
    private userRepository repository;
    @Autowired
    private jwtService jwtservice;
    @Autowired
    public JavaMailSender emailSender;
    
    @PostMapping("/register")
    ResponseEntity<ResponseObject> insertUser(@RequestBody user newUser) {
        // 2 products không nên trùng nhau
        List<user> foundUsers = repository.findByEmail(newUser.getEmail().trim());
        if(foundUsers.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "Existed email", "")
            );
        }
        newUser.setRole("KH");
         String password=newUser.getPassword();
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String hashedPassword = passwordEncoder.encode(password);
         System.out.println(hashedPassword);
         newUser.setPassword(hashedPassword);
         newUser.setToken(jwtservice.generateToken(newUser.getRole()));
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Register success!", repository.save(newUser))
            );
        
    }
    @PostMapping("/login")
    ResponseEntity<ResponseObject> loginUser(@RequestBody user newUser) {
        List<user> foundUsers = repository.findByEmail(newUser.getEmail().trim());
        if(foundUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "Email not existed", "")
            );
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isMatched = passwordEncoder.matches(newUser.getPassword(),foundUsers.get(0).getPassword());
        
        if(!isMatched) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "Password not correct", "")
            );
        }
        
        foundUsers.get(0).setToken(jwtservice.generateToken(foundUsers.get(0).getRole()));
        
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Login success!", foundUsers.get(0))
            );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody user newUser, @PathVariable Long id) {
        Optional<user> updateUser = repository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setDiachi(newUser.getDiachi());
                    user.setGioitinh(newUser.getGioitinh());
                    user.setSdt(newUser.getSdt());
                    user.setNgaysinh(newUser.getNgaysinh());
                    return repository.save(user);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Update user successfully", updateUser)
        );
    }
    @PostMapping("/forgetpass")
    ResponseEntity<ResponseObject> forgetUser(@RequestBody user newUser) {
        List<user> foundUsers = repository.findByEmail(newUser.getEmail().trim());
        if(foundUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "Email not existed", "")
            );
        }
        foundUsers.get(0).setVerifyToken(jwtservice.generateToken(foundUsers.get(0).getEmail()));

        String url=foundUsers.get(0).getVerifyToken();
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(newUser.getEmail());
        message.setSubject("Change your pass because u baka");
        message.setText(url);

        // Send Message!
        this.emailSender.send(message);
        
        
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Login success!", foundUsers.get(0))
            );
    }
    
    @PostMapping("/forgetpass/{token}")
    ResponseEntity<ResponseObject> forgetUser(@RequestBody user newUser, @PathVariable String token) {
        String verifyToken =null; 
        verifyToken= jwtservice.extractUsername(token);
        
        List<user> foundUsers = repository.findByEmail(verifyToken);
        if(foundUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "Cannot change due to wrong token", "")
            );
        }
        
        String password=newUser.getPassword();
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String hashedPassword = passwordEncoder.encode(password);
        
        foundUsers.get(0).setPassword(hashedPassword);

        
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Change password success!", foundUsers.get(0))
            );
    }
    
    
    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {
        String url="";
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo("tibutibu39@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }
}
