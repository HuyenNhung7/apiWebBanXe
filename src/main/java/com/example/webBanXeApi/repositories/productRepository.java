/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.webBanXeApi.models.product;
import java.util.List;

public interface productRepository extends JpaRepository<product, Long> {
        List<product> findByProductName(String productName);        
        List<product> findByUrl(String url);
        List<product> findByUrlAndProductNameContainingIgnoreCase(String url, String productName);
List<product> findByProductNameContainingIgnoreCase(String productName);
    //List<product> findByCategoryAndNameContaining(String category, String name);
    //List<product> findByCategory(String category);
}
