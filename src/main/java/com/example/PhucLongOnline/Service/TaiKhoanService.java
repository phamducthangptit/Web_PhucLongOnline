package com.example.PhucLongOnline.Service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PhucLongOnline.Database.TaiKhoan;
import com.example.PhucLongOnline.Repository.TaiKhoanRepository;

@Service
public class TaiKhoanService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public String customCheckCredentials(String username, String password) {
        // Thực hiện logic kiểm tra tùy chỉnh
        Optional<TaiKhoan> optionalTaikhoan = taiKhoanRepository.findById(username);
        if(optionalTaikhoan.isPresent() == false){
            return "Tài khoản không tồn tại";
        }
        else{
            TaiKhoan taikhoan = optionalTaikhoan.get();
            if(taikhoan.getMatkhau().equals(password)){
                return "ok";
            }
            else{
                return "Sai mật khẩu";
            }
        }
    }

    // Các phương thức khác nếu cần
}