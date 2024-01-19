package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="khachhang")
public class KhachHang {

    @Id
    @Column (name ="tendangnhap")
    private String tenDangNhap;
    @Column(name ="ho")
    private String ho;
    @Column(name="ten")
    private String ten;
    @Column(name = "gioitinh")
    private String gioiTinh;
    @Column(name ="diachi")
    private String diaChi;
    @Column(name="ngaysinh")
    private Date ngaySinh;
    @Column(name ="sdt")
    private String sdt;
    @Column(name = "email")
    private String email;
    @Column(name = "mst")
    private String mst;
    @Column(name ="hinhanh")
    private String hinhAnh;
    @Column(name = "diemtichluy")
    private Float diemTichLuy;
    @OneToOne()
    @JoinColumn(name = "tendangnhap")
    private TaiKhoan taiKhoan;

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Float getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(Float diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
