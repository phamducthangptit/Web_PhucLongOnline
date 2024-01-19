package com.example.PhucLongOnline.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "ct_banggia")
public class CT_BangGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idctbanggia")
    private int idCTBangGia;

    @Column(name="gia")
    private Double gia;

    @ManyToOne()
    @JoinColumn(name = "idsanphamsize")
    private CT_SanPham_Size ctSanPhamSize;

    @ManyToOne()
    @JoinColumn(name = "idbanggia")
    private BangGia bangGia;

    public int getIdCTBangGia() {
        return idCTBangGia;
    }

    public void setIdCTBangGia(int idCTBangGia) {
        this.idCTBangGia = idCTBangGia;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;

    }

    public CT_SanPham_Size getCtSanPhamSize() {
        return ctSanPhamSize;
    }

    public void setCtSanPhamSize(CT_SanPham_Size ctSanPhamSize) {
        this.ctSanPhamSize = ctSanPhamSize;
    }


    public BangGia getBangGia() {
        return bangGia;
    }

    public void setBangGia(BangGia bangGia) {
        this.bangGia = bangGia;
    }

}
