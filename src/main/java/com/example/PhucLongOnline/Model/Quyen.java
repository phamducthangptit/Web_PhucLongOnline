package com.example.PhucLongOnline.Model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "quyen")
public class Quyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idquyen")
    private int idQuyen;
    @Column(name = "chucvu")
    private String chucVu;

    @OneToMany(mappedBy = "quyen")
    private List<TaiKhoan> taiKhoan;
    public Quyen() {
    }


    public Quyen(int idQuyen, String chucVu) {
        this.idQuyen = idQuyen;
        this.chucVu = chucVu;


    }

    public int getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(int idQuyen) {
        this.idQuyen = idQuyen;

    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }


    public List<TaiKhoan> getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(List<TaiKhoan> taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

}
