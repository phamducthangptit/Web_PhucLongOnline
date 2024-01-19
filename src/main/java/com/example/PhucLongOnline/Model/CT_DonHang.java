package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "ct_donhang")
public class CT_DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idctdonhang")
    private int idCTDonHang;

    @Column(name="giaban")
    private Double giaBan;

    @Column(name="soluong")
    private int soLuong;

    @ManyToOne()
    @JoinColumn(name = "iddonhang")
    private DonHang donHang;

    @ManyToOne()
    @JoinColumn(name = "idsanphamsize")
    private CT_SanPham_Size ctSanPhamSize;
    public int getIdCTDonHang() {
        return idCTDonHang;
    }

    public void setIdCTDonHang(int idCTDonHang) {
        this.idCTDonHang = idCTDonHang;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
