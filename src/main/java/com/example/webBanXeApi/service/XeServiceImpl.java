package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.Xe;
import com.example.webBanXeApi.repositories.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XeServiceImpl implements IXeService {
    @Autowired
    private XeRepository repository;

    @Override
    public Xe addXe(Xe xe) {
        if(xe != null) {
            // save() -> nếu có xe rồi thì cập nhật lại chưa có thì thêm mới
            return repository.save(xe);
        }
        return null;
    }

    @Override
    public Xe updateXe(long id, Xe xe) {
        if(xe != null) {
            Xe xe1 = repository.getById(id);
            if(xe1 != null) {
                xe1.setTen(xe.getTen());
                xe1.setThuongHieu(xe.getThuongHieu());
                xe1.setDongCo(xe.getDongCo());
                xe1.setSoCho(xe.getSoCho());
                xe1.setKichThuoc(xe.getKichThuoc());
                xe1.setNguonGoc(xe.getNguonGoc());
                xe1.setVanTocToiDa(xe.getVanTocToiDa());
                xe1.setDungTich(xe.getDungTich());
                xe1.setTieuHaoNhienLieu(xe.getTieuHaoNhienLieu());
                xe1.setCongSuatCucDai(xe.getCongSuatCucDai());
                xe1.setMauSac(xe.getMauSac());
                xe1.setGiaXe(xe1.getGiaXe());
                xe1.setHinhAnh(xe.getHinhAnh());
                xe1.setMoTa(xe.getMoTa());
                xe1.setNamSanXuat(xe.getNamSanXuat());
                xe1.setSoLuong(xe.getSoLuong());
                xe1.setDeXuat(xe1.isDeXuat());
            }
            return repository.save(xe1);
        }
        return null;
    }

    @Override
    public boolean deleteXe(long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Xe> getAllXe() {
        return repository.findAll();
    }

    @Override
    public Xe getOneXeById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Xe> getAllXeByPage(int page, int xePerPage) {
        List<Xe> allXe = this.getAllXe();
        int firstXeIndex = xePerPage*page;
        int lastXeIndex = firstXeIndex + xePerPage;
        if(lastXeIndex > allXe.size())
            lastXeIndex = allXe.size();
        List<Xe> xeByPage = allXe.subList(firstXeIndex, lastXeIndex);
        return xeByPage;
    }

    // Hàm này để phân trang các danh sách xe truyền vào nó
    @Override
    public List<Xe> getXeByPage(int page, int xePerPage, List<Xe> listXe) {
        int firstXeIndex = xePerPage*page;
        int lastXeIndex = firstXeIndex + xePerPage;
        if(lastXeIndex > listXe.size())
            lastXeIndex = listXe.size();
        List<Xe> xeByPage = listXe.subList(firstXeIndex, lastXeIndex);
        return xeByPage;
    }

    @Override
    public List<Xe> getXeByNameAndBranch(String ten, String thuongHieu, int page, int size) {
        List<Xe> results = null;
        if(ten != null && thuongHieu != null){
            results = repository.findByTenOrThuongHieuContainingIgnoreCase(ten, thuongHieu);
        }
        else if(ten != null) {
            results =  repository.findByTenContainingIgnoreCase(ten);
        }
        else if (thuongHieu != null) {
            results =  repository.findByThuongHieuContainingIgnoreCase(thuongHieu);
        }

        if(results != null) {
            results = getXeByPage(page, size, results);
        }
        return results;
    }

    // Hàm giảm số lượng xe khi có khách mua xe hay tạo hóa đơn
    @Override
    public boolean giamSoLuongXe(long id, int soLuongMua) {
        Xe xe = getOneXeById(id);
        if(xe != null) {
            if (xe.getSoLuong() < soLuongMua) {
                return false;
            }
            else {
                xe.setSoLuong(xe.getSoLuong() - soLuongMua);
                return true;
            }
        }
        return true;
    }

}
