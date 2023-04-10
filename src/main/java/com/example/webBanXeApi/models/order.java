/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author TRUC
 */
@Entity
@Table(name="orders")
public class order {
    
    @Id
    @SequenceGenerator(
        name = "order_sequence",
        sequenceName = "order_sequence",
        allocationSize = 1 // tăng 1
    )
    
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "order_sequence"
    )
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "total_price")
    private Double totalPrice;
    
   @OneToMany(mappedBy = "od", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<detailorder> detailOrders = new ArrayList<>();

    //private Set<product> pd = new HashSet<>();
    
    //@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    //@JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    //private List<orderitem> orderItems;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    //private user user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //public List<orderitem> getOrderItems() {
      //  return orderItems;
    //}

    //public void setOrderItems(List<orderitem> orderItems) {
      //  this.orderItems = orderItems;
    //}

    //public user getUser() {
     //   return user;
    //}

    //public void setUser(user user) {
      //  this.user = user;
    //}
    
    }
