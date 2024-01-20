var trangThaiDonHang;
$(document).ready(function(){
    trangThaiDonHang = document.getElementById("trang-thai-don").value;
    $.ajax({
         type: 'GET',
         url: 'https://localhost:7062/api/DatHang/danh-sach-don-hang',
         contentType: 'application/json',
         data: {trangThai: trangThaiDonHang},
         success: function(data){
            HienThiDanhSachDonHang(data);
         },
         error: function(error){
             console.log(error);
         }
    });
});
function HienThiDanhSachDonHang(data){
    var danhSachDonHang = $('#danh-sach-don-hang');
    danhSachDonHang.empty();
    $.each(data, function(index, donHang) {
        var cardHTML = ' <tr>';
        cardHTML += '<td class="text-center align-middle">' + donHang.idDonHang + "</td>";

        cardHTML += '<td class="text-center align-middle">' + catChuoiNgayThang(donHang.ngayLap) + "</td>";

        cardHTML += '<td class="text-center align-middle">' + donHang.trangThaiDonHang + "</td>";
        if(donHang.trangThaiDonHang == "Đã hoàn thành"){
            cardHTML += '<td></td>';
        } else{
            cardHTML += '<td class="text-center align-middle">';
            cardHTML += '<button type="button" class="btn btn-success btn-xs btn-mua-hang" onclick="thayDoiTrangThaiDH(' + donHang.idDonHang + ')">Thay đổi trạng thái </button>';
            cardHTML += "</td>";
        }


        cardHTML += '<td class="text-center align-middle">';
        cardHTML += '<a href="chi-tiet-don-hang/' + donHang.idDonHang + '">Xem chi tiết </a>'
        cardHTML += "</td>";
        cardHTML += '</tr>';
        danhSachDonHang.append(cardHTML);
    });
}

function catChuoiNgayThang(chuoiNgayThang) {
  // Sử dụng phương thức split để cắt chuỗi từ chữ 'T'
  var parts = chuoiNgayThang.split('T');

  // Trả về phần sau chữ 'T', tức là phần thời gian
  return parts[0];
}

function thayDoiTrangThaiDH(idDH){
    if(trangThaiDonHang == "Chưa xác nhận"){
        $.ajax({
             type: 'GET',
             url: 'https://localhost:7062/api/DatHang/thay-doi-trang-thai-don-hang',
             contentType: 'application/json',
             data: {idDonHang : idDH,  trangThai: "Đã xác nhận"},
             success: function(data){
                window.location.href = "http://localhost:8080/don-hang/chua-xac-nhan";
             },
             error: function(error){
                 console.log(error);
             }
        });
    } else if(trangThaiDonHang == "Đã xác nhận"){
        $.ajax({
             type: 'GET',
             url: 'https://localhost:7062/api/DatHang/thay-doi-trang-thai-don-hang',
             contentType: 'application/json',
             data: {idDonHang : idDH,  trangThai: "Đã hoàn thành"},
             success: function(data){
                window.location.href = "http://localhost:8080/don-hang/da-xac-nhan";
             },
             error: function(error){
                 console.log(error);
             }
        });
    }
}