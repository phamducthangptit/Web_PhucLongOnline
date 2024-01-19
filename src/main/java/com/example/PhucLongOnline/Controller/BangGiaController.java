package com.example.PhucLongOnline.Controller;

import org.springframework.ui.Model;

import org.springframework.web.multipart.MultipartFile;

import com.example.PhucLongOnline.Model.BangGia;
import com.example.PhucLongOnline.Model.CT_BangGia;
import com.example.PhucLongOnline.Model.CT_SanPham_Size;
import com.example.PhucLongOnline.Model.NguyenLieu;
import com.example.PhucLongOnline.Model.NhanVien;
import com.example.PhucLongOnline.Model.Quyen;
import com.example.PhucLongOnline.Model.TaiKhoan;
import com.example.PhucLongOnline.Repository.BangGiaRepository;
import com.example.PhucLongOnline.Repository.CTBangGiaRepository;
import com.example.PhucLongOnline.Repository.CTSanPhamSizeRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/banggia")
public class BangGiaController {
    @Autowired
    private BangGiaRepository bangGiaRepository;

    @Autowired
    private CTSanPhamSizeRepository ctSanPhamSizeRepository;

    @Autowired
    private CTBangGiaRepository ctBangGiaRepository;

    private final String path="manage/";


    @GetMapping("/list")
    public String get(Model model){
        try{
            List<BangGia> list=bangGiaRepository.findAll();
            BangGia bangGia=new BangGia();
            model.addAttribute("list", list);
            model.addAttribute("bangGia", bangGia);
            model.addAttribute("btnStatus", "btnAdd");
            

        }
        catch (Exception e){
            System.out.print(e);
            model.addAttribute("message", "Lỗi: "+ e);
        }
        
        return path+"banggia.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") int id, Model model) {
        try{
            List<BangGia> list=bangGiaRepository.findAll();
            BangGia bangGia=bangGiaRepository.getById(id);
            model.addAttribute("list", list);
            model.addAttribute("bangGia", bangGia);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+ e);
        }
        
        return path+"banggia.html";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        try{
            BangGia bangGia=bangGiaRepository.getById(id);
            bangGiaRepository.delete(bangGia);
            System.out.println("Xóa thành công");
            model.addAttribute("message", "Xóa thành công");
            bangGia=new BangGia();
            List<BangGia> list=bangGiaRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("bangGia", bangGia);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"banggia.html";
    }

    @PostMapping(path = "/update")
    public String update(@ModelAttribute BangGia bangGia, Model model, @RequestParam("btnStatus")String btnStatus) {
        try{
            bangGiaRepository.save(bangGia);
            bangGia=new BangGia();
            List<BangGia> list=bangGiaRepository.findAll();
            System.out.println("Save thành công");
            model.addAttribute("message", "Lưu thành công");
            model.addAttribute("list", list);
            model.addAttribute("bangGia", bangGia);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"banggia.html";
    }

    @GetMapping(path = "/detail")
    public String detail(@RequestParam("id") int id, Model model) {
        try{
            BangGia bangGia=bangGiaRepository.getById(id);
            List<CT_SanPham_Size> ctList=ctSanPhamSizeRepository.findAll();
            if (bangGia.getCtBangGiaList().size() ==0){
                for (CT_SanPham_Size x: ctList){
                    CT_BangGia temp=new CT_BangGia();
                    temp.setCtSanPhamSize(x);
                    temp.setBangGia(bangGia);
                    temp.setGia(x.getGiaHienThoi());
                    ctBangGiaRepository.save(temp);
                }
            }
            bangGia=bangGiaRepository.getById(id);
            List<CT_BangGia> list=bangGia.getCtBangGiaList();
            CT_BangGia ctBangGia=new CT_BangGia();
            ctBangGia.setBangGia(bangGia);
            model.addAttribute("list", list);
            model.addAttribute("ctBangGia", ctBangGia);
            model.addAttribute("btnStatus", "btnAdd");
            model.addAttribute("id", id);

        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"ctbanggia.html";
    }

    @PostMapping(path = "/detail")
    public String update(@RequestParam("id") int id, Model model, @ModelAttribute CT_BangGia ct_BangGia) {
        try{
            ctBangGiaRepository.save(ct_BangGia);
            System.out.println("Save thành công");
            model.addAttribute("message", "Lưu thành công");
            BangGia bangGia=bangGiaRepository.getById(id);
            List<CT_BangGia> list=bangGia.getCtBangGiaList();
            model.addAttribute("list", list);
            model.addAttribute("btnStatus", "btnAdd");
            model.addAttribute("id", id);

        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"ctbanggia.html";
    }

    @GetMapping(path = "/editCT")
    public String addDetail(@RequestParam("id") int id,@RequestParam("idBG") int idBG,Model model) {
        try{
            CT_BangGia ctBangGia=ctBangGiaRepository.getById(idBG);
            BangGia bangGia=bangGiaRepository.getById(id);
            List<CT_BangGia> list=bangGia.getCtBangGiaList();
            List<CT_SanPham_Size> ctList=ctSanPhamSizeRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("ctList", ctList);
            model.addAttribute("ctBangGia", ctBangGia);
            model.addAttribute("btnStatus", "btnEdit");
            model.addAttribute("id", id);

        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"ctbanggia.html";
    }
    
}
