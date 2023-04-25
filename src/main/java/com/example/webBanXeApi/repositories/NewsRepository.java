package com.example.webBanXeApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.webBanXeApi.models.News;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    // custom methods can be added here

}
