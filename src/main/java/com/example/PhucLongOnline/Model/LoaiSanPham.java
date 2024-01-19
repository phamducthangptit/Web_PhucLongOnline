package com.example.PhucLongOnline.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loaisanpham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idloaisanpham")
    private int idLoaiSanPham;

    @Column(name="tenloai")
    private String tenLoai;

    public LoaiSanPham(){
        
    }
    
    public LoaiSanPham(int idLoaiSanPham, String tenLoai) {
        this.idLoaiSanPham = idLoaiSanPham;
        this.tenLoai = tenLoai;
    }

    public int getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(int idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }


    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    
}
