/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.DTO.CTHDDTO;
import com.example.webBanXeApi.models.CTHD;
import com.example.webBanXeApi.models.HoaDon;
import com.example.webBanXeApi.models.Product;
import com.example.webBanXeApi.models.ResponseObject;
import com.example.webBanXeApi.models.User;
import com.example.webBanXeApi.repositories.UserRepository;
import com.example.webBanXeApi.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TRUC
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JWTService jwtservice;
    @Autowired
    public JavaMailSender emailSender;
    
    @PostMapping("/register")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
        // 2 products không nên trùng nhau
        List<User> foundUsers = repository.findByEmail(newUser.getEmail().trim());
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
    ResponseEntity<ResponseObject> loginUser(@RequestBody User newUser) {
        List<User> foundUsers = repository.findByEmail(newUser.getEmail().trim());
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
        String token =  foundUsers.get(0).getToken();
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Login success!", foundUsers.get(0))
            );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        Optional<User> updateUser = repository.findById(id)
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
    ResponseEntity<ResponseObject> forgetUser(@RequestBody User newUser) {
        List<User> foundUsers = repository.findByEmail(newUser.getEmail().trim());
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
    
    @PostMapping("/resetpass")
    ResponseEntity<ResponseObject> forgetUser(@RequestBody User newUser, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String path = request.getServletPath();
        String token2=null;
        String username =null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
           token2 = authHeader.substring(7);
           System.out.print(token2);
        }
        
        
        String verifyToken =null; 
        verifyToken= jwtservice.extractUsername(token2);
        
        List<User> foundUsers = repository.findByEmail(verifyToken);
        if(foundUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "Cannot change due to wrong token", "")
            );
        }
        
        String password=newUser.getPassword();
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String hashedPassword = passwordEncoder.encode(password);
        
        foundUsers.get(0).setPassword(hashedPassword);
        repository.save(foundUsers.get(0));
        
        
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Change password success!", foundUsers.get(0))
            );
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseObject> getAllProductswithcount(@PathVariable Long id) {
        Optional<User> result = repository.findById(id);
        User user= new User();
        if(result.isPresent()){
            user = result.get();
            if(user.getRole().equals("KH"))
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","ok",user)
                );  
            else
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("not found","not found",null)
                );
        }else{   
            return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("not found","not found",null)
                );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getAllProductswithcount2(@PathVariable Long id) {
        Optional<User> result = repository.findById(id);
        User user= new User();
        if(result.isPresent()){
            user = result.get();
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","ok",user)
                );            
        }else{   
            return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("not found","not found",null)
                );
        }
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
    
    
    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllProductswithpagereal(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        List<User> result = null;
        if (username == null ) {
            result = repository.findByRole(role);
//result = repository.findByProductNameContainingIgnoreCase(name);
        } else {
            result = repository.findByRoleAndUsernameContainingIgnoreCase(role,username);
        } 

        int total = result.size();
        int totalpage = 1;
        if(size!=0){

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, total);
        result = result.subList(fromIndex, toIndex);
        totalpage = (int)total/size;

        }

        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject(total,result,totalpage)
            );
    }
}
