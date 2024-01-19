package com.example.PhucLongOnline.Controller;

import org.springframework.ui.Model;

import com.example.PhucLongOnline.Model.Voucher;
import com.example.PhucLongOnline.Repository.VoucherRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherRepository voucherRepository;
    private final String path="manage/";
    @GetMapping("/list")
    public String get(Model model){
        try{
            List<Voucher> list=voucherRepository.findAll();
            Voucher voucher=new Voucher();
            model.addAttribute("list", list);
            model.addAttribute("voucher", voucher);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch (Exception e){
            System.out.print(e);
        }
        
        return path+"voucher.html";
    };

    @GetMapping("/edit")
    public String getEditButton(@RequestParam("id") int id, Model model) {
        try{
            List<Voucher> list=voucherRepository.findAll();
            Voucher voucher=voucherRepository.getById(id);
            model.addAttribute("list", list);
            model.addAttribute("voucher", voucher);
            model.addAttribute("btnStatus", "btnEdit");
        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
        }
        
        return path+"voucher.html";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        try{
            
            System.out.print("id: ");
            System.out.println(id);
            Voucher voucher=voucherRepository.getById(id);
            voucherRepository.delete(voucher);
            voucher=new Voucher();
            List<Voucher> list=voucherRepository.findAll();
            model.addAttribute("list", list);
            model.addAttribute("voucher", voucher);
            model.addAttribute("btnStatus", "btnAdd");
        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
        }
        
        return path+"voucher.html";
    }

    @PostMapping(path = "/update")
    public String update(@ModelAttribute Voucher voucher, Model model, @RequestParam("btnStatus")String btnStatus) {
        try{
            voucherRepository.save(voucher);
            voucher=new Voucher();
            List<Voucher> list=voucherRepository.findAll();
            System.out.println("Save thành công");
            model.addAttribute("list", list);
            model.addAttribute("voucher", voucher);
            model.addAttribute("btnStatus", "btnAdd");

        }
        catch(Exception e){
            System.out.print("Error: ");
            System.out.println(e);
        }
        return path+"voucher.html";
    }
    
}
