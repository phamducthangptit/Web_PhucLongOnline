package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="donhang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idctdonhang")
    private int idCTDonHang;

    @Column(name="trangthaidonhang")
    private String trangThaiDonHang;

    @Column(name="ngaylap")
    private Date ngayLap;

    @ManyToOne()
    @JoinColumn(name = "tendangnhap")
    private TaiKhoan taiKhoan;

    @OneToOne()
    @JoinColumn(name = "idhoadon")
    private HoaDon hoaDon;

    public int getIdCTDonHang() {
        return idCTDonHang;
    }

    public void setIdCTDonHang(int idCTDonHang) {
        this.idCTDonHang = idCTDonHang;
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
