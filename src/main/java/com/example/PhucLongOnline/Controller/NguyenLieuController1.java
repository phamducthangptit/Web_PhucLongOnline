package com.example.PhucLongOnline.Controller;

import org.springframework.ui.Model;

import org.springframework.web.multipart.MultipartFile;

import com.example.PhucLongOnline.Model.NguyenLieu;
import com.example.PhucLongOnline.Repository.NguyenLieuRepository;

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
@RequestMapping("/nguyenlieu")
public class NguyenLieuController1 {
    @Autowired
    private NguyenLieuRepository nguyenLieuRepository;
    private final String path="manage/";
    @GetMapping("/list")
    public String get(Model model){
        try{
            List<NguyenLieu> list=nguyenLieuRepository.findAll();
            NguyenLieu nguyenLieu=new NguyenLieu();
            model.addAttribute("list", list);
            model.addAttribute("nguyenLieu", nguyenLieu);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch (Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"nguyenlieu.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") int id, Model model) {
        try{
            List<NguyenLieu> list=nguyenLieuRepository.findAll();
            NguyenLieu nguyenLieu=nguyenLieuRepository.getById(id);
            model.addAttribute("list", list);
            model.addAttribute("nguyenLieu", nguyenLieu);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"nguyenlieu.html";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        try{
            
            NguyenLieu nguyenLieu=nguyenLieuRepository.getById(id);
            nguyenLieuRepository.delete(nguyenLieu);
            System.out.println("Xóa thành công");
            model.addAttribute("message", "Xóa thành công");
            nguyenLieu=new NguyenLieu();
            List<NguyenLieu> list=nguyenLieuRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("nguyenLieu", nguyenLieu);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"nguyenlieu.html";
    }

    @PostMapping(path = "/update")
    public String update(@ModelAttribute NguyenLieu nguyenLieu, Model model,@RequestParam("imageInput") MultipartFile file,
     @RequestParam("btnStatus")String btnStatus) {
        try{
            LocalDateTime currentDateTime = LocalDateTime.now();
            String uploadDir = "Web_PhucLongOnline/src/main/resources/static/img/nguyenlieu";
            String fileName = "";
            System.out.println(file.getResource());
                if(file == null || file.isEmpty()){
                    fileName = "default.png";
                } else {
                    fileName = nguyenLieu.getTenNguyenLieu() + "_" +
                            currentDateTime.getHour() + "h" +
                            currentDateTime.getMinute() + "m" +
                            currentDateTime.getSecond() + "s" + ".png";
                    System.out.println(fileName);
                    Path filePath = Paths.get(uploadDir,fileName);
                    System.out.println(file.getOriginalFilename());
                    System.out.println(filePath);
                    Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
                }
            nguyenLieu.setHinhAnh(fileName);
            nguyenLieuRepository.save(nguyenLieu);
            System.out.println("Save thành công");
            model.addAttribute("message", "Save thành công");
            nguyenLieu=new NguyenLieu();
            List<NguyenLieu> list=nguyenLieuRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("nguyenLieu", nguyenLieu);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"nguyenlieu.html";
    }
    
}
