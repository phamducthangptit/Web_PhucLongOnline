package com.example.PhucLongOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PhucLongOnline.Database.TaiKhoan;



@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,String> {
}
