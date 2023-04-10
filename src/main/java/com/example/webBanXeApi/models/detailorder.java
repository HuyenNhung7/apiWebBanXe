/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.domain.Sort.Order;

/**
 *
 * @author TRUC
 */
@Entity
@Table(name="detailorder")
public class detailorder {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "order_id")
   private order od;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id")
   private product pd;

   private int quantity;
}
