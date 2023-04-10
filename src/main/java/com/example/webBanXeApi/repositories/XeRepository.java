package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.Xe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Long ở đây là một cái object
@Repository
public interface XeRepository extends JpaRepository<Xe, Long> {
}
