package com.example.PhucLongOnline.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.PhucLongOnline.Model.KhachHang;
import com.example.PhucLongOnline.Model.ResponeObject;
import com.example.PhucLongOnline.Model.TaiKhoan;
import com.example.PhucLongOnline.Email.EmailService;
import com.example.PhucLongOnline.Email.GenerateCode;
import com.example.PhucLongOnline.Repository.KhachHangRepository;
import com.example.PhucLongOnline.Repository.TaiKhoanRepository;
import com.example.PhucLongOnline.Service.TaiKhoanService;
import com.example.PhucLongOnline.dto.LoginRequest;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    TaiKhoanService userService;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @GetMapping("/login")
    public String Login(Model model) {
        return "login.html";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ResponeObject> checkLogin(@RequestBody LoginRequest login, HttpSession session) {
        String username = login.getUsername();
        String password = login.getPasswword();
        String checkLogin = userService.customCheckCredentials(username, password);
        if (checkLogin == "ok") {
            // Thêm đối tượng vào session
            Optional<TaiKhoan> optionalTaiKhoan = taiKhoanRepository.findById(username);
            TaiKhoan taiKhoan = optionalTaiKhoan.get();
            session.setAttribute("taikhoan", taiKhoan);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("ok", "Đăng nhập thành công", checkLogin));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("faield", "Đăng nhập thất bại", checkLogin));
        }
    }

    @GetMapping("/signup")
    public String formSignup() {
        return "signup.html";
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<ResponeObject> checkSignup(@RequestBody Map<String, String> params, HttpSession session) {
        String firstname = params.get("firstname");
        String lastname = params.get("lastname");
        String gender = params.get("gender");
        String address = params.get("address");
        String sdt = params.get("sdt");
        String email = params.get("email");
        String mk = params.get("mk");
        Optional<KhachHang> optionalKhachhang = khachHangRepository.findById(email);
        if (optionalKhachhang.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("failed", "Email đã tồn tại", "Email đã tồn tại"));
        } else {
            // Cập nhật vào db
            KhachHang khachhang = new KhachHang();
            khachhang.setTenDangNhap(email);
            khachhang.setHo(firstname);
            khachhang.setTen(lastname);
            khachhang.setGioiTinh(gender);
            khachhang.setDiaChi(address);;
            khachhang.setSdt(sdt);
            session.setAttribute("khachhang", khachhang);
            session.setAttribute("mk", mk);
            GenerateCode generateCode = new GenerateCode();
            String code = generateCode.generateCode();
            System.out.print(code);
            emailService.sendEmail(email, "Mã xác nhận của bạn", code);
            session.setAttribute("code", code);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("ok", "Lấy thông tin khách hàng thành công", khachhang));
        }
    }

    @PostMapping("/checkCode")
    @ResponseBody
    public ResponseEntity<ResponeObject> checkCode(@RequestBody Map<String, String> param, HttpSession session) {
        String code = param.get("code");
        String codeSend = (String) session.getAttribute("code");
        System.out.println(codeSend);
        System.out.println(code);
        if (code.equals(codeSend)) {
            try {
                KhachHang khachhang = (KhachHang) session.getAttribute("khachhang");
                System.out.println(khachhang.getTenDangNhap());
                System.out.println(khachhang.getEmail());
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setTenDangNhap((khachhang.getTenDangNhap()));
                taikhoan.setMatKhau((String) session.getAttribute("mk"));
                taikhoan.setTrangThai(0);
                taiKhoanRepository.save(taikhoan);
                khachHangRepository.save(khachhang);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponeObject("ok", "Thêm tài khoản thành công", "ok"));
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponeObject("failed", "Thêm tài khoản thất bại", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("failed", "Mã xác nhận không đúng", "Mã xác nhận không đúng"));
        }
    }

    @GetMapping("/forgot")
    public String forgot() {
        return "forgot.html";
    }

    @PostMapping("/forgot")
    @ResponseBody
    public ResponseEntity<ResponeObject> forgot(@RequestBody Map<String, String> param,HttpSession session) {
        // TODO: process POST request
        String email = param.get("email");
        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanRepository.findById(email);
        if (optionalTaiKhoan.isPresent()) {
            GenerateCode generateCode = new GenerateCode();
            String code = generateCode.generateCode();
            System.out.println(code);
            emailService.sendEmail(email, "Mã xác nhận của bạn là:", code);
            session.setAttribute("code2", code);
            TaiKhoan taikhoan = optionalTaiKhoan.get();
            session.setAttribute("taikhoan", taikhoan);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("ok", "Mail được xác nhận", ""));
        } else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("failed", "Mail không tồn tại", ""));
    }

    @PostMapping("/reset")
    public ResponseEntity<ResponeObject> reset(@RequestBody Map<String,String> param,HttpSession session) {

        String code = param.get("code");
        String codeSend = (String) session.getAttribute("code2");
        String pass = param.get("password");
        if(code.equals(codeSend)){
            TaiKhoan taikhoan = (TaiKhoan) session.getAttribute("taikhoan");
            taikhoan.setMatKhau(pass);
            try {
                taiKhoanRepository.save(taikhoan);
                return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("ok", "Cập nhật mật khẩu thành công", ""));
            } catch (Exception e) {
                System.out.println(e);
                return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("failed", "Cập nhật thất bại,vui lòng thử lại sau.", ""));
            }

        }
        else{
            return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponeObject("failed", "Mã xác nhận không đúng", ""));
        }
    }
}
