package com.example.PhucLongOnline.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PhucLongOnline.Repository.CTSanPhamSizeRepository;
import com.example.PhucLongOnline.Service.CTSanPhamSizeService;

@Service
public class CTSanPhamSizeServiceImp implements CTSanPhamSizeService {
    @Autowired
    CTSanPhamSizeRepository ctSanPhamSizeReponsitory;

    public List<Integer> findSP(int idSanPham) {
        return ctSanPhamSizeReponsitory.findSP(idSanPham);
    }
}
