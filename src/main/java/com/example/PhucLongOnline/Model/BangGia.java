package com.example.PhucLongOnline.Model;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;


@Entity
@Table(name="banggia")
public class BangGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idbanggia")

    private int idBangGia;

    @Column(name="tenloaigia")
    private String tenLoaiGia;


    @Column(name = "ngayapdung")
    private Date ngayApDung;

    @OneToMany(mappedBy = "bangGia", cascade = CascadeType.ALL)
    private List<CT_BangGia> ctBangGias;

    public BangGia() {

    }

    public int getIdBangGia() {
        return idBangGia;
    }

    public void setIdBangGia(int idBangGia) {
        this.idBangGia = idBangGia;
    }


    public String getTenLoaiGia() {
        return tenLoaiGia;
    }

    public void setTenLoaiGia(String tenLoaiGia) {
        this.tenLoaiGia = tenLoaiGia;
    }

    public Date getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(Date ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public List<CT_BangGia> getCtBangGias() {
        return ctBangGias;
    }

    public void setCtBangGias(List<CT_BangGia> ctBangGias) {
        this.ctBangGias = ctBangGias;
    }

}
