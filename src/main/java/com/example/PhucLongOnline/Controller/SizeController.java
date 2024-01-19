package com.example.PhucLongOnline.Controller;
import org.springframework.ui.Model;


import com.example.PhucLongOnline.Model.Size;
import com.example.PhucLongOnline.Repository.SizeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;

    private final String path="manage/";

    @GetMapping("/test")
    public String index(){
        return "index.html";
    }

    @GetMapping("/list")
    public String get(Model model){
        try{
            List<Size> list=sizeRepository.findAll();
            Size size=new Size();
            model.addAttribute("list", list);
            model.addAttribute("size", size);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch (Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"size.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") int id, Model model) {
        try{
            List<Size> list=sizeRepository.findAll();
            Size size=sizeRepository.getById(id);
            model.addAttribute("list", list);
            model.addAttribute("size", size);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"size.html";
    }
    
    @GetMapping("/delete")
    public String deleteSize(@RequestParam("id") int id, Model model) {
        try{
            
            System.out.print("id: ");
            System.out.println(id);
            Size size=sizeRepository.getById(id);
            sizeRepository.delete(size);
            System.out.println("Xóa thành công");
            model.addAttribute("message", "Xóa thành công");
            size=new Size();
            List<Size> list=sizeRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("size", size);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"size.html";
    }

    @PostMapping(path = "/update-size")
    public String createSize(@ModelAttribute Size size, Model model, @RequestParam("btnStatus")String btnStatus) {
        try{
            
            sizeRepository.save(size);
            size=new Size();
            List<Size> list=sizeRepository.findAll();
            System.out.println("Save thành công");
            model.addAttribute("message", "Save thành công");
            model.addAttribute("list", list);
            model.addAttribute("size", size);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }

        
        return path+"size.html";
    }
    
}
