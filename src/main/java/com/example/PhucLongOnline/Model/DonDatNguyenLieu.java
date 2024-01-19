package com.example.PhucLongOnline.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dondatnguyenlieu")
public class DonDatNguyenLieu {
    @Id
    @Column(name= "iddondatnguyenlieu")
    private int idDonDatNguyenLieu;

    @Column(name = "ngaydat")
    private Date ngayDat;

    @ManyToOne()
    @JoinColumn(name = "manhanvien")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "donDatNguyenLieu")
    private List<CT_DonDatHang> ctDonDatHangs;

    public int getIdDonDatNguyenLieu() {
        return idDonDatNguyenLieu;
    }

    public void setIdDonDatNguyenLieu(int idDonDatNguyenLieu) {
        this.idDonDatNguyenLieu = idDonDatNguyenLieu;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public List<CT_DonDatHang> getCtDonDatHangs() {
        return ctDonDatHangs;
    }

    public void setCtDonDatHangs(List<CT_DonDatHang> ctDonDatHangs) {
        this.ctDonDatHangs = ctDonDatHangs;
    }
}
