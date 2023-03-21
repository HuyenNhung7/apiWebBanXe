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
}
