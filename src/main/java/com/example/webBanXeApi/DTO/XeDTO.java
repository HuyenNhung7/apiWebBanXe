/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.DTO;

/**
 *
 * @author TRUC
 */
public class XeDTO {
   private String tenxe;
   private long id;

    public String getTenxe() {
        return tenxe;
    }

    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public XeDTO(String tenxe, long id) {
        this.tenxe = tenxe;
        this.id = id;
    }
   
}
