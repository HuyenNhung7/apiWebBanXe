package com.example.webBanXeApi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tblKhuyenMai")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Ten", nullable = false, unique = true, length = 300)
    private String ten;

    @Column(name = "TriGia", nullable = false, unique = true)
    private double triGiaKM;

    @Column(name = "NgayBD")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate ngayBD;

    @Column(name = "NgayKT")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate ngayKT;

    @Column(name = "ConHieuLuc")
    private boolean conHieuLuc;

    public long getId() {
        return id;
    }

    public KhuyenMai() {
    }

    public KhuyenMai(String ten, double triGiaKM, LocalDate ngayBD, LocalDate ngayKT) {
        this.ten = ten;
        this.triGiaKM = triGiaKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.conHieuLuc = this.isConHieuLuc();
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getTriGiaKM() {
        return triGiaKM;
    }

    public void setTriGiaKM(double triGiaKM) {
        this.triGiaKM = triGiaKM;
    }

    public LocalDate getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(LocalDate ngayBD) {
        this.ngayBD = ngayBD;
    }

    public LocalDate getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(LocalDate ngayKT) {
        this.ngayKT = ngayKT;
    }

    public boolean isConHieuLuc() {
        LocalDate today = LocalDate.now();
        return today.isAfter(this.ngayBD) && today.isBefore(this.ngayKT);
    }
}
