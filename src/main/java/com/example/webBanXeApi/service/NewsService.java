package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.News;
import com.example.webBanXeApi.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Integer id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    }

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public News updateNews(Integer id, News news) {
        News existingNews = getNewsById(id);
        existingNews.setTitle(news.getTitle());
        existingNews.setContent(news.getContent());
        existingNews.setAuthor(news.getAuthor());
        existingNews.setDate(news.getDate());
        existingNews.setImage(news.getImage());
        existingNews.setCategory(news.getCategory());
        return newsRepository.save(existingNews);
    }

    public void deleteNews(Integer id) {
        newsRepository.deleteById(id);
    }

}
