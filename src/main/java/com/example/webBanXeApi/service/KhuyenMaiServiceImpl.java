package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.KhuyenMai;
import com.example.webBanXeApi.models.Xe;
import com.example.webBanXeApi.repositories.KhuyenMaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KhuyenMaiServiceImpl implements IKhuyenMaiService{
    @Autowired
    private KhuyenMaiRepository repository;

    @Override
    public KhuyenMai addKM(KhuyenMai khuyenMai) {
        if(khuyenMai != null) {
            return repository.save(khuyenMai);
        }
        return null;
    }

    @Override
    public KhuyenMai updateKM(long id, KhuyenMai khuyenMai) {
        if(khuyenMai != null) {
            KhuyenMai updateKM = repository.findById(id).orElse(null);
            updateKM.setTen(khuyenMai.getTen());
            updateKM.setTriGiaKM(khuyenMai.getTriGiaKM());
            updateKM.setNgayBD(khuyenMai.getNgayBD());
            updateKM.setNgayKT(khuyenMai.getNgayKT());
            return repository.save(updateKM);
        }
        return null;
    }

    @Override
    public boolean deleteKM(long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public KhuyenMai getKMById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<KhuyenMai> getAllKM(int page, int size) {
        List<KhuyenMai> allKM = repository.findAll();
        if(size != 0) {
            int firstKMIndex = size * page;
            int lastKMIndex = firstKMIndex + size;
            if(lastKMIndex > allKM.size())
                lastKMIndex = allKM.size();
            allKM = allKM.subList(firstKMIndex, lastKMIndex);
        }
        return allKM;

    }

    @Override
    public List<KhuyenMai> getKMAvailable() {
        List<KhuyenMai> allKM = repository.findAll();
        List<KhuyenMai> results = new ArrayList<>(allKM);
        for (KhuyenMai km : allKM) {
            if(!km.isConHieuLuc()) {
                results.remove(km);
                System.out.println("1");
            }
        }
        return results;
    }

}
