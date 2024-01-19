package com.example.PhucLongOnline.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PhucLongOnline.Model.Size;
@Repository
public interface SizeRepository  extends JpaRepository <Size, Integer>{
}
