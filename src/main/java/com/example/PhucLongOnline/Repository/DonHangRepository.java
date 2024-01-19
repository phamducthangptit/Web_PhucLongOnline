package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang,Integer> {

}
