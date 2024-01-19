package com.example.PhucLongOnline.Model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="nhanvien")
public class NhanVien {
    @Id
    @Column(name = "manhanvien")
    private String maNhanVien;

    @Column(name="ho")
    private String ho;
    
    @Column(name="ten")
    private String ten;

    @Column(name="gioitinh")
    private String gioiTinh;

    @Column(name="diachi")
    private String diaChi;

    @Column(name="ngaysinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date NgaySinh;

    @Column(name="sdt")
    private String sDT;

    @Column(name="email")
    private String email;
    
    @Column(name="mst")
    @Nullable
    private String mST;

    @Column(name="hinhanh")
    private String hinhAnh;

    @Column(name="hopdong")
    private String hopDong;

    @Column(name="sobhxh")
    private String soBHXH;
    
    public NhanVien(){
        this.gioiTinh="Nam";
    }

    public NhanVien(String maNhanVien, String ho, String ten, String gioiTinh, String diaChi, Date ngaySinh, String sDT,
            String email, String mST, String hinhAnh, String hopDong, String soBHXH) {
        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        NgaySinh = ngaySinh;
        this.sDT = sDT;
        this.email = email;
        this.mST = mST;
        this.hinhAnh = hinhAnh;
        this.hopDong = hopDong;
        this.soBHXH = soBHXH;
    }

    public String getSoBHXH() {
        return soBHXH;
    }

    public void setSoBHXH(String soBHXH) {
        this.soBHXH = soBHXH;
    }

    public String getHopDong() {
        return hopDong;
    }

    public void setHopDong(String hopDong) {
        this.hopDong = hopDong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getmST() {
        return mST;
    }

    public void setmST(String mST) {
        this.mST = mST;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

}
