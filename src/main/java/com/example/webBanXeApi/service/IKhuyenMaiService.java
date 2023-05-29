package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.KhuyenMai;

import java.util.List;

public interface IKhuyenMaiService {
    // Hàm thêm khuyến mãi
    public KhuyenMai addKM(KhuyenMai khuyenMai);

    // Hàm chỉnh sửa thông tin khuyến mãi
    public KhuyenMai updateKM(long id, KhuyenMai khuyenMai);

    // Hàm xóa KM
    public boolean deleteKM(long id);

    // Hàm lấy ma km by id
    public KhuyenMai getKMById(long id);

    // lấy tất cả mã km
    public List<KhuyenMai> getAllKM(int page, int size);

    // Lấy ra khuyến mãi có hiêệu lực
    public List<KhuyenMai> getKMAvailable();
}
