package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,String> {
    NhanVien findByMaNhanVien (String maNhanVien);
}
