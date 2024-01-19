package com.example.PhucLongOnline.Model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idvoucher")
    private int idVoucher;

    @Column(name="noidung")
    private String noiDung;

    @Column(name="ngayapdung")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayApDung;
    
    @Column(name="ngayhethan")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayHetHan;

    @Column(name="phantramgiam")
    private float phanTramGiam;

    @Column(name="tiengiam")
    private float tienGiam;

    public Voucher(){
        
    }

    public Voucher(int idVoucher, String noiDung, Date ngayApDung, Date ngayHetHan, float phanTramGiam,
            float tienGiam) {
        this.idVoucher = idVoucher;
        this.noiDung = noiDung;
        this.ngayApDung = ngayApDung;
        this.ngayHetHan = ngayHetHan;
        this.phanTramGiam = phanTramGiam;
        this.tienGiam = tienGiam;
    }

    public float getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(float tienGiam) {
        this.tienGiam = tienGiam;
    }

    public float getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(float phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public Date getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(Date ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    
}
