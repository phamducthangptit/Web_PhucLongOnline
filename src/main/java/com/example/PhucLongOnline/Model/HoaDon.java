package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name="hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idhoadon")
    private int idHoaDon;

    @Column(name="ngaylap")
    private Date ngayLap;

    @Column(name="tonghoadon")
    private Double tongHoaDon;

    @Column(name="diachi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name="thanhtoanonline")
    private Integer thanhToanOnline;

    @OneToOne()
    @JoinColumn(name = "iddonhang")
    private DonHang donHang;
    public HoaDon ()
    {

    }

    public HoaDon(int idHoaDon, Date ngayLap, Double tongHoaDon, String diaChi, String sdt, Integer thanhToanOnline, DonHang donHang) {
        this.idHoaDon = idHoaDon;
        this.ngayLap = ngayLap;
        this.tongHoaDon = tongHoaDon;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.thanhToanOnline = thanhToanOnline;
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

    public Double getTongHoaDon() {
        return tongHoaDon;
    }

    public void setTongHoaDon(Double tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getThanhToanOnline() {
        return thanhToanOnline;
    }

    public void setThanhToanOnline(Integer thanhToanOnline) {
        this.thanhToanOnline = thanhToanOnline;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }
}
