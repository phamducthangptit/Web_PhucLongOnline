package com.example.PhucLongOnline.Controller;

import org.springframework.ui.Model;

import org.springframework.web.multipart.MultipartFile;

import com.example.PhucLongOnline.Model.NguyenLieu;
import com.example.PhucLongOnline.Model.NhaCungCap;
import com.example.PhucLongOnline.Repository.NguyenLieuRepository;
import com.example.PhucLongOnline.Repository.NhaCungCapRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/nhacungcap")
public class NhaCungCapController {
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;
    private final String path="manage/";
    @GetMapping("/list")
    public String get(Model model){
        try{
            List<NhaCungCap> list=nhaCungCapRepository.findAll();
            NhaCungCap nhaCungCap = new NhaCungCap();
            model.addAttribute("list", list);
            model.addAttribute("nhaCungCap", nhaCungCap);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch (Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"nhacungcap.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") String id, Model model) {
        try{
            List<NhaCungCap> list=nhaCungCapRepository.findAll();
            NhaCungCap nhaCungCap=nhaCungCapRepository.getById(id);
            model.addAttribute("list", list);
            model.addAttribute("nhaCungCap", nhaCungCap);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"nhacungcap.html";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id, Model model) {
        try{
            
            System.out.print("id: ");
            System.out.println(id);
            NhaCungCap nhaCungCap=nhaCungCapRepository.getById(id);
            try{
                nhaCungCapRepository.delete(nhaCungCap);
            }
            catch(Exception e){
                System.out.println("Lỗi: "+ e);
                model.addAttribute("message", "Không thể xóa vì đã có liên kết");
            }
            nhaCungCap=new NhaCungCap();
            List<NhaCungCap> list=nhaCungCapRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("nhaCungCap", nhaCungCap);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
        }
        
        return path+"nhacungcap.html";
    }

    @PostMapping(path = "/update")
    public String update(@ModelAttribute NhaCungCap nhaCungCap, Model model,
     @RequestParam("btnStatus")String btnStatus) {
        try{
            try{
                nhaCungCapRepository.save(nhaCungCap);
                System.out.println("Save thành công");
                model.addAttribute("message", "Save thành công");
            }
            catch(Exception e){
                System.out.println("Lỗi: "+ e);
                model.addAttribute("message", "Lỗi: "+e);
            }
            nhaCungCap=new NhaCungCap();
            List<NhaCungCap> list=nhaCungCapRepository.findAll();
            
            model.addAttribute("list", list);
            model.addAttribute("nhaCungCap", nhaCungCap);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"nhacungcap.html";
    }
    
}
