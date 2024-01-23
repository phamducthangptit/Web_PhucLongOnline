package com.example.PhucLongOnline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DonHangController {
    @GetMapping("/don-hang/{trang-thai}")
    public String DonHangChuaXacNhan(@PathVariable("trang-thai") String trangThai, Model model){
        System.out.println(trangThai);
        if(trangThai.equals("chua-xac-nhan")){
            model.addAttribute("trangthaidonhang", "Chưa xác nhận");
        } else if(trangThai.equals("da-xac-nhan")){
            model.addAttribute("trangthaidonhang", "Đã xác nhận");
        } else if(trangThai.equals("da-hoan-thanh")){
            model.addAttribute("trangthaidonhang", "Đã hoàn thành");
        }
        return "danhsachdonhang.html";
    }

    @GetMapping("/don-hang/chi-tiet-don-hang/{id}")
    public String ChiTietDonHang(@PathVariable("id") int id){
        return "redirect:/chi-tiet-lich-su-don-hang/" + id;
    }
}
