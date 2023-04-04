/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.webBanXeApi.service.jwtService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author TRUC
 */
@RestController
public class helloWorldController {
    @Autowired
    private jwtService jwtservice;
    @Autowired
    private AuthenticationManager authenticationManager;
     //@Autowired(required = false)
    //private HttpServletRequest request;
    
    @RequestMapping({ "/hello" })
	public String firstPage() {
		return "dcm cuoc doiiiiiiiiiii";
	}
    @PostMapping({ "/hello2" })
	public String firstPage(@RequestBody user authuser) {
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
    public String authenticateAndGetToken(@RequestBody user authuser) {
        //org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authuser.getUsername(), authuser.getPassword()));
        //if (authentication.isAuthenticated()) {
        return jwtservice.generateToken(authuser.getUsername());
        //} else {
        //    throw new UsernameNotFoundException("invalid user request !");
        //}


        
    }
}
