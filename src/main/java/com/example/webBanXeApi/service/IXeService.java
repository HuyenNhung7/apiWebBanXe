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
    public Xe getOneXeById(long id);

    // Hàm lấy xe theo trang
    public List<Xe> getAllXeByPage(int currentPage, int size);

    // Hàm phân trang cho một danh sách xe bất kỳ
    public List<Xe> getXeByPage(int currentPage, int size, List<Xe> lstXe);

    // Hàm tìm kiếm xe
    public List<Xe> getXeByNameAndBranch(String name, String thuongHieu, int currentPage, int size);

    // Hàm giảm số lượng của một xe khi có thêm xe vào hóa đơn
    public boolean giamSoLuongXe(long id, int soLuongMua);

    public List<Xe> getXeByDeXuat();
}
