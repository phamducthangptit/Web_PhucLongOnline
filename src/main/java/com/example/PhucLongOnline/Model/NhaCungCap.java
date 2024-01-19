package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {
    @Id
    @Column(name = "manhacungcap")
    private String maNhaCungCap;

    @Column(name = "tennhacungcap")
    private String tenNhaCungCap;

    @OneToMany(mappedBy = "nhaCungCap")
    private List<CT_NguyenLieu> ctNguyenLieus;

    @OneToMany(mappedBy = "nhaCungCap")
    private List<CT_DonDatHang> ctDonDatHangs;

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public List<CT_NguyenLieu> getCtNguyenLieus() {
        return ctNguyenLieus;
    }

    public void setCtNguyenLieus(List<CT_NguyenLieu> ctNguyenLieus) {
        this.ctNguyenLieus = ctNguyenLieus;
    }
}
