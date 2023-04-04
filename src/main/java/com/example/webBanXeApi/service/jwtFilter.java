/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author TRUC
 */
@Component
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    private jwtService jwtService;
    //@Autowired
    //private UserInfoUserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String path = request.getServletPath();
        String token=null;
        String username =null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
           token = authHeader.substring(7);
           username = jwtService.extractUsername(token);
        }
        if(path.startsWith("/hello2"))
        {
            //System.out.print(path);            
            System.out.print("route hello2");
            //System.out.print(username);
            if (username.equals("truc")) {
                //request.getSession().setAttribute("test", username);
                System.out.print(username);
                request.setAttribute("pass", "testing");
                filterChain.doFilter(request, response);
            }
            else{
                //filterChain.doFilter(request, response);
                throw new UnsupportedOperationException("Not supported yet.");
            /*System.out.print("khong co bear o request");
            filterChain.doFilter(request, response);*/
            }
        } else {
            System.out.print("route hello1");
            if (username != null) {
            //request.getSession().setAttribute("test", username);
            System.out.print(username);
            filterChain.doFilter(request, response);
            }
            else{
                throw new UnsupportedOperationException("Not supported yet.");
            /*System.out.print("khong co bear o request");
            filterChain.doFilter(request, response);*/
            }
        }
        //filterChain.doFilter(request, response);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return !(path.startsWith("/hello")||path.startsWith("/hello2"));
    }
    
}
