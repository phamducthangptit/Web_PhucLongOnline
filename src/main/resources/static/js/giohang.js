$(document).ready(function(){
    var tenDangNhap = document.getElementById("tenDangNhap").value;
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
    function hienThiCheckBox(){
        $.ajax({
            type: 'GET',
            url: 'https://localhost:7062/api/DatHang/danh-sach-san-pham-trong-don-hang',
            contentType: 'application/json',
            data: {tenDangNhap: tenDangNhap},
            success: function(data){
                console.log("checkbox");
                console.log(data);
                data.forEach(function (sanPhamId) {
                    var bochonButton = document.getElementById('bochon_' + sanPhamId);
                    var chonButton = document.getElementById('chon_' + sanPhamId);
                    bochonButton.style.display = 'none';
                    chonButton.style.display = 'inline-block';

                });
            },
            error: function(error){
                console.log(error);
            }
        });
    }
    LoadSoLuongSanPhamTrongGioHang();
    $.ajax({
        type: 'GET',
        url: 'https://localhost:7062/api/DatHang/get-tat-ca-san-pham-trong-gh',
        contentType: 'application/json',
        data: {tenDangNhap: tenDangNhap},
        success: function(data){
            if(data.length > 0){
                hienThiDanhSachSanPhamTrongGioHang(data);
//                hienThiCheckBox();
            }
            console.log(data);
        },
        error: function(error){
            console.log(error);
        }
    });
    $(document).on('click', '.btn-minus', function() {
        // Xử lý sự kiện khi nút - được click
        console.log("nut -");
        var idSPSize = $(this).data('product-id');
//        console.log(idSanSPSize);
//        var soLuongSpan = $('#so-luong-sp-' + productId);
        $.ajax({
            type: 'GET',
            url: 'https://localhost:7062/api/DatHang/giam-so-luong-san-pham-trong-gio-hang',
            contentType: 'application/json',
            data: {tenDangNhap: tenDangNhap, idSanPhamSize: idSPSize},
            success: function(data){
                LoadSoLuongSanPhamTrongGioHang();
                hienThiDanhSachSanPhamTrongGioHang(data);
            },
            error: function(error){
                console.log(error);
            }
        });
    });

    $(document).on('click', '.btn-plus', function() {
        // Xử lý sự kiện khi nút + được click
        console.log("nut +");
        var idSPSize = $(this).data('product-id');
//        console.log(idSanSPSize);
        $.ajax({
                type: 'GET',
                url: 'https://localhost:7062/api/DatHang/tang-so-luong-san-pham-trong-gio-hang',
                contentType: 'application/json',
                data: {tenDangNhap: tenDangNhap, idSanPhamSize: idSPSize},
                success: function(data){
                    LoadSoLuongSanPhamTrongGioHang();
                    hienThiDanhSachSanPhamTrongGioHang(data);
                },
                error: function(error){
                    console.log(error);
                }
        });
    });

    $(document).on('click', '.delete-product', function() {
        var idSPSize = $(this).data('product-id');
        // Xử lý khi nút backspace được click cho sản phẩm có ID là productId
//        console.log("xóa: " + idSPSize);
        $.ajax({
                type: 'GET',
                url: 'https://localhost:7062/api/DatHang/delete-san-pham-khoi-gio-hang',
                contentType: 'application/json',
                data: {tenDangNhap: tenDangNhap, idSanPhamSize: idSPSize},
                success: function(data){
                    LoadSoLuongSanPhamTrongGioHang();
                    hienThiDanhSachSanPhamTrongGioHang(data);
                },
                error: function(error){
                    console.log(error);
                }
        });
    });


});

function thaydoitrangthaichon(idSanPham) {
    var bochonButton = document.getElementById('bochon_' + idSanPham);
    var chonButton = document.getElementById('chon_' + idSanPham);

    if (bochonButton.style.display !== 'none') {
        // Nếu nút "bochon" đang hiển thị, ẩn nút "bochon" và hiển thị nút "chon"
        bochonButton.style.display = 'none';
        chonButton.style.display = 'inline-block';
        var bochonSP = document.getElementById('bochonsp_' + idSanPham).value;

        console.log("chọn:" + bochonSP);
    } else {
        // Nếu nút "chon" đang hiển thị, ẩn nút "chon" và hiển thị nút "bochon"
        chonButton.style.display = 'none';
        bochonButton.style.display = 'inline-block';
        var chonSP = document.getElementById('chonsp_' + idSanPham).value;
        console.log("bỏ chọn:" + chonSP);
    }
}

