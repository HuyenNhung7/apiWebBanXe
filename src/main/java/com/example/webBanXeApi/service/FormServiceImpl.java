package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.Form;
import com.example.webBanXeApi.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormServiceImpl implements IFormService{
    @Autowired
    private FormRepository repository;

    //Hàm thêm form
    @Override
    public Form addForm(Form form) {
        return repository.save(form);
    }

    //Hàm xóa form
    @Override
    public boolean deleteForm(Long id) {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    //Ham xoa tat ca form
    @Override
    public boolean deleteAllForm(){
        repository.deleteAll();
        if(repository.findAll().size() == 0)
            return true;
        return false;
    }

    //Hàm phân trang cho form
    @Override
    public List<Form> formPerPage(int currentPage, int size, List<Form> lstForm) {
        int firstFormIndex = currentPage * size;
        int lastFormIndex = firstFormIndex + size;
        if(lastFormIndex > lstForm.size())
            lastFormIndex = lstForm.size();
        List<Form> forms = lstForm.subList(firstFormIndex, lastFormIndex);
        return forms;
    }

    //Hàm lấy tất cả form
    @Override
    public List<Form> getAllForm() {
        return repository.findAll();
    }

    //Hàm lấy tất cả các form và phân trang
    @Override
    public List<Form> getAllFormPerPage(int currentPage, int size){
        List<Form> lstForm = formPerPage(currentPage, size, repository.findAll());
        return lstForm;
    }

    // Hàm tìm form theo id
    @Override
    public Form findFormById(long id) {
        return repository.findById(id).orElse(null);
    }
}
