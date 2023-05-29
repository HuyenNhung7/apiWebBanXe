package com.example.webBanXeApi.DTO;

import com.example.webBanXeApi.models.KhuyenMai;

import java.util.List;
public class KhuyenMaiDTO {
    private List<KhuyenMai> data;
    private int totalKM = 0;
    private int page = 0;
    private int size = 0;

    public KhuyenMaiDTO(List<KhuyenMai> data, int totalKM, int page, int size) {
        this.totalKM = totalKM;
        this.page = page;
        this.size = size;
        this.data = data;
    }

    public List<KhuyenMai> getData() {
        return data;
    }

    public void setData(List<KhuyenMai> data) {
        this.data = data;
    }

    public int getTotalKM() {
        return totalKM;
    }

    public void setTotalKM(int totalKM) {
        this.totalKM = totalKM;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
