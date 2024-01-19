package com.example.PhucLongOnline.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "congthuc")
public class CongThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idcongthuc")
    private int idCongThuc;

    @Column(name = "soluong")
    private Double soLuong;

    @Column(name = "donvi")
    private String donVi;

    @ManyToOne()
    @JoinColumn(name = "idnguyenlieu")
    private NguyenLieu nguyenLieu;

    @ManyToOne()
    @JoinColumn(name = "idsanphamsize")
    private CT_SanPham_Size ctSanPhamSize;

    public int getIdCongThuc() {
        return idCongThuc;
    }

    public void setIdCongThuc(int idCongThuc) {
        this.idCongThuc = idCongThuc;
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


    public NguyenLieu getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(NguyenLieu nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public CT_SanPham_Size getCtSanPhamSize() {
        return ctSanPhamSize;
    }

    public void setCtSanPhamSize(CT_SanPham_Size ctSanPhamSize) {
        this.ctSanPhamSize = ctSanPhamSize;
    }

}
