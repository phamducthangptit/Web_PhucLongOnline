package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.CT_DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CT_DonDatHangRepository extends JpaRepository<CT_DonDatHang,Integer> {

    @Query(value = "{call SP_GET_CT_DDH_X(:idDDNL, :ngay)}", nativeQuery = true)
    List<Map<String,Object>> getCT_DDH_X(@Param("idDDNL") int idDDNL,
                                         @Param("ngay") String ngay);
}
