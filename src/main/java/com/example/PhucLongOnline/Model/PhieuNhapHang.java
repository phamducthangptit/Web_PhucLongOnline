package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "phieunhaphang")
public class PhieuNhapHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idphieunhaphang")
    private int idPhieuNhapHang;

    @Column(name = "ngaylapphieunhap")
    private Date ngayLapPhieuNhap;

    @ManyToOne()
    @JoinColumn(name = "manhanvien")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "phieuNhapHang")
    private List<CT_PhieuNhapHang> ctPhieuNhapHangs;

    public int getIdPhieuNhapHang() {
        return idPhieuNhapHang;
    }

    public void setIdPhieuNhapHang(int idPhieuNhapHang) {
        this.idPhieuNhapHang = idPhieuNhapHang;
    }

    public Date getNgayLapPhieuNhap() {
        return ngayLapPhieuNhap;
    }

    public void setNgayLapPhieuNhap(Date ngayLapPhieuNhap) {
        this.ngayLapPhieuNhap = ngayLapPhieuNhap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public List<CT_PhieuNhapHang> getCtPhieuNhapHangs() {
        return ctPhieuNhapHangs;
    }

    public void setCtPhieuNhapHangs(List<CT_PhieuNhapHang> ctPhieuNhapHangs) {
        this.ctPhieuNhapHangs = ctPhieuNhapHangs;
    }
}
