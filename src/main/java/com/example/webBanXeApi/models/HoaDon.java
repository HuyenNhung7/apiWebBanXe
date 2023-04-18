/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
   //@JsonManagedReference
    private List<CTHD> cthds = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKH")
    private User us2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTriGia() {
        return triGia;
    }

    public void setTriGia(String triGia) {
        this.triGia = triGia;
    }

    public List<CTHD> getCthds() {
        return cthds;
    }

    public void setCthds(List<CTHD> cthds) {
        this.cthds = cthds;
    }

    public User getUs2() {
        return us2;
    }

    public void setUs2(User us2) {
        this.us2 = us2;
    }

    public User getUs3() {
        return us3;
    }

    public void setUs3(User us3) {
        this.us3 = us3;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNV")
    private User us3;
}
