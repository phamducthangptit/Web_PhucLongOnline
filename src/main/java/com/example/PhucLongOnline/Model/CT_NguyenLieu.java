package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name="ct_nguyenlieu")
public class CT_NguyenLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idctnguyenlieu")
    private int idCTNguyenLieu;

    @ManyToOne
    @JoinColumn(name = "idnguyenlieu")
    NguyenLieu nguyenLieu;

    @ManyToOne
    @JoinColumn(name = "manhacungcap")
    NhaCungCap nhaCungCap;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "ngay")
    private Date ngay;

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getIdCTNguyenLieu() {
        return idCTNguyenLieu;
    }

    public void setIdCTNguyenLieu(int idCTNguyenLieu) {
        this.idCTNguyenLieu = idCTNguyenLieu;
    }

    public NguyenLieu getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(NguyenLieu nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

}
