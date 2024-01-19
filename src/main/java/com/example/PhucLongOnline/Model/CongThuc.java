package com.example.PhucLongOnline.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="congthuc")
public class CongThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcongthuc")
    private int idCongThuc;

    @ManyToOne
	@JoinColumn(name="idsanphamsize")
    private CT_SanPham_Size ctSanPhamSize;

    @ManyToOne
	@JoinColumn(name="idnguyenlieu")
    private NguyenLieu nguyenLieu;

    @Column(name="soluong")
    private float soLuong;

    @Column(name="donvi")
    private String donVi;

    public CongThuc(){}

    public CongThuc(int idCongThuc, CT_SanPham_Size ctSanPhamSize, NguyenLieu nguyenLieu, float soLuong, String donVi) {
        this.idCongThuc = idCongThuc;
        this.ctSanPhamSize = ctSanPhamSize;
        this.nguyenLieu = nguyenLieu;
        this.soLuong = soLuong;
        this.donVi = donVi;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public float getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(float soLuong) {
        this.soLuong = soLuong;
    }

    public NguyenLieu getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(NguyenLieu nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public CT_SanPham_Size getCtSanPhamSize() {
        return ctSanPhamSize;
    }

    public void setCtSanPhamSize(CT_SanPham_Size ctSanPhamSize) {
        this.ctSanPhamSize = ctSanPhamSize;
    }

    public int getIdCongThuc() {
        return idCongThuc;
    }

    public void setIdCongThuc(int idCongThuc) {
        this.idCongThuc = idCongThuc;
    }
    
}
