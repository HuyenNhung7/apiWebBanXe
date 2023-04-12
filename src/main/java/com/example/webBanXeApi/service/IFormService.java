package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.Form;

import java.util.List;

public interface IFormService {
    //Hàm thêm form
    public Form addForm(Form form);

    // Hàm xóa form
    public boolean deleteForm(Long id);

    //Ham xoa tat ca cac form
    public boolean deleteAllForm();

    //Hàm phân trang cho form
    public List<Form> formPerPage(int currentPage, int size, List<Form> lstForm);

    // Hàm lấy tất cả các form
    public List<Form> getAllForm();

    //Hàm lấy tất cả các form và phân trang
    public List<Form> getAllFormPerPage(int currentPage, int size);

    // Hàm tìm kiếm form theo id
    public  Form findFormById(long id);

}
