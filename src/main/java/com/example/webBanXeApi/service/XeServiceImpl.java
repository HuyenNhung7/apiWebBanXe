package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.KhuyenMai;
import com.example.webBanXeApi.models.Xe;
import com.example.webBanXeApi.repositories.KhuyenMaiRepository;
import com.example.webBanXeApi.repositories.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class XeServiceImpl implements IXeService {
    @Autowired
    private XeRepository repository;

    @Autowired
    private KhuyenMaiRepository repository1;

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
//            Xe xe2 = repository.ge
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

    @Override
    public List<Xe> get10XeMoiNhat() {
        List<Xe> kq = new ArrayList<>();
        List<Xe> allXe = getAllXe();
        int index = allXe.size() - 10;
        if(index < 0)
            index = 0;
        kq = allXe.subList(index, allXe.size());
        return kq;
    }

    @Override
    public Xe addKMToXe(long id_xe, long id_km) {
        Xe xe = repository.findById(id_xe).orElse(null);
        KhuyenMai km = repository1.findById(id_km).orElse(null);
//        if(km.isConHieuLuc()) {
            if (xe != null) {
                if (km != null) {
                    if(xe.getKhuyenMai() == null){
                        xe.setKhuyenMai(km);
                        repository.save(xe);
                    } else {
                        return null;
                    }
                } else {
                    System.out.println("km ko ton tai");
                }
            } else {
                System.out.println("ko co xe nay");
//          }
        }
        return xe;
    }

    @Override
    public Xe deleteKMXe(long id_xe) {
        Xe xe = repository.findById(id_xe).orElse(null);
        xe.setKhuyenMai(null);
        return xe;
    }

    @Override
    public List<Xe> filterXe(String thuonghieu, double min_gia, double max_gia, int soCho, int namSX, String nguongoc, int khuyenmai) {
        List<Xe> lstXe = repository.findAll();
        List<Xe> result = new ArrayList<>(lstXe);
        int index;
        if(thuonghieu != null) {
            for (Xe xe : result) {
                if(!xe.getThuongHieu().trim().equalsIgnoreCase(thuonghieu)) {
                    lstXe.remove(xe);
                }
            }
        }

        if(max_gia !=0 && !lstXe.isEmpty()) {
            for (Xe xe : result) {
                if(xe.getGiaXe() < min_gia || xe.getGiaXe() > max_gia) {
//                    index = lstXe.indexOf(xe);
//                    lstXe.remove(index);
                    lstXe.remove(xe);
                }
            }
        }

        if(soCho != 0 && !lstXe.isEmpty()) {
            for (Xe xe : result) {
                if(xe.getSoCho() != soCho) {
//                    index = lstXe.indexOf(xe);
//                    lstXe.remove(index);
                    lstXe.remove(xe);
                }
            }
        }

        if(namSX != 0 && !lstXe.isEmpty()) {
            for (Xe xe : result) {
                if(xe.getNamSanXuat() != namSX) {
//                    index = lstXe.indexOf(xe);
//                    lstXe.remove(index);
                    lstXe.remove(xe);
                }
            }
        }

        if(nguongoc!=null && !lstXe.isEmpty()) {
            for (Xe xe : result) {
                System.out.println(xe.getTen() + ", " + xe.getNguonGoc().trim());
                if(!xe.getNguonGoc().trim().equalsIgnoreCase(nguongoc)) {
                    System.out.println("nguon goc: " + nguongoc);
//                    index = lstXe.indexOf(xe);
//                    lstXe.remove(index);
                    lstXe.remove(xe);
                }
            }
        }

        if(khuyenmai != -1 && !lstXe.isEmpty()) {
            for (Xe xe : result) {
                // co khuyen mai
                if(khuyenmai == 1) {
                    if(xe.getKhuyenMai() == null) {
//                        index = lstXe.indexOf(xe);
//                        lstXe.remove(index);
                        lstXe.remove(xe);
                    }
                }
                else {
                    if(xe.getKhuyenMai() != null) {
//                        index = lstXe.indexOf(xe);
//                        lstXe.remove(index);
                        lstXe.remove(xe);
                    }
                }
            }
        }
        return lstXe;
    }


}
