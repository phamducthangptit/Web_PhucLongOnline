package com.example.PhucLongOnline.Controller;

import org.springframework.ui.Model;

import org.springframework.web.multipart.MultipartFile;

import com.example.PhucLongOnline.Model.CT_SanPham_Size;
import com.example.PhucLongOnline.Model.CongThuc;
import com.example.PhucLongOnline.Model.LoaiSanPham;
import com.example.PhucLongOnline.Model.NguyenLieu;
import com.example.PhucLongOnline.Model.SanPham;
import com.example.PhucLongOnline.Model.Size;
import com.example.PhucLongOnline.Repository.CTSanPhamSizeRepository;
import com.example.PhucLongOnline.Repository.CongThucRepository;
import com.example.PhucLongOnline.Repository.LoaiSanPhamRepository;
import com.example.PhucLongOnline.Repository.NguyenLieuRepository;
import com.example.PhucLongOnline.Repository.SanPhamRepository;
import com.example.PhucLongOnline.Repository.SizeRepository;
import com.example.PhucLongOnline.Service.CTSanPhamSizeService;
import com.example.PhucLongOnline.Service.Imp.CTSanPhamSizeServiceImp;

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
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private CTSanPhamSizeRepository ctSanPhamSizeRepository;
    @Autowired
    private CTSanPhamSizeServiceImp ctSanPhamSizeService;

    @Autowired
    private NguyenLieuRepository nguyenLieuRepository;

    @Autowired
    private CongThucRepository congThucRepository;

    private final String path="manage/";
    @GetMapping("/list")
    public String get(Model model){
        try{
            List<SanPham> list=sanPhamRepository.findAll();
            List<LoaiSanPham> loaiSanPhamsList=loaiSanPhamRepository.findAll();
            // for(SanPham x: list){
            //     for (CT_SanPham_Size y:x.getCtSanPhamSizeList()){
            //         System.out.println(1);
            //         System.out.println(y.getGiaHienThoi());
            //     }
            // }
            // List<Size> sizeList=sizeRepository.findAll();
            // model.addAttribute("sizeList", sizeList);
            SanPham sanPham=new SanPham();
            model.addAttribute("list", list);
            model.addAttribute("loaiSanPhamList", loaiSanPhamsList);
            model.addAttribute("sanPham", sanPham);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch (Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"sanpham.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") int id, Model model) {
        try{
            List<SanPham> list=sanPhamRepository.findAll();
            SanPham sanPham=sanPhamRepository.getById(id);
            List<LoaiSanPham> loaiSanPhamsList=loaiSanPhamRepository.findAll();
            model.addAttribute("loaiSanPhamList", loaiSanPhamsList);
            model.addAttribute("list", list);
            model.addAttribute("sanPham", sanPham);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"sanpham.html";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        try{
            
            System.out.print("id: ");
            System.out.println(id);
            SanPham sanPham=sanPhamRepository.getById(id);
            try{
                sanPhamRepository.delete(sanPham);
                System.out.println("Xóa thành công");
                model.addAttribute("message", "Xóa thành công");
            }
            catch(Exception e){
                System.out.println("Lỗi: "+ e);
                model.addAttribute("message", "Lỗi: Đã liên kết");
            }
            sanPham=new SanPham();
            List<SanPham> list=sanPhamRepository.findAll();
            List<LoaiSanPham> loaiSanPhamsList=loaiSanPhamRepository.findAll();
            model.addAttribute("loaiSanPhamList", loaiSanPhamsList);
            model.addAttribute("list", list);
            model.addAttribute("sanPham", sanPham);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"sanpham.html";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SanPham sanPham, Model model,@RequestParam("imageInput") MultipartFile file,
     @RequestParam("btnStatus")String btnStatus) {
        try{
            LocalDateTime currentDateTime = LocalDateTime.now();
            String uploadDir = "Web_PhucLongOnline/src/main/resources/static/img/sanpham";
            String fileName = "";
            System.out.println(file.getResource());
                if(file == null || file.isEmpty()){
                    fileName = "default.png";
                } else {
                    fileName = sanPham.getIdSanPham() + "_" +
                            currentDateTime.getHour() + "h" +
                            currentDateTime.getMinute() + "m" +
                            currentDateTime.getSecond() + "s" + ".png";
                    System.out.println(fileName);
                    Path filePath = Paths.get(uploadDir,fileName);
                    System.out.println(file.getOriginalFilename());
                    System.out.println(filePath);
                    Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
                }
            sanPham.setHinhAnh(fileName);
            try{
                sanPhamRepository.save(sanPham);
                System.out.println("Save thành công");
                model.addAttribute("message", "Save thành công");
            }
            catch(Exception e){
                System.out.println("Lỗi: "+ e);
                model.addAttribute("message", "Lỗi: Trùng khóa");
            }
            sanPham=new SanPham();
            List<SanPham> list=sanPhamRepository.findAll();
            List<LoaiSanPham> loaiSanPhamsList=loaiSanPhamRepository.findAll();
            model.addAttribute("loaiSanPhamList", loaiSanPhamsList);
            model.addAttribute("list", list);
            model.addAttribute("sanPham", sanPham);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        return path+"sanpham.html";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") int id, Model model) {
        try{
            SanPham sanPham=sanPhamRepository.getById(id);
            List<CT_SanPham_Size> list=sanPham.getCtSanPhamSizes();
            List<Size> sizeList =sizeRepository.findAll();
            CT_SanPham_Size ctSanPhamSize=new CT_SanPham_Size();
            ctSanPhamSize.setGiaHienThoi(0.0);
            ctSanPhamSize.setSanPham(sanPham);
            model.addAttribute("ctSanPhamSize", ctSanPhamSize);
            model.addAttribute("list", list);
            model.addAttribute("sizeList", sizeList);
            model.addAttribute("sanPham", sanPham);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"ctsanphamsize.html";
    }

    @PostMapping("/addSize")
    public String detailAdd(@ModelAttribute SanPham sanPham,@ModelAttribute CT_SanPham_Size ctSanPhamSize, Model model) {
        try{
            
            List<Size> sizeList =sizeRepository.findAll();
            try{
                ctSanPhamSizeRepository.save(ctSanPhamSize);
                System.out.println("Save thành công");
                model.addAttribute("message", "Save thành công");
            }
            catch (Exception e){
                System.out.println("Lỗi: Đã tồn tại trong hệ thống");
                model.addAttribute("message", "Lỗi: Đã tồn tại trong hệ thống");
            }
            ctSanPhamSize=new CT_SanPham_Size();
            ctSanPhamSize.setGiaHienThoi(0.0);
            ctSanPhamSize.setSanPham(sanPham);
            List<CT_SanPham_Size> list;
            try{    
                List<Integer> idList=ctSanPhamSizeService.findSP(sanPham.getIdSanPham());
                list=ctSanPhamSizeRepository.findAllById(idList);
                for (int x: idList){
                    System.out.println(x);
                }
            }
            catch (Exception e){
                System.out.println("Lỗi: "+ e);
                model.addAttribute("message", "Lỗi: "+e);
                sanPham=sanPhamRepository.getById(sanPham.getIdSanPham());
                list=sanPham.getCtSanPhamSizes();
            }
            model.addAttribute("ctSanPhamSize", ctSanPhamSize);
            model.addAttribute("list", list);
            model.addAttribute("sizeList", sizeList);
            model.addAttribute("sanPham", sanPham);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"ctsanphamsize.html";
    }

    @GetMapping("/deleteSize")
    public String detailDelete(@RequestParam("id")int id,@RequestParam("idSP")int idSP,Model model) {
        try{
            
            List<Size> sizeList =sizeRepository.findAll();
            CT_SanPham_Size temp=ctSanPhamSizeRepository.getById(id);
            try{
                ctSanPhamSizeRepository.delete(temp);
                System.out.println("Xóa thành công");
                model.addAttribute("message", "Xóa thành công");
            }
            catch(Exception ex){
                System.out.println("Lỗi: "+ ex);
                model.addAttribute("message", "Lỗi: "+ex);
            }
            CT_SanPham_Size ctSanPhamSize =new CT_SanPham_Size();
            ctSanPhamSize.setGiaHienThoi(0.0);
            SanPham sanPham=sanPhamRepository.getById(idSP);
            ctSanPhamSize.setSanPham(sanPham);
            List<CT_SanPham_Size> list=sanPham.getCtSanPhamSizes();
            model.addAttribute("ctSanPhamSize", ctSanPhamSize);
            model.addAttribute("list", list);
            model.addAttribute("sizeList", sizeList);
            model.addAttribute("sanPham", sanPham);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"ctsanphamsize.html";
    }

    @GetMapping("/addNguyenLieu")
    public String addNguyenLieu(@RequestParam("id")int id,Model model) {
        try{
            CT_SanPham_Size ctSanPhamSize=ctSanPhamSizeRepository.getById(id);
            List<CongThuc> list = ctSanPhamSize.getCongThucs();
            CongThuc congThuc = new CongThuc();
            congThuc.setCtSanPhamSize(ctSanPhamSize);
            List<NguyenLieu> nguyenLieuList= nguyenLieuRepository.findAll();
            model.addAttribute("congThuc", congThuc);
            model.addAttribute("ctSanPhamSize", ctSanPhamSize);
            model.addAttribute("list", list);
            model.addAttribute("nguyenLieuList", nguyenLieuList);
            model.addAttribute("btnStatus", "btnAdd");
            model.addAttribute("idct", id);
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"congthuc.html";
    }

    @PostMapping("/addNguyenLieu")
    public String themNguyenLieu(@RequestParam("id")int id,Model model,@ModelAttribute CongThuc congThuc) {
        try{
            try{
                congThucRepository.save(congThuc);
                System.out.println("Save thành công");
                model.addAttribute("message", "Save thành công");
            }
            catch(Exception ex){
                System.out.println("Lỗi: "+ ex);
                model.addAttribute("message", "Lỗi: Trùng khóa");
            }
            CT_SanPham_Size ctSanPhamSize=ctSanPhamSizeRepository.getById(id);
            List<CongThuc> list = ctSanPhamSize.getCongThucs();
            congThuc = new CongThuc();
            congThuc.setCtSanPhamSize(ctSanPhamSize);
            List<NguyenLieu> nguyenLieuList= nguyenLieuRepository.findAll();
            model.addAttribute("congThuc", congThuc);
            model.addAttribute("ctSanPhamSize", ctSanPhamSize);
            model.addAttribute("list", list);
            model.addAttribute("nguyenLieuList", nguyenLieuList);
            model.addAttribute("btnStatus", "btnAdd");
            model.addAttribute("idct", id);
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"congthuc.html";
    }

    @GetMapping("/addNguyenLieu/xoa")
    public String xoaNguyenLieu(@RequestParam("id")int id,@RequestParam("idCongThuc")int idCongThuc,Model model) {
        try{
            CongThuc congThuc= congThucRepository.getById(idCongThuc);
            try{
                congThucRepository.delete(congThuc);
                System.out.println("Xóa thành công");
                model.addAttribute("message", "Xóa thành công");
            }
            catch(Exception ex){
                System.out.println("Lỗi: "+ ex);
                model.addAttribute("message", "Lỗi: "+ex);
            }
            CT_SanPham_Size ctSanPhamSize=ctSanPhamSizeRepository.getById(id);
            List<CongThuc> list = ctSanPhamSize.getCongThucs();
            congThuc = new CongThuc();
            congThuc.setCtSanPhamSize(ctSanPhamSize);
            List<NguyenLieu> nguyenLieuList= nguyenLieuRepository.findAll();
            model.addAttribute("congThuc", congThuc);
            model.addAttribute("ctSanPhamSize", ctSanPhamSize);
            model.addAttribute("list", list);
            model.addAttribute("nguyenLieuList", nguyenLieuList);
            model.addAttribute("btnStatus", "btnAdd");
            model.addAttribute("idct", id);
        }
        catch(Exception e){
            System.out.println("Lỗi: "+ e);
            model.addAttribute("message", "Lỗi: "+e);
        }
        
        return path+"congthuc.html";
    }
}
