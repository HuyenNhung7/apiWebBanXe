package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.Xe;
import java.util.List;
public interface IXeService {
    //Hàm thêm xe
    public Xe addXe(Xe xe);

    //Hàm chỉnh sửa thông tin xe
    public Xe updateXe(long id, Xe xe);

    //Hàm xóa xe
    public boolean deleteXe(long id);

    //Hàm lấy danh sách Xe
    public List<Xe> getAllXe();

    // Hàm lấy 1 Xe theo Id
    public Xe getOneXeById(long id);
}
