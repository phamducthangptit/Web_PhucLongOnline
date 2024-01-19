package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ct_dondathang")
public class CT_DonDatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idctdondathang")
    private int idCTDonDatHang;

    @Column(name = "soluong")
    private Double soLuong;

    @ManyToOne()
    @JoinColumn(name = "iddondatnguyenlieu")
    private DonDatNguyenLieu donDatNguyenLieu;

    @ManyToOne()
    @JoinColumn(name = "idnguyenlieu")
    private NguyenLieu nguyenLieu;

    @ManyToOne()
    @JoinColumn(name = "manhacungcap")
    private NhaCungCap nhaCungCap;

    @OneToMany(mappedBy = "ctDonDatHang")
    private List<CT_PhieuNhapHang> ctPhieuNhapHangs;

    @ManyToOne()
    @JoinColumn(name = "idsanphamsize")
    private CT_SanPham_Size ctSanPhamSize;

    @ManyToOne()
    @JoinColumn(name = "iddonhang")
    private DonHang donHang;

    public int getIdCTDonDatHang() {
        return idCTDonDatHang;
    }

    public void setIdCTDonDatHang(int idCTDonDatHang) {
        this.idCTDonDatHang = idCTDonDatHang;
    }

    public Double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public DonDatNguyenLieu getDonDatNguyenLieu() {
        return donDatNguyenLieu;
    }

    public void setDonDatNguyenLieu(DonDatNguyenLieu donDatNguyenLieu) {
        this.donDatNguyenLieu = donDatNguyenLieu;
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

    public List<CT_PhieuNhapHang> getCtPhieuNhapHangs() {
        return ctPhieuNhapHangs;
    }

    public void setCtPhieuNhapHangs(List<CT_PhieuNhapHang> ctPhieuNhapHangs) {
        this.ctPhieuNhapHangs = ctPhieuNhapHangs;
    }

    public CT_SanPham_Size getCtSanPhamSize() {
        return ctSanPhamSize;
    }

    public void setCtSanPhamSize(CT_SanPham_Size ctSanPhamSize) {
        this.ctSanPhamSize = ctSanPhamSize;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }
}
