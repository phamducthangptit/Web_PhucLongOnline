package com.example.PhucLongOnline.Repository;

import org.springframework.stereotype.Repository;
import com.example.PhucLongOnline.Database.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,String> {
    
}
