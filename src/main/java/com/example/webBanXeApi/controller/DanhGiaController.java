/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.DTO.CTHDDTO;
import com.example.webBanXeApi.DTO.DanhGiaDTO;
import com.example.webBanXeApi.DTO.HoaDonDTO;
import com.example.webBanXeApi.models.CTHD;
import com.example.webBanXeApi.models.DanhGia;
import com.example.webBanXeApi.models.HoaDon;
import com.example.webBanXeApi.models.ResponseObject;
import com.example.webBanXeApi.repositories.DanhGiaRepository;
import com.example.webBanXeApi.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/danhgia")
    public ResponseEntity<ResponseObject> getAllProductswithpagereal(
            @RequestParam(defaultValue = "0") long id,
            //@RequestParam(required = false) String tinhtrang,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        List<DanhGia> result = null;
        
        if (id != 0) {
            result = danhgiarepo.findByUser_Id(id);
            System.out.print("vao cao nayyy");
        } else {
            result = danhgiarepo.findAll();
        }
       

        //result = repository.findAll();
        int total = result.size();
        int totalpage = 1;
        if(size!=0){

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, total);
        result = result.subList(fromIndex, toIndex);
        totalpage = (int)total/size;

        }
        //List<CTHDDTO> cthddtos ;
        List<DanhGiaDTO> danhGiaDTOs = new ArrayList<>();
        for ( DanhGia danhgia : result) {
 
            DanhGiaDTO danhgiaDTO = new DanhGiaDTO(danhgia.getId(),danhgia.getRating(),danhgia.getBinhLuan(),danhgia.getUser(),danhgia.getXe());

            danhGiaDTOs.add(danhgiaDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject(total,danhGiaDTOs,totalpage)
            );
    }
    
    
    
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
    
    @GetMapping("/danhgia/{id}")
    public ResponseEntity<ResponseObject> getAllProductswithpagereal2(
            @RequestParam(defaultValue = "0") int rating,
            @PathVariable Long id,
            @RequestParam(required = false) String tinhtrang,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        List<DanhGia> result = null;
        if (rating != 0) {
            result = danhgiarepo.findByRatingAndXe_Id(rating,id);
        } else {
            result = danhgiarepo.findByXe_Id(id);
        }
        
        
        
        //result = repository.findAll();
        int total = result.size();
        int totalpage = 1;
        if(size!=0){

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, total);
        result = result.subList(fromIndex, toIndex);
        totalpage = (int)total/size;

        }
        List<DanhGiaDTO> danhGiaDTOs = new ArrayList<>();
        for ( DanhGia danhgia : result) {
 
            DanhGiaDTO danhgiaDTO = new DanhGiaDTO(danhgia.getId(),danhgia.getRating(),danhgia.getBinhLuan(),danhgia.getUser(),danhgia.getXe());

            danhGiaDTOs.add(danhgiaDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject(total,danhGiaDTOs,totalpage)
            );
    }
}

    


