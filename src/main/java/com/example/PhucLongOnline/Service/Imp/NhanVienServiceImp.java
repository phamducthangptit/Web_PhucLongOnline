package com.example.PhucLongOnline.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PhucLongOnline.Repository.NhanVienRepository;
import com.example.PhucLongOnline.Service.NhanVienService;

@Service
public class NhanVienServiceImp implements NhanVienService {
    @Autowired
    NhanVienRepository nhanVienReponsitory;


    @Override
    public String getNewMaNhanVien() {
        // TODO Auto-generated method stub
        return nhanVienReponsitory.getNewMaNhanVien();
    }
}
