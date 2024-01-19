package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

@Entity
@Table(name="giohang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idgiohang")
    private int idGioHang;

    @Column(name="soluong")
    private int soLuong;

    @ManyToOne()
    @JoinColumn(name = "idsanphamsize")
    private CT_SanPham_Size ctSanPhamSize;

    @ManyToOne()
    @JoinColumn(name = "tendangnhap")
    private TaiKhoan taiKhoan;

    public int getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(int idGioHang) {
        this.idGioHang = idGioHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public CT_SanPham_Size getCtSanPhamSize() {
        return ctSanPhamSize;
    }

    public void setCtSanPhamSize(CT_SanPham_Size ctSanPhamSize) {
        this.ctSanPhamSize = ctSanPhamSize;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
