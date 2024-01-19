var tenDangNhap;
$(document).ready(function(){

    tenDangNhap = document.getElementById("tenDangNhap").value;
    function LoadSoLuongSanPhamTrongGioHang(){
        console.log("load số lượng");
        // load lại số lượng trong giỏ hàng
         $.ajax({
             type: 'GET',
             url: 'https://localhost:7062/api/DatHang/get-so-luong-san-pham-trong-gio-hang',
             contentType: 'application/json',
             data: {tenDangNhap: tenDangNhap},
             success: function(data){
                console.log("abccc");
                var soLuongSPTrongGH = document.getElementById("soLuongSPTrongGioHang");
                soLuongSPTrongGH.innerText = data.soLuongSanPhamTrongGH;
             },
             error: function(error){
                 console.log(error);
             }
        });
    }
    LoadSoLuongSanPhamTrongGioHang();

    $.ajax({
        type: 'GET',
        url: 'https://localhost:7062/api/DatHang/tat-ca-san-pham-trong-don-hang',
        contentType: 'application/json',
        data: {tenDangNhap: tenDangNhap},
        success: function(data){
            if(data.length > 0){
                hienThiDanhSachSanPhamTrongDonHang(data);
                tinhTongSLVaTongTien(tenDangNhap);
            }
            console.log(data);
        },
        error: function(error){
            console.log(error);
        }
    });
})
function hienThiDanhSachSanPhamTrongDonHang(data){
    var hienThiSanPham = $('#hien-thi-san-pham');
        hienThiSanPham.empty();
        $.each(data, function(index, sanPham) {
            var cardHTML = ' <tr>';
            cardHTML += '<td class="col-md-2">';
            cardHTML += '<div class="card mb-2 product-wap rounded-0">';
            cardHTML += '<div class="card rounded-0">';
            cardHTML += '<img class="card-img rounded-0 img-fluid" src="/img/sanpham/' + sanPham.hinhAnh + '">';
            cardHTML += '</div>';
            cardHTML += '</div>';
            cardHTML += '</td>';

            cardHTML += '<td class="text-center align-middle">' + sanPham.tenSanPham + "</td>";

            cardHTML += '<td class="text-center align-middle">' + sanPham.tenSize + "</td>";
            cardHTML += '<td class="text-center align-middle">' + sanPham.giaBan.toLocaleString() + "đ</td>";

            cardHTML += '<td class="col-md-2 text-center align-middle">';
            cardHTML += '<li class="list-inline-item" id="so-luong-sp"><span class="badge bg-secondary var-value" id="so-luong-sp-' + sanPham.idSanPhamSize + '">' + sanPham.soLuong + '</span></li>';
            cardHTML += '</td>';

            cardHTML += '<td class="text-center align-middle">' + (sanPham.giaBan*sanPham.soLuong).toLocaleString() + "đ</td>";

            cardHTML += '</tr>';
            hienThiSanPham.append(cardHTML);
        });
        var tmp = '<tr>'
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td class="col-md-1 text-center align-middle" style="font-weight: bold;">Tổng</td>';
        tmp += '<td class="col-md-1 text-center align-middle" id="tongSLSanPhamDaChon" style="font-weight: bold;"></td>';
        tmp += '<td class="col-md-1 text-center align-middle" id="tongTien" style="font-weight: bold;"></td>';
        tmp += '<input type="hidden" id="tongTienThanhToan" value="0">';
        tmp += '</tr>';

        tmp += '<tr>';
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td class="col-md-1 text-center align-middle" style="font-weight: bold; white-space: nowrap;">Số điện thoại:</td>';
        tmp += '<td class="col-md-1 text-center align-middle" style="font-weight: bold;">'
        tmp += '<input  id="SDT" required>';
        tmp += '<td></td>';
        tmp += '</tr>';

        tmp += '<tr>';
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td class="col-md-1 text-center align-middle" style="font-weight: bold; white-space: nowrap;">Địa chỉ giao hàng:</td>';
        tmp += '<td class="col-md-1 text-center align-middle" style="font-weight: bold;">'
        tmp += '<input  id="diaChiGiaoHang" required >';
        tmp += '<td></td>';
        tmp += '</tr>';

        tmp += '<tr>'
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td></td>';
        tmp += '<td>';
        tmp += '<button type="button" class="btn btn-success btn-xs btn-mua-hang" style="margin-top: 20px;" onclick="thanhToanOnline()">Thanh toán Online</button>';
        tmp += '</td>';
        tmp += '<td>';
        tmp += '<button type="button" class="btn btn-success btn-xs btn-mua-hang" style="margin-top: 20px; white-space: nowrap;" onclick="shipCOD()">Ship COD</button>';
        tmp += '</td>';
        tmp += '</tr>'



        hienThiSanPham.append(tmp);
}
function tinhTongSLVaTongTien(tenDangNhap){
    console.log("tính toong sl va tien");
    $.ajax({
        type: 'GET',
        url: 'https://localhost:7062/api/DatHang/tong-so-luong-va-tien-don-hang',
        contentType: 'application/json',
        data: {tenDangNhap: tenDangNhap},
        success: function(data){
            var tongSLSanPham = document.getElementById("tongSLSanPhamDaChon");
            var tongTien = document.getElementById("tongTien");
            var tongTienThanhToan = document.getElementById("tongTienThanhToan");
            tongSLSanPham.innerText = data.tongSoLuong;
            tongTien.innerText = data.tongTien.toLocaleString() + 'đ';
            tongTienThanhToan.value = data.tongTien;
        },
        error: function(error){
            console.log(error);
        }
    });
}

function thanhToanOnline(){ // thanh toán online thì cần lấy mã trả về để ra bill
    console.log("thanh toasn online");
    var diaChi = document.getElementById("diaChiGiaoHang").value;
    var SDT = document.getElementById("SDT").value;
    var tongTien = document.getElementById("tongTienThanhToan").value;
    $.ajax({
        type: 'POST',
        url: 'https://localhost:7062/api/VnPay/thanh-toan-don-hang/' + tenDangNhap + '/' + tongTien + '/' + diaChi + '/' + SDT,
        contentType: 'application/json',
        success: function(data){
            window.location.href = data.url;
        },
        error: function(error){
            console.log(error);
        }
    });

}

function shipCOD(){ // ship code ra bill luôn
    console.log("Ship cod");
    var diaChi = document.getElementById("diaChiGiaoHang").value;
    var SDT = document.getElementById("SDT").value;
    $.ajax({
        type: 'GET',
        url: 'https://localhost:7062/api/DatHang/thuc-hien-tao-hoa-don',
        contentType: 'application/json',
        data: {tenDangNhap: tenDangNhap, diaChi: diaChi, SDT: SDT},
        success: function(data){
            alert("Bạn đã đặt hàng thành công");
            window.location.href = "http://localhost:8080/lich-su-don-hang";
        },
        error: function(error){
            console.log(error);
        }
    });
}
