package com.example.PhucLongOnline.Model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="loaisanpham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idloaisanpham")
    private int idLoaiSanPham;

    @Column( name = "tenloai")
    private String tenLoai;

    @OneToMany(mappedBy = "loaiSanPham")
    private List<SanPham> sanPhams;


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


    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

}
