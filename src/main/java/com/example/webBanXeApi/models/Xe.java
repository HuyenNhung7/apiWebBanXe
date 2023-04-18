package com.example.webBanXeApi.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tblxe")
public class Xe {
    String datetimeString = "2023-04-09 10:30:00";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Ten", nullable = false, unique = true, length = 300)
    private String ten;

    @Column(name = "ThuongHieu")
    private String thuongHieu;

    @Column(name = "DongCo")
    private String dongCo;

    @Column(name = "SoCho")
    private int soCho;

    @Column(name = "KichThuoc")
    private String kichThuoc;

    @Column(name = "NguonGoc")
    private String nguonGoc;

    @Column(name = "VanTocToiDa")
    private String vanTocToiDa;

    @Column(name = "DungTich")
    private String dungTich;

    @Column(name = "TieuHaoNhienLieu")
    private String tieuHaoNhienLieu;

    @Column(name = "CongSuatCucDai")
    private String congSuatCucDai;

    @Column(name = "MauSac")
    private String mauSac;

    @Column(name = "GiaXe")
    private double giaXe;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "NamSanXuat")
    private int namSanXuat;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DeXuat")
    private Boolean deXuat = true;

    @OneToMany(mappedBy = "xe", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<CTHD> cthds = new ArrayList<>();
    
    @OneToMany(mappedBy = "xe2", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<KhoXe> khoxes = new ArrayList<>();
    
    
    public Xe(){};

    public Xe(String ten, String thuongHieu, String dongCo, int soCho, String kichThuoc, String nguonGoc, String vanTocToiDa, String dungTich, String tieuHaoNhienLieu, String congSuatCucDai, String mauSac, double giaXe, String hinhAnh, String moTa, int namSanXuat, int soLuong, Boolean deXuat) {
        this.ten = ten;
        this.thuongHieu = thuongHieu;
        this.dongCo = dongCo;
        this.soCho = soCho;
        this.kichThuoc = kichThuoc;
        this.nguonGoc = nguonGoc;
        this.vanTocToiDa = vanTocToiDa;
        this.dungTich = dungTich;
        this.tieuHaoNhienLieu = tieuHaoNhienLieu;
        this.congSuatCucDai = congSuatCucDai;
        this.mauSac = mauSac;
        this.giaXe = giaXe;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.namSanXuat = namSanXuat;
        this.soLuong = soLuong;
        this.deXuat = deXuat;
    }

    public long getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getDongCo() {
        return dongCo;
    }

    public void setDongCo(String dongCo) {
        this.dongCo = dongCo;
    }

    public int getSoCho() {
        return soCho;
    }

    public void setSoCho(int soCho) {
        this.soCho = soCho;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getNguonGoc() {
        return nguonGoc;
    }

    public void setNguonGoc(String nguonGoc) {
        this.nguonGoc = nguonGoc;
    }

    public String getVanTocToiDa() {
        return vanTocToiDa;
    }

    public void setVanTocToiDa(String vanTocToiDa) {
        this.vanTocToiDa = vanTocToiDa;
    }

    public String getDungTich() {
        return dungTich;
    }

    public void setDungTich(String dungTich) {
        this.dungTich = dungTich;
    }

    public String getTieuHaoNhienLieu() {
        return tieuHaoNhienLieu;
    }

    public void setTieuHaoNhienLieu(String tieuHaoNhienLieu) {
        this.tieuHaoNhienLieu = tieuHaoNhienLieu;
    }

    public String getCongSuatCucDai() {
        return congSuatCucDai;
    }

    public void setCongSuatCucDai(String congSuatCucDai) {
        this.congSuatCucDai = congSuatCucDai;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public double getGiaXe() {
        return giaXe;
    }

    public void setGiaXe(double giaXe) {
        this.giaXe = giaXe;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Boolean isDeXuat() {
        return deXuat;
    }

    public void setDeXuat(Boolean deXuat) {
        this.deXuat = true;
    }

}
