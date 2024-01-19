package com.example.PhucLongOnline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DatHangController {
    @GetMapping("index")
    public String index(){
        return "index.html";
    }

    @GetMapping("san-pham")
    public String SanPham(){
        return "sanpham.html";
    }

    @GetMapping("gio-hang")
    public String GioHang(){
        return "giohang.html";
    }
}
