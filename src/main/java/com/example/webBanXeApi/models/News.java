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
@Table(name = "tblnews")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "Title")
    private String title;
    
    @Column(name = "Hinh")
    private String hinh;
    
    @Column(name = "NoiDung")
    private String noiDung;
    
    @Column(name = "MoTa")
    private String moTa;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TacGia")
    private user us;
    
    
}
