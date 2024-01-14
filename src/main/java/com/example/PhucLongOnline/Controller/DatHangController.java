package com.example.PhucLongOnline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatHangController {
    @GetMapping("index")
    public String index(){
        return "index.html";
    }
}
