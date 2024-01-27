package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.CT_PhieuNhapHang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CT_PhieuNhapHangRepository extends JpaRepository<CT_PhieuNhapHang, Integer> {
    @Query(value = "{call SP_INSERT_CT_PHIEU_NHAP( :idCTDon, :sl, :gia, :idPN, :idNL)}", nativeQuery = true)
    String insertCTPN(
            @Param("idCTDon") int ngay,
            @Param("sl") Double sl,
            @Param("gia") Double gia,
            @Param("idPN") int idPN,
            @Param("idNL") int idNL
    );
}
