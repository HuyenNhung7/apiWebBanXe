package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.Xe;

import java.util.List;

public class XeDto {
    private List<Xe> data;
    private int totalXe = 0;
    private int totalXeByfilter = 0;

    private int page = 0;
    private int pageSize = 0;
    private int totalPage = 0;
    public XeDto(List<Xe> data, int totalXe, int page, int size) {
        this.data = data;
        this.totalXe = totalXe;
        this.totalXeByfilter = this.data.size();
        this.page = page;
        this.pageSize = size;
        if(pageSize == 0)
            this.totalPage = 1;
        else
            this.totalPage = this.data.size() / size + 1;
    }

    public List<Xe> getData() {
        return data;
    }

    public void setData(List<Xe> data) {
        this.data = data;
    }

    public int getTotalXe() {
        return totalXe;
    }

    public void setTotalXe(int totalXe) {
        this.totalXe = totalXe;
    }

    public int getTotalXeByfilter() {
        return totalXeByfilter;
    }

    public void setTotalXeByfilter(int totalXeByfilter) {
        this.totalXeByfilter = totalXeByfilter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
