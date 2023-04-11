package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.Xe;
import java.util.List;
import java.util.Optional;

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
    public Optional<Xe> getOneXeById(long id);

    // Hàm lấy xe theo trang
    public List<Xe> getXeByPage(int page, int xePerPage);

}
