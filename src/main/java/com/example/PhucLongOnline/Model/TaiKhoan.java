package com.example.PhucLongOnline.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="taikhoan")
public class TaiKhoan {
    @Id
    @Column(name="tendangnhap")
    private String tenDangNhap;
    
    @Column(name="matkhau")
    private String matKhau;

    @Column(name="trangthai")
    private int trangThai;

    @ManyToOne
	@JoinColumn(name="idquyen")
    private Quyen quyen;

    public TaiKhoan(){
        
    }

    public TaiKhoan(String tenDangNhap, String matKhau, int trangThai, Quyen quyen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.quyen = quyen;
    }

    public Quyen getQuyen() {
        return quyen;
    }

    public void setQuyen(Quyen quyen) {
        this.quyen = quyen;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
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

    
}
