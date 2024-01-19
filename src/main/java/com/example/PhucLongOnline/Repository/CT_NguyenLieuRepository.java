package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.CT_NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CT_NguyenLieuRepository extends JpaRepository<CT_NguyenLieu,Integer> {

    @Query(value = "{call SP_GET_NCC_NL(:idNL)}", nativeQuery = true)
    List<CT_NguyenLieu> getNCC(@Param("idNL") int idNL);

    @Query(value = "{call SP_GET_NL_NCC(:maNCC)}", nativeQuery = true)
    List<CT_NguyenLieu> getNL_NCC(@Param("maNCC") String maNCC);

    @Query(value = "{call SP_GET_CT_NL_NCC(:maNCC, :idNL)}", nativeQuery = true)
    List<CT_NguyenLieu> getCT_NL_NCC(@Param("maNCC") String maNCC
            ,@Param("idNL") int idNL);
    @Query(value = "{call SP_INSERT_CT_NL(:maNCC, :idNL, :gia, :ngay)}", nativeQuery = true)
    int insertCTNL(@Param("maNCC") String maNCC,
                    @Param("idNL") int idNL,
                    @Param("gia") Double gia,
                    @Param("ngay") String ngay
                    );

    @Query(value = "{call SP_UPDATE_CT_NL(:maNCC, :idNL, :gia, :ngay)}", nativeQuery = true)
    int updateCTNL(@Param("maNCC") String maNCC,
                   @Param("idNL") int idNL,
                   @Param("gia") Double gia,
                   @Param("ngay") String ngay
    );
}
