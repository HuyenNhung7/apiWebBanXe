package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Long> {
//     KhuyenMai getById(long id);
}
