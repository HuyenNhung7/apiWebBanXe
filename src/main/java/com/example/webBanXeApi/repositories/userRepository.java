/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.product;
import com.example.webBanXeApi.models.user;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TRUC
 */
@Repository
public interface userRepository extends JpaRepository<user, Long>{
    List<user> findByEmail(String email);      
}
