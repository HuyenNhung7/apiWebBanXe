/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

import jakarta.persistence.*;

/**
 *
 * @author TRUC
 */
@Entity
@Table(name = "tlbform")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "TenNguoiGui", nullable = false, length = 300)
    private String tenNguoiGui;

    @Column(name = "SoDienThoai", nullable = false)
    private String soDienThoai;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Message", nullable = false)
    private String message;

    public Form() {
    }

    public Form(String tenNguoiGui, String soDienThoai, String email, String message) {
        this.tenNguoiGui = tenNguoiGui;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getTenNguoiGui() {
        return tenNguoiGui;
    }

    public void setTenNguoiGui(String tenNguoiGui) {
        this.tenNguoiGui = tenNguoiGui;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
