package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "taikhoan")
public class TaiKhoan {
    @Id
    @Column(name = "tendangnhap")
    private String tenDangNhap;
    @Column(name = "matkhau")
    private String matKhau;
    @Column(name = "trangthai")
    private int trangThai;
    @ManyToOne
    @JoinColumn(name = "idquyen")
    private Quyen quyen;
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private List<DonHang> donHang;
    @OneToOne(mappedBy = "taiKhoan",  cascade = CascadeType.ALL)
    private NhanVien nhanVien;
    @OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private KhachHang khachHang;
    public TaiKhoan()
    {

    }
    public TaiKhoan(String tenDangNhap, String matKhau, int trangThai) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
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

    public List<DonHang> getDonHang() {
        return donHang;
    }

    public void setDonHang(List<DonHang> donHang) {
        this.donHang = donHang;
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
}
