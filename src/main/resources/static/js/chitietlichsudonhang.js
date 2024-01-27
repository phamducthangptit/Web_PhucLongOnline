var tenDangNhap;
$(document).ready(function(){
    var idDonHang = document.getElementById("idDonHang").value;
    console.log(idDonHang);
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
         url: 'https://localhost:7062/api/DatHang/chi-tiet-hoa-don',
         contentType: 'application/json',
         data: {idDonHang: idDonHang},
         success: function(data){
            if(data.length > 0){
                hienThiChiTietSanPhamTrongHoaDon(data);
            }
         },
         error: function(error){
             console.log(error);
         }
    });
})
function hienThiChiTietSanPhamTrongHoaDon(data){
    var hienThiSanPham = $('#hien-thi-lich-su');
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

        cardHTML += '<td class="text-center align-middle">' + sanPham.tenSanPham + '</td>';

        cardHTML += '<td class="text-center align-middle">' + sanPham.tenSize + '</td>';
        cardHTML += '<td class="text-center align-middle">' + sanPham.giaBan.toLocaleString() + 'đ</td>';
        cardHTML += '<td class="text-center align-middle">' + sanPham.soLuong + '</td>';
        cardHTML += '<td class="text-center align-middle">' + sanPham.thanhTien.toLocaleString() + 'đ</td>';

        cardHTML += '</tr>';
        hienThiSanPham.append(cardHTML);
    });
 }
