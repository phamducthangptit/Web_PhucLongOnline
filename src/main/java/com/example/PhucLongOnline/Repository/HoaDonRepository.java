package com.example.PhucLongOnline.Repository;

import com.example.PhucLongOnline.Model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Integer> {
    List<HoaDon> findAllByNgayLap(Date ngayLap);
    List<HoaDon> findAllByNgayLapBetween(Date startDate, Date endDate);
    @Query("SELECT hd FROM HoaDon hd WHERE YEAR(hd.ngayLap) = :nam")
    List<HoaDon> findAllByNam(@Param("nam") int nam);

}
