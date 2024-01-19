package com.example.PhucLongOnline.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="ct_sanpham_size")
public class CT_SanPham_Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idsanphamsize")
    private int idSanPhamSize;

    @ManyToOne
	@JoinColumn(name="idsize")
    private Size size;
    
    @ManyToOne
	@JoinColumn(name="idsanpham")
    private SanPham sanPham;
    
    @Column(name="giahienthoi")
    private float giaHienThoi;

    @OneToMany(mappedBy = "ctSanPhamSize")
    private List<CongThuc> congThuc;

    public List<CongThuc> getCongThuc() {
        return congThuc;
    }

    public CT_SanPham_Size(){
        
    }

    public CT_SanPham_Size(int idSanPhamSize, Size size, SanPham sanPham, float giaHienThoi) {
        this.idSanPhamSize = idSanPhamSize;
        this.size = size;
        this.sanPham = sanPham;
        this.giaHienThoi = giaHienThoi;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    
    
    public float getGiaHienThoi() {
        return giaHienThoi;
    }

    public void setGiaHienThoi(float giaHienThoi) {
        this.giaHienThoi = giaHienThoi;
    }

    public int getIdSanPhamSize() {
        return idSanPhamSize;
    }

    public void setIdSanPhamSize(int idSanPhamSize) {
        this.idSanPhamSize = idSanPhamSize;
    }

    

}
