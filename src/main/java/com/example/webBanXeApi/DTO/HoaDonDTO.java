/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.DTO;

import com.example.webBanXeApi.models.CTHD;
import com.example.webBanXeApi.models.HoaDon;
import com.example.webBanXeApi.models.User;
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
    
    private String ngayHD;
    private long id;
    private UserDTO us2;

    public String getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public long getId() {
        return id;
    }

    
    
    
    
    
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

    public UserDTO getUs2() {
        return us2;
    }

    public void setUs2(UserDTO us2) {
        this.us2 = us2;
    }
    
    
    
    public HoaDonDTO(long id, String ngayHD, User us2,String tinhtrang, String trigia, List<CTHDDTO> detailOrders) {
        this.tinhtrang = tinhtrang;
        this.trigia = trigia;
        this.detailOrders = detailOrders;
        this.id=id;
        this.ngayHD=ngayHD;
        this.us2=new UserDTO(us2.getId(),us2.getUsername());
    }

    public void setDetailOrders(List<CTHDDTO> detailOrders) {
        this.detailOrders = detailOrders;
    }
    private List<CTHDDTO> detailOrders;
}
