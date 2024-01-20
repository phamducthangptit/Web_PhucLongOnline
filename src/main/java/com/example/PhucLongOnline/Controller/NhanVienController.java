package com.example.PhucLongOnline.Controller;

import org.springframework.ui.Model;

import org.springframework.web.multipart.MultipartFile;

import com.example.PhucLongOnline.Model.NguyenLieu;
import com.example.PhucLongOnline.Model.NhanVien;
import com.example.PhucLongOnline.Model.Quyen;
import com.example.PhucLongOnline.Model.TaiKhoan;
import com.example.PhucLongOnline.Repository.NguyenLieuRepository;
import com.example.PhucLongOnline.Repository.NhanVienRepository;
import com.example.PhucLongOnline.Repository.QuyenRepository;
import com.example.PhucLongOnline.Repository.TaiKhoanRepository;
import com.example.PhucLongOnline.Service.NhanVienService;

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
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private QuyenRepository quyenRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    private final String path="manage/";
    @GetMapping("/list")
    public String get(Model model){
        try{
            List<NhanVien> list=nhanVienRepository.findAll();
            NhanVien nhanVien=new NhanVien();
            nhanVien.setMaNhanVien(nhanVienService.getNewMaNhanVien());
            model.addAttribute("list", list);
            model.addAttribute("nhanVien", nhanVien);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch (Exception e){
            System.out.print(e);
            model.addAttribute("message", "Lỗi: "+ e);
        }
        
        return path+"nhanvien.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") String id, Model model) {
        try{
            List<NhanVien> list=nhanVienRepository.findAll();
            NhanVien nhanVien=nhanVienRepository.getById(id);
            model.addAttribute("list", list);
            model.addAttribute("nhanVien", nhanVien);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+ e);
        }
        
        return path+"nhanVien.html";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id, Model model) {
        try{
            NhanVien nhanVien=nhanVienRepository.getById(id);
            try{
                nhanVienRepository.delete(nhanVien);
                System.out.println("Xóa thành công");
                model.addAttribute("message", "Xóa thành công");
            }
            catch (Exception e){
                System.out.println("Nhân viên đã thực thi thao tác");// nhân viên bị khóa tài khoản
                TaiKhoan taiKhoan = taiKhoanRepository.getById(nhanVien.getMaNhanVien());
                taiKhoan.setTrangThai(0);
                taiKhoanRepository.save(taiKhoan);
                model.addAttribute("message", "Đã khóa tài khoản thành công");
            }
            nhanVien=new NhanVien();
            nhanVien.setMaNhanVien(nhanVienService.getNewMaNhanVien());
            List<NhanVien> list=nhanVienRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("nhanVien", nhanVien);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"nhanvien.html";
    }

    @PostMapping(path = "/update")
    public String update(@ModelAttribute NhanVien nhanVien, Model model,@RequestParam("imageInput") MultipartFile file,
     @RequestParam("btnStatus")String btnStatus) {
        try{
            LocalDateTime currentDateTime = LocalDateTime.now();
            String uploadDir = "Web_PhucLongOnline/src/main/resources/static/img/nhanVien";
            String fileName = "";
            System.out.println(file.getResource());
                if(file == null || file.isEmpty()){
                    fileName = "default.png";
                } else {
                    fileName = nhanVien.getMaNhanVien() + "_" +
                            currentDateTime.getHour() + "h" +
                            currentDateTime.getMinute() + "m" +
                            currentDateTime.getSecond() + "s" + ".png";
                    System.out.println(fileName);
                    Path filePath = Paths.get(uploadDir,fileName);
                    System.out.println(file.getOriginalFilename());
                    System.out.println(filePath);
                    Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
                }
            nhanVien.setHinhAnh(fileName);
            if (btnStatus.equals("btnAdd")){
                Quyen quyen=quyenRepository.getById(4);
                TaiKhoan taiKhoan = new TaiKhoan(nhanVien.getMaNhanVien(), "123", 1, quyen);
                taiKhoanRepository.save(taiKhoan);
            }
            
            try{
                nhanVienRepository.save(nhanVien);
                System.out.println("Save thành công");
                model.addAttribute("message", "Save thành công");
            }
            catch(Exception e){
                System.out.println("Lỗi: "+ e);
                model.addAttribute("message", "Lỗi: Trùng khóa");
            }
            nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(nhanVienService.getNewMaNhanVien());
            List<NhanVien> list=nhanVienRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("nhanVien", nhanVien);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"nhanvien.html";
    }
    
}
