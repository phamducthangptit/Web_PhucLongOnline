package com.example.PhucLongOnline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DatHangController {
    @GetMapping("index")
    public String index(){
        return "index.html";
    }

//    @GetMapping("san-pham")
//    public String SanPham(){
//        return "sanpham.html";
//    }

    @GetMapping("san-pham/{loaisanpham}")
    public String LoaiSanPham(@PathVariable("loaisanpham") String loaiSanPham, Model model){
//        model.addAttribute("idLoaiSanPham", id);
        if(loaiSanPham.equals("tat-ca")){ // get tat ca san pham
            model.addAttribute("loaisanpham", "tatca");
        } else { // get san pham theo loai
            model.addAttribute("loaisanpham", loaiSanPham);
        }
        return "sanpham.html";
    }

    @GetMapping("gio-hang")
    public String GioHang(){
        return "giohang.html";
    }

    @GetMapping("don-hang")
    public String DonHang(){
        return "donhang.html";
    }

    @GetMapping("lich-su-don-hang")
    public String LichSuDonHang(){
        return "lichsudonhang.html";
    }

    @GetMapping("chi-tiet-lich-su-don-hang/{id}")
    public String ChiTietDonHang(@PathVariable("id") int id, Model model){
        model.addAttribute("idDonHang", id);
        return "chitietlichsudonhang.html";
    }
}
