package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.PhieuNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhieuNhapHangRepsitory extends JpaRepository<PhieuNhapHang, Integer> {

    @Query(value = "{call SP_INSERT_PHIEU_NHAP( :ngay, :manv)}", nativeQuery = true)
    int insertPN(
                   @Param("ngay") String ngay,
                   @Param("manv") String maNV
    );
}
