package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhoadon")
    private int idHoaDon;
    @Column (name = "ngaylap")
    private Date ngayLap;
    @Column (name = "tonghoadon")
    private Float tongHoaDon;
    @Column(name = "diachinhanhang")
    private String diaChiNhanHang;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddonhang")
    private DonHang donHang;
    public HoaDon ()
    {

    }

    public HoaDon(int idHoaDon, Date ngayLap, Float tongHoaDon, String diaChiNhanHang, DonHang donHang) {
        this.idHoaDon = idHoaDon;
        this.ngayLap = ngayLap;
        this.tongHoaDon = tongHoaDon;
        this.diaChiNhanHang = diaChiNhanHang;
        this.donHang = donHang;
    }


    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Float getTongHoaDon() {
        return tongHoaDon;
    }

    public void setTongHoaDon(Float tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    public String getDiaChiNhanHang() {
        return diaChiNhanHang;
    }

    public void setDiaChiNhanHang(String diaChiNhanHang) {
        this.diaChiNhanHang = diaChiNhanHang;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }
}
