package com.example.PhucLongOnline.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="sanpham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idsanpham")
    private int idSanPham;

    @Column(name="tensanpham")
    private String tenSanPham;

    @Column(name="hinhanh")
    private String hinhAnh;
    
    @Column(name="cachphache")
    private String cachPhaChe;

    @ManyToOne
	@JoinColumn(name="idloaisanpham")
    private LoaiSanPham loaiSanPham;

    @ManyToMany(cascade = { CascadeType.ALL })  
    @JoinTable(
            name = "ct_sanpham_size",
            joinColumns = { @JoinColumn(name = "idsanpham") },
            inverseJoinColumns = { @JoinColumn(name = "idsize") }
    )
    @JsonManagedReference
    private List<Size> sizeList;

    public List<Size> getSizeList() {
        return sizeList;
    }

    @OneToMany(mappedBy = "sanPham")
    private List<CT_SanPham_Size> ctSanPhamSizeList;

    


    public List<CT_SanPham_Size> getCtSanPhamSizeList() {
        return ctSanPhamSizeList;
    }

    public SanPham(){

    }

    public SanPham(int idSanPham,String tenSanPham, String hinhAnh, String cachPhaChe, LoaiSanPham loaiSanPham) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.cachPhaChe = cachPhaChe;
        this.loaiSanPham = loaiSanPham;
    }

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



    
}
