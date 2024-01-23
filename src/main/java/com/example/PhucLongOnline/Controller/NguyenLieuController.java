package com.example.PhucLongOnline.Controller;


import com.example.PhucLongOnline.Model.*;
import com.example.PhucLongOnline.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class NguyenLieuController {

    @Autowired
    private NguyenLieuRepository nguyenLieuRepository;

    @Autowired
    private CT_NguyenLieuRepository ct_nguyenLieuRepository;

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    @Autowired
    private DonDatNguyenLieuRepository donDatNguyenLieuRepository;

    @Autowired
    private CT_DonDatHangRepository ct_donDatHangRepository;

    @Autowired
    private PhieuNhapHangRepsitory phieuNhapHangRepsitory;

    @Autowired
    private CT_PhieuNhapHangRepository ct_phieuNhapHangRepository;
    @GetMapping(value={"/nguyen-lieu"})
    public String nguyenLieu(Model model) {
        List<NguyenLieu> nguyenLieuList = nguyenLieuRepository.findAll();
        for(NguyenLieu nl : nguyenLieuList){
            System.out.println(nl.toString());
        }
        model.addAttribute("listNL",nguyenLieuList);
        return "nguyenlieu";
    }

    @GetMapping(value = {"/dat-nguyen-lieu"})
    public String getNguyenLieu(Model model,@RequestParam(value = "inputNL") List<Integer> ids){
        System.out.println(ids);
        List<NguyenLieu> result = new ArrayList<>();
        System.out.println("check dat nguyen lieu");
        for(int x: ids) {
            NguyenLieu nl = nguyenLieuRepository.findById(x).orElse(null);
            nl.setCtNguyenLieus(ct_nguyenLieuRepository.getNCC(nl.getIdNguyenLieu()));
//            System.out.println(nl.getCtNguyenLieus());
            result.add((NguyenLieu) nl);
        }
        LocalDate currentDate = LocalDate.now();
        String[] tmp = currentDate.toString().split("-");
        String outputDay = tmp[2]+"-"+tmp[1]+"-"+tmp[0];

        model.addAttribute("currDay", outputDay);
        model.addAttribute("listDNL", result);
        return "datnguyenlieu";
    }

    @GetMapping(value = {"/ds-nha-cung-cap"})
    public String getNhaCungCap(Model model) {
        List<NhaCungCap> listNCC = nhaCungCapRepository.findAll();
        model.addAttribute("listNCC", listNCC);
        return "dsnhacungcap";
    }

    @GetMapping(value = {"/nha-cung-cap"})
    public String getNhaCungCap1(Model model,@RequestParam(value = "maNCC") String ma) {
        NhaCungCap ncc = nhaCungCapRepository.findById(ma).orElse(null);
        if (ncc == null) return "redirect:http://localhost:8080/ds-nha-cung-cap";
        List<CT_NguyenLieu> ctnls = ct_nguyenLieuRepository.getNL_NCC(ma);
        List<NguyenLieu> nls = nguyenLieuRepository.getNL_NCC_X(ma);

        model.addAttribute("listNL",ctnls);
        model.addAttribute("listNL1", nls);
        return "nhacungcap";
    }

    @GetMapping(value = {"/ct-nha-cung-cap"})
    public String getCTNhaCungCap1(Model model,
                                   @RequestParam(value = "maNCC") String ma,
                                   @RequestParam(value = "idNL") int id) {
        List<CT_NguyenLieu> ctnls = ct_nguyenLieuRepository.getCT_NL_NCC(ma,id);

        model.addAttribute("listNL",ctnls);
        model.addAttribute("nl", ctnls.get(0));
        return "them-thaydoi-gia-nl";
    }

    @PostMapping(value = {"/gia-nl"})
    public String postCTNL(Model model,
                           @RequestParam(value = "idNL") int idNL,
                           @RequestParam(value = "maNCC") String maNCC,
                           @RequestParam(value = "ngay") String ngay,
                           @RequestParam(value = "gia") Double gia
                           ) {
        System.out.println("idnguyenlieu: "+ idNL + ",ma nha cc: "+ maNCC + ",ngay: "+ ngay + ",gia: "+gia);
        List<CT_NguyenLieu> ctnls1 = ct_nguyenLieuRepository.getCT_NL_NCC(maNCC,idNL);
        int check = 0;
        for(CT_NguyenLieu ctnl:ctnls1) {
            String ngayCheck = ctnl.getNgay().toString().split(" ")[0];
            if(ngayCheck.equals(ngay)){
                check = 1;
                break;
            }
        }
        String[] tmp = ngay.split("-");
        String ngayInput = tmp[0]+"-"+tmp[1]+"-"+tmp[2];
        int result = 0;
        if (check == 1) {
            result = ct_nguyenLieuRepository.updateCTNL(maNCC,idNL,gia,ngayInput);
        } else {
            result = ct_nguyenLieuRepository.insertCTNL(maNCC,idNL,gia,ngayInput);
        }
        return "redirect:http://localhost:8080/ct-nha-cung-cap?maNCC="+maNCC+"&idNL="+idNL;
    }

    @PostMapping(value = {"/them-nl"})
    public String postAddCTNL(Model model,
                           @RequestParam(value = "idNL") int idNL,
                           @RequestParam(value = "maNCC") String maNCC,
                           @RequestParam(value = "ngay") String ngay,
                           @RequestParam(value = "gia") Double gia
    ) {

        String[] tmp = ngay.split("-");
        String ngayInput = tmp[0]+"-"+tmp[1]+"-"+tmp[2];

        int check = ct_nguyenLieuRepository.insertCTNL(maNCC,idNL,gia,ngayInput);
        List<CT_NguyenLieu> ctnls = ct_nguyenLieuRepository.getNL_NCC(maNCC);
        List<NguyenLieu> nls = nguyenLieuRepository.getNL_NCC_X(maNCC);

        model.addAttribute("listNL",ctnls);
        model.addAttribute("listNL1", nls);
        return "redirect:http://localhost:8080/nha-cung-cap";
    }

    @PostMapping("/dat-nguyen-lieu/api")
    @ResponseBody
    public int getDonAPI(@RequestParam(value = "ngay") String ngay,
                         @RequestBody List<Map<String,Object>> data) {
        String maNV = "abc";
        String[] tmp = ngay.split("-");
        String ngayInput = tmp[2]+"-"+tmp[1]+"-"+tmp[0];
        int maDon = nguyenLieuRepository.insetDDNL(ngayInput,maNV);
        for (Map<String, Object> record : data) {
            int idNL = Integer.parseInt((String) record.get("idNguyenLieu"));
            String maNCC = (String) record.get("maNCC");
            Double soLuong = Double.parseDouble((String) record.get("soLuong"));
            int result = nguyenLieuRepository.insetCTDDH(maDon,idNL,maNCC,soLuong);
        }
        return 1;
    }

    @GetMapping("/don-phieu-nhap")
    public String getDon_PhieuNhap(Model model) {
        List<DonDatNguyenLieu> donDatNguyenLieus = donDatNguyenLieuRepository.findAll();
        List<CheckDon> checkDons = new ArrayList<>();
        for(DonDatNguyenLieu x : donDatNguyenLieus) {
            int check = 0;
            List<Map<String,Object>> ctDonDatHangs = ct_donDatHangRepository.getCT_DDH_X(x.getIdDonDatNguyenLieu(),x.getNgayDat().toString().split(" ")[0]);
            for (Map<String, Object> y : ctDonDatHangs) {
                if((Double)y.get("SoLuong")>0) {
                    check = 1;
                    break;
                }
            }
            CheckDon checkDon = new CheckDon(x,check);
            checkDon.setNgayDat(x.getNgayDat().toString());
            checkDons.add(checkDon);
        }

        model.addAttribute("dons",checkDons);
        return "donphieunhap";
    }

    @GetMapping("/phieu-nhap")
    public String taoPhieuNhap(Model model,
                               @RequestParam(value = "idDon") int idDon,
                               @RequestParam(value = "ngay") String ngay) {
        String[] tmp = ngay.toString().split("-");
        String inputDay = tmp[2]+"-"+tmp[1]+"-"+tmp[0];
        List<Map<String,Object>> ctDonDatHangs = ct_donDatHangRepository.getCT_DDH_X(idDon,inputDay);
        LocalDate currentDate = LocalDate.now();
        String[] tmp1 = currentDate.toString().split("-");
        String outputDay = tmp1[2]+"-"+tmp1[1]+"-"+tmp1[0];

        model.addAttribute("currDay", outputDay);
        model.addAttribute("listNL", ctDonDatHangs);
        return "phieunhap";
    }
    @PostMapping("/phieu-nhap/api")
    @ResponseBody
    public String setPhieuNhapAPI(@RequestParam(value = "ngay") String ngay,
                         @RequestBody List<Map<String,Object>> data) {
        String maNV = "abc";
        String[] tmp = ngay.split("-");
        String ngayInput = tmp[2]+"-"+tmp[1]+"-"+tmp[0];
        String result = "";
        int soPhieu = phieuNhapHangRepsitory.insertPN(ngayInput,maNV);
        for (Map<String, Object> record : data) {
            int idCTDonDatHang = Integer.parseInt((String) record.get("idCTDonDatHang"));
            int idNL = Integer.parseInt((String) record.get("idNguyenLieu"));
            Double soLuong = Double.parseDouble((String) record.get("soLuong"));
            Double gia = Double.parseDouble((String) record.get("gia"));
            result = ct_phieuNhapHangRepository.insertCTPN(idCTDonDatHang,soLuong,gia,soPhieu,idNL);
        }
        return result;
    }
}
