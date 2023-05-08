package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.Xe;
import com.example.webBanXeApi.service.IXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/xe")
@CrossOrigin(origins = "*")
public class XeController {

    @Autowired
    private IXeService iXeService;

    @GetMapping
    public ResponseEntity<XeDto> findXeByTenAndThuongHieu(@RequestParam(required = false) String ten,
                                                          @RequestParam(required = false) String thuongHieu,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "100") int size)
    {
        List<Xe> results;
        if (ten == null && thuongHieu == null) {
            results = iXeService.getAllXeByPage(page, size);
        } else {
            results = iXeService.getXeByNameAndBranch(ten, thuongHieu, page, size);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new XeDto(results, iXeService.getAllXe().size(), page, size)
        );
    }

    // API add xe
    // http://localhost:8080/api/v1/xe/
    @PostMapping
    public Xe addXe(@RequestBody Xe xe){
        return iXeService.addXe(xe);
    }

    // API update xe
    // http://localhost:8080/api/v1/xe/1
    @PutMapping("/{id}")
    public Xe updateXe(@PathVariable long id, @RequestBody Xe xe){
        return iXeService.updateXe(id, xe);
    }

    //API lấy ra 1 xe theo id
    // http://localhost:8080/api/v1/xe/1
    @GetMapping("/{id}")
    public Xe getOneXeById(@PathVariable long id) {
        return iXeService.getOneXeById(id);
    }

    // API xóa một xe theo id
    // http://localhost:8080/api/v1/xe/1
    @DeleteMapping("/{id}")
    public boolean deleteXe(@PathVariable long id){
        return iXeService.deleteXe(id);
    }

    // API lấy xe theo trang
    // http://localhost:8080/api/v1/xe/page?page=0&size=2
    @GetMapping("/page")
    public ResponseEntity<XeDto> getAllXeByPage(@RequestParam int page, @RequestParam int size) {
        List<Xe> results = iXeService.getAllXeByPage(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                new XeDto(results, iXeService.getAllXe().size(), page, size)
        );
    }

    @GetMapping("/dexuat")
    public ResponseEntity<XeDto> getXeByDeXuat() {
        List<Xe> result= iXeService.getXeByDeXuat();
        return ResponseEntity.status(HttpStatus.OK).body(
                new XeDto(result, iXeService.getXeByDeXuat().size(), 0, result.size())
        );
    }
}

