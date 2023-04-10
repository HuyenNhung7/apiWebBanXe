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
        if(id>0) {
            Xe xe1 = repository.getById(id);
            if (xe1 != null) {
                repository.delete(xe1);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Xe> getAllXe() {
        return repository.findAll();
    }

    @Override
    public Optional<Xe> getOneXeById(long id) {
        return repository.findById(id);
    }
}
