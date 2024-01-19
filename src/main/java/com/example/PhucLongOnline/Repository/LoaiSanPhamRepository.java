package com.example.PhucLongOnline.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PhucLongOnline.Model.LoaiSanPham;
@Repository
public interface LoaiSanPhamRepository extends JpaRepository <LoaiSanPham, Integer>{
}
