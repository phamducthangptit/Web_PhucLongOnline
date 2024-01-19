package com.example.PhucLongOnline.Model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="banggia")
public class BangGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idbanggia")
    private int idBangGia;

    @Column(name="tenloaigia")
    private String tenLoaiGia;

    @Column(name="ngayapdung")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayApDung;

    @OneToMany(mappedBy = "bangGia",cascade = CascadeType.REMOVE)
    private List<CT_BangGia> ctBangGiaList;

    public BangGia(){}

    public BangGia(int idBangGia, String tenLoaiGia, Date ngayApDung) {
        this.idBangGia = idBangGia;
        this.tenLoaiGia = tenLoaiGia;
        this.ngayApDung = ngayApDung;
    }

    public List<CT_BangGia> getCtBangGiaList() {
        return ctBangGiaList;
    }

    public Date getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(Date ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public String getTenLoaiGia() {
        return tenLoaiGia;
    }

    public void setTenLoaiGia(String tenLoaiGia) {
        this.tenLoaiGia = tenLoaiGia;
    }

    public int getIdBangGia() {
        return idBangGia;
    }

    public void setIdBangGia(int idBangGia) {
        this.idBangGia = idBangGia;
    }

}
