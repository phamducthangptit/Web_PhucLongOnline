package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

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
    public String getGia1() {
        // Tạo một đối tượng NumberFormat với locale là "vi-VN"
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        // Định dạng số và in ra kết quả
        String formattedAmount = currencyFormatter.format(gia);
        return formattedAmount;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Date getNgay() {
        return ngay;
    }

    public String getNgay1() {
        String ngay1 = ngay.toString();
        String[] tmp = ngay1.toString().split("-");
        String outputDay = tmp[2]+"-"+tmp[1]+"-"+tmp[0];
        return outputDay;
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
