/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.webBanXeApi.repositories.productRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
public class Database {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    
    @Bean
    CommandLineRunner initDatabase(productRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                product productA = new product("Macbook pro 16 inch", 2020, 2400.0, "");
//                product productB = new product("Ipad air green", 2021, 599.0, "");
//                
//                // Lưu 2 sản phẩm / 2 obj vào repository
////                System.out.println("insert data: " + proRepository.save(productA));
////                System.out.println("insert data: " + proRepository.save(productB));
//                logger.info("insert data: " + productRepository.save(productA));
//                logger.info("insert data: " + productRepository.save(productB));

            }
        };
    }
}
