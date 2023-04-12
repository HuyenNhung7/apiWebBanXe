package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.Form;

import java.util.List;

public class FormDto {
    private List<Form> data;
    private int totalForm = 0;
    private int totalFormByfilter = 0;

    private int currentPage = 0;
    private int pageSize = 0;
    private int totalPage = 0;

    public FormDto(List<Form> data, int totalForm, int currentPage, int pageSize) {
        this.data = data;
        this.totalForm = totalForm;
        this.totalFormByfilter = this.data.size();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        if(pageSize == 0)
            this.totalPage = 1;
        else
            this.totalPage = this.data.size() / pageSize + 1;
    }

    public List<Form> getData() {
        return data;
    }

    public void setData(List<Form> data) {
        this.data = data;
    }

    public int getTotalForm() {
        return totalForm;
    }

    public void setTotalForm(int totalForm) {
        this.totalForm = totalForm;
    }

    public int getTotalFormByfilter() {
        return totalFormByfilter;
    }

    public void setTotalFormByfilter(int totalFormByfilter) {
        this.totalFormByfilter = totalFormByfilter;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
