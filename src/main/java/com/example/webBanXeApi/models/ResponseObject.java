/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webBanXeApi.models;

import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author TGDD
 */

public class ResponseObject<T> {
    private String status;
    private String message;
    private Object data;
    private List<product> lst=null;    
    private Page<product> lst2=null;
    private int count=0;    
    private int total=0;


    public List<product> getLst() {
        return lst;
    }

    public void setLst(List<product> lst) {
        this.lst = lst;
    }

    

    public ResponseObject(int count,  List<product> lst) {
        this.lst=lst;
        this.count=count;
        //this.count=count;
    }
    public ResponseObject(int count,  List<product> lst, int total) {
        this.lst=lst;
        this.count=count;        
        this.total=total;

        //this.count=count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public ResponseObject(int count,  Page<product> lst2) {
        this.lst2= lst2;
        this.count=count;
        //this.count=count;
    }
    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        //this.count=count;
    }

    public Page<product> getLst2() {
        return lst2;
    }

    public void setLst2(Page<product> lst2) {
        this.lst2 = lst2;
    }

    public ResponseObject(List<product> result, int size) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        this.lst=result;
        this.count=size;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
        
}

