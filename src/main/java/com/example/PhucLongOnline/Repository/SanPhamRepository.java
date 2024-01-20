package com.example.PhucLongOnline.Repository;



import com.example.PhucLongOnline.Model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SanPhamRepository extends JpaRepository<SanPham,Integer> {
    @Query(value = "{call sp_tim_top_3()}", nativeQuery = true)
    List<SanPham> findTop3();

}
