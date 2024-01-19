package com.example.PhucLongOnline.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.PhucLongOnline.Model.HoaDon;
import com.example.PhucLongOnline.Model.Quyen;
import com.example.PhucLongOnline.Model.TaiKhoan;
import com.example.PhucLongOnline.Repository.HoaDonRepository;
import com.example.PhucLongOnline.Repository.QuyenRepository;
import com.example.PhucLongOnline.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class Controller036 {
    @Autowired
    private QuyenRepository quyenRepository;
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @GetMapping("home")
    public String home ()
    {
        return "home.html";
    }
    @GetMapping("danhSachTaiKhoan")
    public String danhSachTaiKhoan(Model model)
    {
        List<TaiKhoan> taiKhoanList =taiKhoanRepository.findAll();
        List<TaiKhoan> temp =new ArrayList<>(); ;
        for (TaiKhoan taiKhoan : taiKhoanList)
            {
                if(taiKhoan.getQuyen().getIdQuyen()!= 5)
                {
                    temp.add(taiKhoan);
                }
            }

        System.out.println(temp.get(0).getNhanVien().getEmail());

        model.addAttribute("taiKhoanList",temp);
        return "danhSachTaiKhoan";
    }
    @GetMapping("danhSachTaiKhoanKH")
    public String danhSachTaiKhoanKH(Model model)
    {
        List<TaiKhoan> taiKhoanList =taiKhoanRepository.findAll();
        List<TaiKhoan> temp =new ArrayList<>(); ;
        for (TaiKhoan taiKhoan : taiKhoanList)
        {
            if(taiKhoan.getQuyen().getIdQuyen()== 5)
            {
                temp.add(taiKhoan);
            }
        }



        model.addAttribute("taiKhoanList",temp);
        return "danhSachTaiKhoanKH";
    }
    @RequestMapping("/resetMatKhau")
    public String resetPassword(Model model, @RequestParam("username") String tenDangNhap)
    {
        TaiKhoan taiKhoan =taiKhoanRepository.findByTenDangNhap(tenDangNhap);
        taiKhoan.setMatKhau("123456");
        taiKhoanRepository.save(taiKhoan);
        return "redirect:/danhSachTaiKhoan";
    }

    @RequestMapping("/thaydoiquyen")
    public String thayDoiQuyen(Model model, @RequestParam("tenDN") String tenDN, @RequestParam("idQuyen") int id)
    {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(tenDN);
        Quyen quyen = quyenRepository.findById(id);
        if (taiKhoan.getQuyen().getIdQuyen() != id)
        {
            taiKhoan.setQuyen(quyen);
            taiKhoanRepository.save(taiKhoan);
        }
        return "redirect:/danhSachTaiKhoan";
    }
    @RequestMapping("/thaydoitrangthai")
    public String thayDoiTrangThai(Model model, @RequestParam("tenDN") String tenDN)
    {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(tenDN);
        if (taiKhoan.getTrangThai()==1)
        {
            taiKhoan.setTrangThai(0);
        }
        else taiKhoan.setTrangThai(1);
        taiKhoanRepository.save(taiKhoan);
        return "redirect:/danhSachTaiKhoan";
    }
    @GetMapping("/doanhThuNgay")
    public String doanhThuNgay()
    {

        return "doanhThuNgay";
    }
    @GetMapping ("/xemDoanhThuNgay")
            public String xemDoanhThuNgay(Model model , @RequestParam("ngayXem") String ngayXem)
    {
        System.out.println(ngayXem);
            Date ngayLap = Date.valueOf(ngayXem);
            List<HoaDon> hoaDonList = hoaDonRepository.findAllByNgayLap(ngayLap);

            int tongDoanhThuNgay = 0;
            for (HoaDon hoaDon : hoaDonList)
            {
                tongDoanhThuNgay += hoaDon.getTongHoaDon();
            }

        System.out.println(tongDoanhThuNgay);
            System.out.println(ngayLap);

        model.addAttribute("hoaDonList",hoaDonList);
        model.addAttribute("tongDoanhThuNgay",tongDoanhThuNgay);
        model.addAttribute("ngayDangXem",ngayLap);
        return "xemDoanhThuNgay";
    }
    @GetMapping ("xemDoanhThuThang")
    public String xemDoanhThuThang (Model model, @RequestParam ("thangXem") int thangXem,@RequestParam ("namXem") int namXem)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(namXem, thangXem-1, 1); // Lưu ý: Tháng trong Calendar bắt đầu từ 0
        Date startDate = new Date(calendar.getTimeInMillis());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = new Date(calendar.getTimeInMillis());
        System.out.println(startDate);
        System.out.println(endDate);
        List<HoaDon> hoaDonList = hoaDonRepository.findAllByNgayLapBetween(startDate,endDate);
        int tongDoanhThuThang = 0;
        for (HoaDon hoaDon : hoaDonList)
        {
            tongDoanhThuThang += hoaDon.getTongHoaDon();
        }
        model.addAttribute("hoaDonList",hoaDonList);
        model.addAttribute("thangDangXem", thangXem);
        model.addAttribute("namDangXem", namXem);
        model.addAttribute("tongDoanhThuThang",tongDoanhThuThang);
        return "xemDoanhThuThang";
    }
}
