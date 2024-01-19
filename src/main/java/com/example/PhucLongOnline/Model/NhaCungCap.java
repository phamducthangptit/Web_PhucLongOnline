package com.example.PhucLongOnline.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="nhacungcap")
public class NhaCungCap {
    @Id
    @Column(name="manhacungcap")
    private String maNhaCungCap;
    
    @Column(name="tennhacungcap")
    private String tenNhaCungCap;

    public NhaCungCap(){
        
    }

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

}
