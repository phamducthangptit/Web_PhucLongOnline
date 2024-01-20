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
        url: 'https://localhost:7062/api/DatHang/danh-sach-hoa-don',
        contentType: 'application/json',
        data: {tenDangNhap: tenDangNhap},
        success: function(data){
            if(data.length > 0){
                hienThiDanhSachHoaDon(data);
            }
            console.log(data);
        },
        error: function(error){
            console.log(error);
        }
    });
});
function hienThiDanhSachHoaDon(data){
    var danhSachHoaDon = $('#hien-thi-lich-su');
    danhSachHoaDon.empty();
    $.each(data, function(index, hoaDon) {
        var cardHTML = ' <tr>';
        cardHTML += '<td class="text-center align-middle">' + catChuoiNgayThang(hoaDon.ngayLap) + "</td>";

        cardHTML += '<td class="text-center align-middle">' + hoaDon.tongHoaDon.toLocaleString() + "đ</td>";

        cardHTML += '<td class="text-center align-middle">' + hoaDon.diaChi + "</td>";
        cardHTML += '<td class="text-center align-middle">' + hoaDon.sdt + "</td>";
        cardHTML += '<td class="text-center align-middle">';
        cardHTML += '<a href="chi-tiet-lich-su-don-hang/' + hoaDon.idDonHang + '">Xem chi tiết </a>'
        cardHTML += "</td>";
        cardHTML += '</tr>';
        danhSachHoaDon.append(cardHTML);
    });
}
function catChuoiNgayThang(chuoiNgayThang) {
  // Sử dụng phương thức split để cắt chuỗi từ chữ 'T'
  var parts = chuoiNgayThang.split('T');

  // Trả về phần sau chữ 'T', tức là phần thời gian
  return parts[0];
}