package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.DTO.KhuyenMaiDTO;
import com.example.webBanXeApi.models.KhuyenMai;
import com.example.webBanXeApi.service.IKhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/khuyenmai")
@CrossOrigin(origins = "*")
public class KhuyenMaiController {
    @Autowired
    private IKhuyenMaiService iKhuyenMaiService;

    // add km
    @PostMapping
    public KhuyenMai addKM(@RequestBody KhuyenMai km) {
        return iKhuyenMaiService.addKM(km);
    }

    @DeleteMapping("/{id}")
    public boolean deleteKM(@PathVariable long id) {
        return iKhuyenMaiService.deleteKM(id);
    }

    @GetMapping
    public ResponseEntity<KhuyenMaiDTO> getAllKM(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "0") int size) {
        List<KhuyenMai> result = iKhuyenMaiService.getAllKM(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                new KhuyenMaiDTO(result, iKhuyenMaiService.getAllKM(0,0).size(), page, size)
        );
    }

    @GetMapping("/hieuluc")
    public List<KhuyenMai> getKMAvailable () {
        return iKhuyenMaiService.getKMAvailable();
    }

    @GetMapping("/{id}")
    public KhuyenMai getKMById(@PathVariable long id) {
        return iKhuyenMaiService.getKMById(id);
    }

    @PutMapping("/{id}")
    public KhuyenMai updateKM(@PathVariable long id, @RequestBody KhuyenMai km) {
        return iKhuyenMaiService.updateKM(id, km);
    }
}
