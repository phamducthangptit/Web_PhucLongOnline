package com.example.PhucLongOnline.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="nguyenlieu")
public class NguyenLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idnguyenlieu")
    private int idNguyenLieu;

    @Column(name="tennguyenlieu")
    private String tenNguyenLieu;

    @Column(name="hinhanh")
    private String hinhAnh;
    
    @Column(name="soluong")
    private Float soLuong;
    public NguyenLieu(){
        
    }

    public NguyenLieu(int idNguyenLieu, String tenNguyenLieu, String hinhAnh, Float soLuong) {
        this.idNguyenLieu = idNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
    }

    public Float getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Float soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public int getIdNguyenLieu() {
        return idNguyenLieu;
    }

    public void setIdNguyenLieu(int idNguyenLieu) {
        this.idNguyenLieu = idNguyenLieu;
    }



}
