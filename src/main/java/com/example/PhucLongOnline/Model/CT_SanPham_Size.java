package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ct_sanpham_size")
public class CT_SanPham_Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idsanphamsize")
    private int idSanPhamSize;

    @Column(name="giahienthoi")
    private Double giaHienThoi;

    @OneToMany(mappedBy = "ctSanPhamSize")
    private List<CongThuc> congThucs;

    @OneToMany(mappedBy = "ctSanPhamSize")
    private List<CT_BangGia> ctBangGias;

    @ManyToOne()
    @JoinColumn(name = "idsanpham")
    private SanPham sanPham ;

    @ManyToOne()
    @JoinColumn(name = "idsize")
    private Size size;

    @OneToMany(mappedBy = "ctSanPhamSize")
    private List<GioHang> gioHangs;

    @OneToMany(mappedBy = "ctSanPhamSize")
    private List<CT_DonHang> ctDonHangs;

    public int getIdSanPhamSize() {
        return idSanPhamSize;
    }

    public void setIdSanPhamSize(int idSanPhamSize) {
        this.idSanPhamSize = idSanPhamSize;
    }

    public Double getGiaHienThoi() {
        return giaHienThoi;
    }

    public void setGiaHienThoi(Double giaHienThoi) {
        this.giaHienThoi = giaHienThoi;
    }

    public List<CongThuc> getCongThucs() {
        return congThucs;
    }

    public void setCongThucs(List<CongThuc> congThucs) {
        this.congThucs = congThucs;
    }

    public List<CT_BangGia> getCtBangGias() {
        return ctBangGias;
    }

    public void setCtBangGias(List<CT_BangGia> ctBangGias) {
        this.ctBangGias = ctBangGias;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<GioHang> getGioHangs() {
        return gioHangs;
    }

    public void setGioHangs(List<GioHang> gioHangs) {
        this.gioHangs = gioHangs;
    }

    public List<CT_DonHang> getCtDonHangs() {
        return ctDonHangs;
    }

    public void setCtDonHangs(List<CT_DonHang> ctDonHangs) {
        this.ctDonHangs = ctDonHangs;
    }
}
