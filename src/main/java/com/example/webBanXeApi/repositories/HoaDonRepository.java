/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.HoaDon;
import com.example.webBanXeApi.models.Xe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TRUC
 */

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    //public List<HoaDon> findByUserNameContainingIgnoreCase(String name);
    //HoaDon findById(Long orderId);

    public List<HoaDon> findByUs2(long id);
    
}
