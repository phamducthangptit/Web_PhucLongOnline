package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "nguyenlieu")
public class NguyenLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idnguyenlieu")
    private int idNguyenLieu;

    @Column(name="tennguyenlieu")
    private String tenNguyenLieu;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name="soluong")
    private Double soLuong;

    @Column(name = "donvi")
    private String donVi;

    @OneToMany(mappedBy = "nguyenLieu")
    private List<CT_NguyenLieu> ctNguyenLieus;

    @OneToMany(mappedBy = "nguyenLieu")
    private List<CT_DonDatHang> ctDonDatHangs;

    @OneToMany(mappedBy = "nguyenLieu")
    private List<CongThuc> congThucs;

    public int getIdNguyenLieu() {
        return idNguyenLieu;
    }

    public void setIdNguyenLieu(int idNguyenLieu) {
        this.idNguyenLieu = idNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    @Override
    public String toString() {
        return "NguyenLieu{" +
                "idNguyenLieu=" + idNguyenLieu +
                ", tenNguyenLieu='" + tenNguyenLieu + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", soLuong=" + soLuong +
                ", donVi='" + donVi + '\'' +
                '}';
    }

    public List<CT_NguyenLieu> getCtNguyenLieus() {
        return ctNguyenLieus;
    }

    public void setCtNguyenLieus(List<CT_NguyenLieu> ctNguyenLieus) {
        this.ctNguyenLieus = ctNguyenLieus;
    }
}
