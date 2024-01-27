package com.example.PhucLongOnline.Model;

public class CheckDon {

    private DonDatNguyenLieu donDatNguyenLieu;
    private  int check;
    private String ngayDat;
    public CheckDon(DonDatNguyenLieu donDatNguyenLieu, int check) {
        this.donDatNguyenLieu = donDatNguyenLieu;
        this.check = check;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        String day = ngayDat.split(" ")[0];
        String[] tmp = day.toString().split("-");
        String outputDay = tmp[2]+"-"+tmp[1]+"-"+tmp[0];
        this.ngayDat = outputDay;
    }

    public DonDatNguyenLieu getDonDatNguyenLieu() {
        return donDatNguyenLieu;
    }

    public void setDonDatNguyenLieu(DonDatNguyenLieu donDatNguyenLieu) {
        this.donDatNguyenLieu = donDatNguyenLieu;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
