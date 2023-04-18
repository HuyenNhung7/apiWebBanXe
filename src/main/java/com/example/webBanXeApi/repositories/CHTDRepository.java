/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.CTHD;
import com.example.webBanXeApi.models.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TRUC
 */
@Repository
public interface CHTDRepository  extends JpaRepository<CTHD, Long> {
    //HoaDon findById(Long orderId);

}