function hienThiDanhSachSanPhamTrongGioHang(data){
    var hienThiSanPham = $('#hien-thi-san-pham');
    hienThiSanPham.empty();
    $.each(data, function(index, sanPham) {
        var cardHTML = ' <tr> <td class="col-md-1 text-center align-middle">';
        cardHTML += '<div class="checkbox-link">';
        cardHTML += '<a class="nav-link" id="bochon_' + sanPham.idSanPhamSize + '" onclick="thaydoitrangthaichon(' + sanPham.idSanPhamSize + ')">';
        cardHTML += '<i class="far fa-square fa-lg" style="color: #000000;"></i>';
        cardHTML += '<input hidden="hidden" id="bochonsp_' + sanPham.idSanPhamSize + '" value="' + sanPham.idSanPhamSize + '">';
        cardHTML += '</a>';
        cardHTML += '<a class="nav-link" style="display: none" id="chon_' + sanPham.idSanPhamSize + '" onclick="thaydoitrangthaichon(' + sanPham.idSanPhamSize + ')">';
        cardHTML += '<i class="far fa-check-square fa-lg" style="color: #000000;"></i>';
        cardHTML += '<input hidden="hidden" id="chonsp_' + sanPham.idSanPhamSize + '" value="' + sanPham.idSanPhamSize + '">';
        cardHTML += '</a>';
        cardHTML += '</div>';
        cardHTML += '</td>';

        cardHTML += '<td class="col-md-2">';
        cardHTML += '<div class="card mb-2 product-wap rounded-0">';
        cardHTML += '<div class="card rounded-0">';
        cardHTML += '<img class="card-img rounded-0 img-fluid" src="/img-sanpham/' + sanPham.hinhAnh + '">';
        cardHTML += '</div>';
        cardHTML += '</div>';
        cardHTML += '</td>';

        cardHTML += '<td class="text-center align-middle">' + sanPham.tenSanPham + "</td>";

        cardHTML += '<td class="text-center align-middle">' + sanPham.tenSize + "</td>";
        cardHTML += '<td class="text-center align-middle">' + sanPham.giaHienThoi.toLocaleString() + "đ</td>";

        cardHTML += '<td class="col-md-2 text-center align-middle">';
        cardHTML += '<div class="col-auto">';
        cardHTML += '<ul class="list-inline pb-3">';
        // Sử dụng class thay vì ID cho sự kiện click
        cardHTML += '<li class="list-inline-item"><span class="btn btn-success btn-minus" data-product-id="' + sanPham.idSanPhamSize + '">-</span></li>';
        cardHTML += '<li class="list-inline-item" id="so-luong-sp"><span class="badge bg-secondary var-value" id="so-luong-sp-' + sanPham.idSanPhamSize + '">' + sanPham.soLuong + '</span></li>';
        cardHTML += '<li class="list-inline-item"><span class="btn btn-success btn-plus" data-product-id="' + sanPham.idSanPhamSize + '">+</span></li>';


        cardHTML += '</ul>';
        cardHTML += '</div>';
        cardHTML += '</td>';

        cardHTML += '<td class="text-center align-middle">' + (sanPham.giaHienThoi*sanPham.soLuong).toLocaleString() + "đ</td>";

        cardHTML += '<td class="text-center align-middle">';
        cardHTML += '<a class="delete-product" data-product-id="' + sanPham.idSanPhamSize + '">';
        cardHTML += '<i class="fas fa-backspace fa-lg" style="color: #000000;"></i>';
        cardHTML += '</a>';
        cardHTML += '</td>';
        cardHTML += '</tr>';
        hienThiSanPham.append(cardHTML);
    });
    var tmp = '<td></td>';
    tmp += '<td></td>';
    tmp += '<td></td>';
    tmp += '<td></td>';
    tmp += '<td></td>';
    tmp += '<td></td>';
    tmp += '<td></td>';
    tmp += '<td class="col-md-1 text-center align-middle">';
    tmp += '<button type="submit" class="btn btn-success btn-xs btn-mua-hang" style="margin-top: 20px ;">Mua hàng</button>';
    tmp += '</td>';
    hienThiSanPham.append(tmp);

}

//document.addEventListener('DOMContentLoaded', function () {
//    // Lấy tham chiếu đến nút "Mua hàng"
//    var muaHangButton = document.querySelector('.btn-mua-hang');
//
//    // Thêm sự kiện click
//    muaHangButton.addEventListener('click', function () {
//        // Thực hiện các hành động khi nút được nhấp vào ở đây
//        alert('Nút "Mua hàng" đã được nhấp!');
//    });
//});
