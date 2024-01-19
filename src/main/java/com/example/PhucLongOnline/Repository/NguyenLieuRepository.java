package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.CT_NguyenLieu;
import com.example.PhucLongOnline.Model.NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguyenLieuRepository extends JpaRepository<NguyenLieu,Integer> {
    @Query(value = "{call SP_GET_NL_NCC_X(:maNCC)}", nativeQuery = true)
    List<NguyenLieu> getNL_NCC_X(@Param("maNCC") String maNCC);

    @Query(value = "{call SP_INSERT_DDNL(:ngay, :manv)}", nativeQuery = true)
    int insetDDNL(@Param("ngay") String ngay,
                  @Param("manv") String manv
    );

    @Query(value = "{call SP_INSERT_CTDDH(:idDon, :idNL, :maNCC, :sl)}", nativeQuery = true)
    int insetCTDDH(@Param("idDon") int idDon,
                   @Param("idNL") int idNL,
                   @Param("maNCC") String maNCC,
                   @Param("sl") Double soLuong
    );

}
