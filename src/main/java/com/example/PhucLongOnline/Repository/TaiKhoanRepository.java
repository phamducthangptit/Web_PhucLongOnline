package com.example.PhucLongOnline.Repository;


import com.example.PhucLongOnline.Model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,String> {
    TaiKhoan findByTenDangNhap(String tenDangNhap);

}
