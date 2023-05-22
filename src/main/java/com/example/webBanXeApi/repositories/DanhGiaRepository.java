/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.DanhGia;
import com.example.webBanXeApi.models.HoaDon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TRUC
 */
@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long>{
    public List<DanhGia> findByXe_Id(long id);
    public List<DanhGia> findByUser_Id(long id);    
    public List<DanhGia> findByRatingAndXe_Id(int rating,long xeId);

    
}
