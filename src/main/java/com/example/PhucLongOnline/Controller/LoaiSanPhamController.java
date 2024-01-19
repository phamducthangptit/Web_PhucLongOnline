package com.example.PhucLongOnline.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RestController;

import com.example.PhucLongOnline.Model.LoaiSanPham;
import com.example.PhucLongOnline.Repository.LoaiSanPhamRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/loaisanpham")
public class LoaiSanPhamController {
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;
    private final String path="manage/";
    @GetMapping("/list")
    public String get(Model model){
        try{
            List<LoaiSanPham> list=loaiSanPhamRepository.findAll();
            LoaiSanPham loai=new LoaiSanPham();
            model.addAttribute("list", list);
            model.addAttribute("loai", loai);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch (Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"loaisanpham.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") int id, Model model) {
        try{
            List<LoaiSanPham> list=loaiSanPhamRepository.findAll();
            LoaiSanPham loai=loaiSanPhamRepository.getById(id);
            model.addAttribute("list", list);
            model.addAttribute("loai", loai);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"loaisanpham.html";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        try{
            
            System.out.print("id: ");
            System.out.println(id);
            LoaiSanPham loai=loaiSanPhamRepository.getById(id);
            loaiSanPhamRepository.delete(loai);
            System.out.println("Xóa thành công");
            model.addAttribute("message", "Xóa thành công");
            loai=new LoaiSanPham();
            List<LoaiSanPham> list=loaiSanPhamRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("loai", loai);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"loaisanpham.html";
    }

    @PostMapping(path = "/update")
    public String updateLoai(@ModelAttribute LoaiSanPham loai, Model model, @RequestParam("btnStatus")String btnStatus) {
        try{
            loaiSanPhamRepository.save(loai);
            loai=new LoaiSanPham();
            List<LoaiSanPham> list=loaiSanPhamRepository.findAll();
            System.out.println("Save thành công");
            model.addAttribute("message", "Save thành công");
            model.addAttribute("list", list);
            model.addAttribute("loai", loai);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"loaisanpham.html";
    }
    
}
