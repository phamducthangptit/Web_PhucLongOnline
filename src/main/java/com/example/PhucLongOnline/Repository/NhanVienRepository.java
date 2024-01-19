package com.example.PhucLongOnline.Repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.PhucLongOnline.Model.NhanVien;

public interface NhanVienRepository extends JpaRepository <NhanVien, String>{
    @Query(value = "SELECT dbo.GEN_NV()", nativeQuery = true)
    public String getNewMaNhanVien();
}
