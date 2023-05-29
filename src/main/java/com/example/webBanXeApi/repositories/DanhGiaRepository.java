/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TRUC
 */
@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long>{
    
}
