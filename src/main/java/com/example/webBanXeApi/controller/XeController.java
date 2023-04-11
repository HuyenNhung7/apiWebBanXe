package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.Xe;
import com.example.webBanXeApi.service.IXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/xe")
public class XeController {

    @Autowired
    private IXeService iXeService;

    // API add xe
    @PostMapping("/add")
    public Xe addXe(@RequestBody Xe xe){
        return iXeService.addXe(xe);
    }

    // API update xe
    @PutMapping("/update")
    public Xe updateXe(@RequestParam long id, @RequestBody Xe xe){
        return iXeService.updateXe(id, xe);
    }

    // API lấy ra tất cả xe
    @GetMapping("all")
    public List<Xe> getAllXe() {
        return iXeService.getAllXe();
    }

    //API lấy ra 1 xe theo id
    @GetMapping("")
    public Optional<Xe> getOneXeById(@RequestParam long id) {
        return iXeService.getOneXeById(id);
    }

    @DeleteMapping("/delete")
    public boolean deleteXe(@RequestParam long id){
        return iXeService.deleteXe(id);
    }

    // API lấy xe theo trang

}
