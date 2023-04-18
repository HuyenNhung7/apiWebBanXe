/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.ResponseObject;
import com.example.webBanXeApi.models.Product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webBanXeApi.repositories.productRepository;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/v1/products")
public class productController {
    // DI = Dependency Injection
    /* Khi để autowised thì ngay khi app chạy đối tượng repository đc
    tạo ra 1 lần và dùng mãi mãi */
    @Autowired
    private productRepository repository;

    @GetMapping("")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseObject> getAllProductswithcount() {
        List<Product> result = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject(result.size(),result)
            );
    }
    @GetMapping("/find/page")
    public ResponseEntity<ResponseObject> getAllProductswithpage() {
        List<Product> result = repository.findAll(PageRequest.of(1,2)).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject(result.size(),result)
            );
    }
    @GetMapping("/find/page/real")
    public ResponseEntity<ResponseObject> getAllProductswithpagereal(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String url,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        List<Product> result = null;
        if (name != null && url != null) {
            result = repository.findByUrlAndProductNameContainingIgnoreCase(url, name);
//result = repository.findByProductNameContainingIgnoreCase(name);
        } else if (name != null) {
            result = repository.findByProductNameContainingIgnoreCase(name);
        } else if (url != null) {
            result = repository.findByUrl(url);
        } else {
            result = repository.findAll();
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
                 new ResponseObject(total,result,totalpage)
            );
    }
    // Get detail product
    @GetMapping("/{id}")
    // Optional nghĩa là có thể null
    // return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent()?
            // HttpStatus.OK nghĩa là http code 200. dùng ? :
             ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Query product successfully!", foundProduct)
            ):

            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                 new ResponseObject("false", "Cannot find product with id = "+id, "")
            );
    }

    // insert new Product with POST method
    // Postman : Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // 2 products không nên trùng nhau
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                 new ResponseObject("failed", "product name already taken!", "")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                 new ResponseObject("ok", "Insert product successfully!", repository.save(newProduct))
            );

    }

    // update, insert = upsert. muốn update nhưng tìm ko thấy thì insert luôn
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updateProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Update product successfully", updateProduct)
        );
    }

    // Delete a product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
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
    
    
}

