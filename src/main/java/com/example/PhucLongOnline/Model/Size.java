package com.example.PhucLongOnline.Model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="size")
public class Size {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idsize")
    private int idSize;

    @Column(name = "tensize")
    private String tenSize;

    @OneToMany(mappedBy = "size")
    private List<CT_SanPham_Size> ctSanPhamSizes;


    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public List<CT_SanPham_Size> getCtSanPhamSizes() {
        return ctSanPhamSizes;
    }

    public void setCtSanPhamSizes(List<CT_SanPham_Size> ctSanPhamSizes) {
        this.ctSanPhamSizes = ctSanPhamSizes;
    }

}
