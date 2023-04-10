/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

//POJO - Plain Old Java Object.

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Calendar;

@Entity
@Table(name="tblProduct")
public class Product {
    // Phải có "Primary key"
    @Id
    // sequence tạo rule thì thêm vào
    // cái sequence ở dưới giống như AUTO
    @SequenceGenerator(
        name = "product_sequence",
        sequenceName = "product_sequence",
        allocationSize = 1 // tăng 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "product_sequence"
    )
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //validate = constraint
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int year;
    private double price;
    private String url;

    @Column(name = "nguongoc")
    private String nguongoc;


    public Product() {
    }

    // caced field = transient
    // Những trường không đc lưu trong csdl mà đc tính toàn từ những trường khác
    @Transient
    private int age;
    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR)-year;
    }
    public Product(String productName, int year, double price, String url, String nguongoc) {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
        this.nguongoc=nguongoc;
    }

    // id có thể tự sinh ra trong csdl

    //Hiện thông tin chi tiết của đối tượng
    @Override
    public String toString() {
        return "product{" + "id=" + id +
                ", productName=" + productName +
                ", year=" + year +
                ", price=" + price +
                ", url=" + url + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

