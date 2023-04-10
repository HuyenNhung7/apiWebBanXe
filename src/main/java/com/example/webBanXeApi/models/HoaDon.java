/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRUC
 */
@Entity
@Table(name = "tblhoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "NgayHoaDon", nullable = false,length = 300)
    private String ngayHD;
    
    @Column(name = "TinhTrang", nullable = false,length = 300)
    private String tinhTrang;
    
    @Column(name = "TriGia", nullable = false,length = 300)
    private String triGia;
    
    @OneToMany(mappedBy = "hd", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<CTHD> cthds = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKH")
    private user us2;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNV")
    private user us3;
}
