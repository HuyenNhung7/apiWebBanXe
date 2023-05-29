/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.DTO;

/**
 *
 * @author TRUC
 */
public class DanhGiaDTO {
    private Long id;
    private int soLuong;
    private String binhLuan;

    public DanhGiaDTO(Long id, int soLuong, String binhLuan) {
        this.id = id;
        this.soLuong = soLuong;
        this.binhLuan = binhLuan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }
    
    
}
