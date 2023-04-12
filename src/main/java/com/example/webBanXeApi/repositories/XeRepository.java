package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.Product;
import com.example.webBanXeApi.models.Xe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// Long ở đây là một cái object
@Repository
public interface XeRepository extends JpaRepository<Xe, Long> {
    List<Xe> findAll();
    List<Xe> findByTenOrThuongHieuContainingIgnoreCase(String ten, String thuongHieu);
    List<Xe> findByTenContainingIgnoreCase(String ten);
    List<Xe> findByThuongHieuContainingIgnoreCase(String thuongHieu);
}
