/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.DTO;

import com.example.webBanXeApi.models.CTHD;

/**
 *
 * @author TRUC
 */
public class CTHDDTO {
    private Long id;
    private int soLuong;
    private XeDTO xedto;

    public CTHDDTO(CTHD cthd) {
        this.id = cthd.getId();
        this.soLuong=cthd.getSoLuong();
        this.xedto=new XeDTO(cthd.getXe().getTen(),cthd.getXe().getId());
    }

    public CTHDDTO(Long id, int soLuong, XeDTO xedto) {
        this.id = id;
        this.soLuong = soLuong;
        this.xedto = xedto;
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

    public XeDTO getXedto() {
        return xedto;
    }

    public void setXedto(XeDTO xedto) {
        this.xedto = xedto;
    }
    
}
