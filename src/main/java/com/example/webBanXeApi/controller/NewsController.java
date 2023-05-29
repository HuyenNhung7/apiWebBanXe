/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.News;
import com.example.webBanXeApi.models.Product;
import com.example.webBanXeApi.models.ResponseObject;
import com.example.webBanXeApi.repositories.NewsRepository;
//import com.example.webBanXeApi.repositories.productRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TRUC
 */
@RestController
public class NewsController {
    @Autowired
    private NewsRepository repository;
    
    @PostMapping("/news/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody News newNews) {
        /*List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "product name already taken!", "")
            );
        }*/

        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Insert product successfully!", repository.save(newNews))
            );

    }
    @GetMapping("/news")
    public ResponseEntity<ResponseObject> getAllProductswithpagereal(
            @RequestParam(required = false) String name,
            //@RequestParam(required = false) String url,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        List<News> result = null;
        if (name != null) {
            result = repository.findByTitle(name);
        } else {
            result = repository.findAll();
            System.out.print("ko null");
        }

        int total = result.size();
        int totalpage = 1;
        if(size!=0){

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, total);
        result = result.subList(fromIndex, toIndex);
        totalpage = (int)total/size;

        }

        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject(total,totalpage,result)
            );
    }
    
    
}
