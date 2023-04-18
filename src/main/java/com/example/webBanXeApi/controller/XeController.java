package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.Xe;
import com.example.webBanXeApi.service.IXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/xe")
public class XeController {

    @Autowired
    private IXeService iXeService;

    // API add xe
    // http://localhost:8080/api/v1/xe/add
    @PostMapping("/add")
    public Xe addXe(@RequestBody Xe xe){
        return iXeService.addXe(xe);
    }

    // API update xe
    // http://localhost:8080/api/v1/xe/update?id=2
    @PutMapping("/update")
    public Xe updateXe(@RequestParam long id, @RequestBody Xe xe){
        return iXeService.updateXe(id, xe);
    }

    // API lấy ra tất cả xe
    // http://localhost:8080/api/v1/xe/all
    @GetMapping("/all")
    public ResponseEntity<XeDto> getAllXe() {
        List<Xe> result= iXeService.getAllXe();
        return ResponseEntity.status(HttpStatus.OK).body(
                new XeDto(result, iXeService.getAllXe().size(), 0, result.size())
        );
    }

    //API lấy ra 1 xe theo id
    // http://localhost:8080/api/v1/xe?id=1
    @GetMapping("")
    public Xe getOneXeById(@RequestParam long id) {
        return iXeService.getOneXeById(id);
    }

    // API xóa một xe theo id
    // http://localhost:8080/api/v1/xe/delete?id=1
    @DeleteMapping("/delete")
    public boolean deleteXe(@RequestParam long id){
           return iXeService.deleteXe(id);
    }

    // API lấy xe theo trang
    // http://localhost:8080/api/v1/xe/page?page=0&size=2
    @GetMapping("/page")
    public ResponseEntity<XeDto> getAllXeByPage(@RequestParam int page, @RequestParam int size){
        List<Xe> results = iXeService.getAllXeByPage(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                new XeDto(results, iXeService.getAllXe().size(), page, size)
        );
    }

    @GetMapping("/find")
    public ResponseEntity<XeDto> findXeByTenAndThuongHieu(@RequestParam(required = false) String ten,
                                                 @RequestParam(required = false) String thuongHieu,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "0") int size)
    {
        List<Xe> results = iXeService.getXeByNameAndBranch(ten, thuongHieu, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                new XeDto(results, iXeService.getAllXe().size(), page, size)
        );
    }
}
