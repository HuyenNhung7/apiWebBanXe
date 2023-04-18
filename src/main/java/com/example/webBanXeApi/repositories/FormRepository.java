package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
}
