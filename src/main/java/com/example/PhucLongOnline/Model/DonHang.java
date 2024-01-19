package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "donhang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddonhang")
    private int idDonHang;
    @Column(name = "trangthaidonhang")
    private String trangThaiDonHang;
    @Column(name = "ngaylap")
    private Date ngayLap;
    @ManyToOne
    @JoinColumn(name = "tendangnhap")
    private TaiKhoan taiKhoan;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="idhoadon")
    private HoaDon hoaDon;
    public DonHang()
    {

    }
    public DonHang(int idDonHang, String trangThaiDonHang, Date ngayLap, TaiKhoan taiKhoan, HoaDon hoaDon) {
        this.idDonHang = idDonHang;
        this.trangThaiDonHang = trangThaiDonHang;
        this.ngayLap = ngayLap;
        this.taiKhoan = taiKhoan;
        this.hoaDon = hoaDon;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }
}
