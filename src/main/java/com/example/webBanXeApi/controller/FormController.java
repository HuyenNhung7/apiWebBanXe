package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.DTO.FormDto;
import com.example.webBanXeApi.models.Form;
import com.example.webBanXeApi.service.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/form")
@CrossOrigin(origins = "*")
public class FormController {
    @Autowired
    private IFormService iFormService;

//    @GetMapping("")
//    public String hello(){
//        return "Hello world!";
//    }

    // api thêm form
    // http://localhost:8080/api/v1/form/add
    @PostMapping
    public Form addForm(@RequestBody Form form) {
        return iFormService.addForm(form);
    }

    // api xoa form
    // http://localhost:8080/api/v1/form/delete?id=...
    @DeleteMapping("/{id}")
    public boolean deleteForm(@PathVariable long id) {
        return iFormService.deleteForm(id);
    }

    //api xoa tat ca form
    @DeleteMapping("/all")
    public boolean deleteAllForm(){
        return iFormService.deleteAllForm();
    }

    @GetMapping("/all")
    public ResponseEntity<FormDto> getAllForm() {
        List<Form> result = iFormService.getAllForm();
        return ResponseEntity.status(HttpStatus.OK).body(
                new FormDto(result, result.size(),0, result.size() )
        );
    }

    @GetMapping("")
    public ResponseEntity<FormDto> getAllFormPerPage(@RequestParam int page,
                                                     @RequestParam int size) {
        List<Form> result = iFormService.getAllFormPerPage(page, size);
        return ResponseEntity.ok(
                new FormDto(result, iFormService.getAllForm().size(), page, size)
        );
    }

    @GetMapping("/{id}")
    public Form findFormById (@PathVariable long id) {
        return iFormService.findFormById(id);
    }
}
