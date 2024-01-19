package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idsanpham")
    private int idSanPham;

    @Column(name = "tensanpham")
    private String tenSanPham;

    @Column(name="hinhanh")
    private String hinhAnh;

    @Column(name = "cachphache")
    private String cachPhaChe;

    @ManyToOne()
    @JoinColumn(name = "idloaisanpham")
    private LoaiSanPham loaiSanPham;
    @OneToMany(mappedBy = "sanPham")
    private List<CT_SanPham_Size> ctSanPhamSizes;

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getCachPhaChe() {
        return cachPhaChe;
    }

    public void setCachPhaChe(String cachPhaChe) {
        this.cachPhaChe = cachPhaChe;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public List<CT_SanPham_Size> getCtSanPhamSizes() {
        return ctSanPhamSizes;
    }

    public void setCtSanPhamSizes(List<CT_SanPham_Size> ctSanPhamSizes) {
        this.ctSanPhamSizes = ctSanPhamSizes;
    }
}
