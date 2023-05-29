/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.webBanXeApi.repositories;

import com.example.webBanXeApi.models.News;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TRUC
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    public List<News> findByTitle(String name);
}
