/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author TRUC
 */
@Entity
@Table(name="CTHD")
public class CTHD {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "MaHD")
   private HoaDon hd;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "MaXe")
   private Xe xe;
   
   @Column(name = "SoLuong")
   private int soLuong;
   
   @Column(name = "Gia")
   private int gia;
   
   @Column(name = "TenXe")
   private String TenXe;

}
