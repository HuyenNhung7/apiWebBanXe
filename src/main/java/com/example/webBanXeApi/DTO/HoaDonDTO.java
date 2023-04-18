/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.DTO;

import com.example.webBanXeApi.models.CTHD;
import com.example.webBanXeApi.models.HoaDon;
import java.util.List;

/**
 *
 * @author TRUC
 */
public class HoaDonDTO {
    private String tinhtrang;

    /*public HoaDonDTO(HoaDon hd) {
        this.tinhtrang=hd.getTinhTrang();
        this.trigia=hd.getTriGia();
        this.detailOrders=hd.getCthds();
    }*/
    private String trigia;

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getTrigia() {
        return trigia;
    }

    public void setTrigia(String trigia) {
        this.trigia = trigia;
    }

    public List<CTHDDTO> getDetailOrders() {
        return detailOrders;
    }

    public HoaDonDTO(String tinhtrang, String trigia, List<CTHDDTO> detailOrders) {
        this.tinhtrang = tinhtrang;
        this.trigia = trigia;
        this.detailOrders = detailOrders;
    }

    public void setDetailOrders(List<CTHDDTO> detailOrders) {
        this.detailOrders = detailOrders;
    }
    private List<CTHDDTO> detailOrders;
}
