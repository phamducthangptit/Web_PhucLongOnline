package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "ct_phieunhaphang")
public class CT_PhieuNhapHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idctphieunhaphang")
    private int idCTPhieuNhapHang;

    @Column(name = "soluongnhap")
    private Double soLuongNhap;

    @Column(name="dongia")
    private Double donGia;

    @ManyToOne()
    @JoinColumn(name = "idphieunhaphang")
    private PhieuNhapHang phieuNhapHang;

    @ManyToOne()
    @JoinColumn(name = "idctdondathang")
    private CT_DonDatHang ctDonDatHang;

    public int getIdCTPhieuNhapHang() {
        return idCTPhieuNhapHang;
    }

    public void setIdCTPhieuNhapHang(int idCTPhieuNhapHang) {
        this.idCTPhieuNhapHang = idCTPhieuNhapHang;
    }

    public Double getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(Double soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public PhieuNhapHang getPhieuNhapHang() {
        return phieuNhapHang;
    }

    public void setPhieuNhapHang(PhieuNhapHang phieuNhapHang) {
        this.phieuNhapHang = phieuNhapHang;
    }

    public CT_DonDatHang getCtDonDatHang() {
        return ctDonDatHang;
    }

    public void setCtDonDatHang(CT_DonDatHang ctDonDatHang) {
        this.ctDonDatHang = ctDonDatHang;
    }
}
