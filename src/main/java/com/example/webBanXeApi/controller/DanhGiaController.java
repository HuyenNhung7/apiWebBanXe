/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.CTHD;
import com.example.webBanXeApi.models.DanhGia;
import com.example.webBanXeApi.models.HoaDon;
import com.example.webBanXeApi.models.ResponseObject;
import com.example.webBanXeApi.repositories.DanhGiaRepository;
import com.example.webBanXeApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TRUC
 */
@RestController
public class DanhGiaController {
    @Autowired
    private UserRepository userepo;
    @Autowired
    private DanhGiaRepository danhgiarepo;
    
    @PostMapping("/danhgia")
    ResponseEntity<ResponseObject> insertHD(@RequestBody DanhGia newDG) {
        // 2 products không nên trùng nhau
        //ist<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        /*if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "product name already taken!", "")
            );
        }*/
        DanhGia savedDG = danhgiarepo.save(newDG);

        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Insert successfully!", newDG)
            );

    }
}
