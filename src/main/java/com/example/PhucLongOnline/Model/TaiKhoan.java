package com.example.PhucLongOnline.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity

@Table(name="taikhoan")

public class TaiKhoan {

    @Id
    @Column(name = "tendangnhap")
    private String tenDangNhap;


    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "trangthai")
    private int trangThai;

    public TaiKhoan(String tenDangNhap, String matKhau, int trangThai, Quyen quyen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.quyen = quyen;
    }

    @ManyToOne()
    @JoinColumn(name = "idquyen")
    private Quyen quyen;

    @OneToOne(mappedBy = "taiKhoan")
    private NhanVien nhanVien;

    @OneToOne(mappedBy = "taiKhoan")
    private KhachHang khachHang;

    @OneToMany(mappedBy = "taiKhoan")
    private List<DonHang> donHangs;

    @OneToMany(mappedBy = "taiKhoan")
    private List<GioHang> gioHangs;
    public TaiKhoan()
    {

    }

    public TaiKhoan(String tenDangNhap, String matKhau, int trangThai, Quyen quyen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.quyen = quyen;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Quyen getQuyen() {
        return quyen;
    }

    public void setQuyen(Quyen quyen) {
        this.quyen = quyen;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public List<DonHang> getDonHangs() {
        return donHangs;
    }

    public void setDonHangs(List<DonHang> donHangs) {
        this.donHangs = donHangs;
    }

    public List<GioHang> getGioHangs() {
        return gioHangs;
    }

    public void setGioHangs(List<GioHang> gioHangs) {
        this.gioHangs = gioHangs;
    }

}
