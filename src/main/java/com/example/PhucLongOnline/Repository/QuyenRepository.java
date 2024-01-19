package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenRepository extends JpaRepository<Quyen,Integer> {
    Quyen findById(int idQuyen);
}
