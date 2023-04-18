/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.DTO.CTHDDTO;
import com.example.webBanXeApi.DTO.HoaDonDTO;
import com.example.webBanXeApi.models.CTHD;
import com.example.webBanXeApi.models.HoaDon;
import com.example.webBanXeApi.models.Product;
import com.example.webBanXeApi.models.ResponseObject;
import com.example.webBanXeApi.repositories.CHTDRepository;
import com.example.webBanXeApi.repositories.HoaDonRepository;
import com.example.webBanXeApi.repositories.userRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TRUC
 */
@RestController
public class HoaDonController {
     @Autowired
    private HoaDonRepository repository;
     
     @Autowired
    private CHTDRepository cthdrepo;
     
     @Autowired
    private userRepository userepo;
     
     
    @GetMapping("/hd/find/{id}")
    public ResponseEntity<ResponseObject> getorderbyid(@PathVariable Long id) {
        Optional<HoaDon> result = repository.findById(id);
        HoaDon hoadon= new HoaDon();
        List<CTHDDTO> cthddtos ;
        if(result.isPresent()){
            hoadon = result.get();
            List<CTHD> detailorders = hoadon.getCthds();
            cthddtos = detailorders.stream().map(dt-> new CTHDDTO(dt)).collect(Collectors.toList());

//System.out.print(detailorders);
        }else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
        HoaDonDTO dto = new HoaDonDTO(hoadon.getTinhTrang(),hoadon.getTriGia(),cthddtos);
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok","ok",dto))
            ;
    }
    
    @PostMapping("/hd/insert")
    ResponseEntity<ResponseObject> insertHD(@RequestBody HoaDon newHD) {
        // 2 products không nên trùng nhau
        //ist<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        /*if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "product name already taken!", "")
            );
        }*/
        HoaDon savedHD = repository.save(newHD);
        for(CTHD cthd:newHD.getCthds()){
            cthd.setHd(savedHD);
            cthdrepo.save(cthd);
            System.out.print("not null");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Insert successfully!", newHD)
            );

    }
    @DeleteMapping("hd/{id}")
    ResponseEntity<ResponseObject> deleteHD(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete product successfully", "")
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }
    
    @GetMapping("/hd")
    public ResponseEntity<ResponseObject> getAllProductswithpagereal(
            //@RequestParam(defaultValue = "0") long id,
            //@RequestParam(required = false) String url,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        List<HoaDon> result = null;
        /*if (id != 0) {
            result = repository.findByUs2(id);
        } else {
            result = repository.findAll();
        }*/
        result = repository.findAll();
        int total = result.size();
        int totalpage = 1;
        if(size!=0){

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, total);
        result = result.subList(fromIndex, toIndex);
        totalpage = (int)total/size;

        }
        List<CTHDDTO> cthddtos ;
        List<HoaDonDTO> hoaDonDTOs = new ArrayList<>();
        for (HoaDon hoaDon : result) {
             List<CTHD> detailorders = hoaDon.getCthds();
            cthddtos = detailorders.stream().map(dt-> new CTHDDTO(dt)).collect(Collectors.toList());

            HoaDonDTO hoaDonDTO = new HoaDonDTO(hoaDon.getTinhTrang(),hoaDon.getTriGia(),cthddtos);

            hoaDonDTOs.add(hoaDonDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject(total,hoaDonDTOs,totalpage)
            );
    }
    @PutMapping("/hd/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Object newHD, @PathVariable Long id) {
         Optional<HoaDon> result = repository.findById(id);
         HoaDon hoadon= new HoaDon();
     
        if(result.isPresent()){
            hoadon = result.get();
            hoadon.setTinhTrang("Hoan thanh");
            repository.save(hoadon);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Update successfully", null)
        );
    }
}
