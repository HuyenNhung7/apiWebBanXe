/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.webBanXeApi.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
        List<Product> findByProductName(String productName);
        List<Product> findByUrl(String url);
        List<Product> findByUrlAndProductNameContainingIgnoreCase(String url, String productName);
List<Product> findByProductNameContainingIgnoreCase(String productName);
    //List<product> findByCategoryAndNameContaining(String category, String name);
    //List<product> findByCategory(String category);

    
}
