package com.example.PhucLongOnline.Model;


import jakarta.persistence.*;

import java.sql.Date;

import java.util.List;

@Entity
@Table(name = "nhanvien")
public class NhanVien {
    @Id
    @Column(name = "manhanvien")
    private String maNhanVien;

    @Column(name="ho")
    private String ho;

    @Column(name ="ten")
    private String ten;

    @Column(name="gioitinh")
    private String gioiTinh;

    @Column(name="diachi")
    private String diaChi;

    @Column(name="ngaysinh")

    private Date ngaySinh;

    @Column(name = "sdt")
    private String sdt;

    @Column(name ="email")
    private String email;

    @Column(name = "mst")
    private String mst;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "hopdong")
    private String hopDong;

    @Column(name = "sobhxh")
    private String soBHXH;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manhanvien")
    private TaiKhoan taiKhoan;



    @OneToMany(mappedBy = "nhanVien")
    private List<PhieuNhapHang> phieuNhapHangs;

    @OneToMany(mappedBy = "nhanVien")
    private List<DonDatNguyenLieu> donDatNguyenLieus;
    public NhanVien(String maNhanVien, String ho, String ten, String gioiTinh, Date ngaySinh, String sdt, String email, String mst, String hinhAnh, String hopDong, String soBHXH) {

        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.mst = mst;
        this.hinhAnh = hinhAnh;
        this.hopDong = hopDong;
        this.soBHXH = soBHXH;
    }

    public NhanVien()
    {

    }
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getHopDong() {
        return hopDong;
    }

    public void setHopDong(String hopDong) {
        this.hopDong = hopDong;
    }

    public String getSoBHXH() {
        return soBHXH;
    }

    public void setSoBHXH(String soBHXH) {
        this.soBHXH = soBHXH;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public List<PhieuNhapHang> getPhieuNhapHangs() {
        return phieuNhapHangs;
    }

    public void setPhieuNhapHangs(List<PhieuNhapHang> phieuNhapHangs) {
        this.phieuNhapHangs = phieuNhapHangs;
    }

    public List<DonDatNguyenLieu> getDonDatNguyenLieus() {
        return donDatNguyenLieus;
    }

    public void setDonDatNguyenLieus(List<DonDatNguyenLieu> donDatNguyenLieus) {
        this.donDatNguyenLieus = donDatNguyenLieus;
    }

}
