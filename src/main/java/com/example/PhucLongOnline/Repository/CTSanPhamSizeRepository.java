package com.example.PhucLongOnline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PhucLongOnline.Model.CT_SanPham_Size;

public interface CTSanPhamSizeRepository extends JpaRepository <CT_SanPham_Size, Integer>{
    @Query(value = "{call SP_FIND_SP (:idsanpham)}", nativeQuery = true)
    public List<Integer> findSP(@Param("idsanpham") int idSanPham);
}
