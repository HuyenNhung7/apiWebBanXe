/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.DTO;

import com.example.webBanXeApi.models.User;
import com.example.webBanXeApi.models.Xe;

/**
 *
 * @author TRUC
 */
public class DanhGiaDTO {
    private Long id;
    private int rating;
    private String binhLuan;
    private UserDTO us;
    private XeDTO xe;
    
    

    public DanhGiaDTO(Long id, int rating, String binhLuan, User us, Xe xe) {
        this.id = id;
        this.rating = rating;
        this.binhLuan = binhLuan;
        this.us=new UserDTO(us.getId(),us.getUsername());
        this.xe=new XeDTO(xe.getTen(), xe.getId());
    }

    public UserDTO getUs() {
        return us;
    }

    public void setUs(UserDTO us) {
        this.us = us;
    }

    public XeDTO getXe() {
        return xe;
    }

    public void setXe(XeDTO xe) {
        this.xe = xe;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }
    
    
}
