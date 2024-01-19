$(document).ready(function(){
    var tenDangNhap = document.getElementById("tenDangNhap").value;
    var loaiSanPham = document.getElementById("loaisanpham").value;
    $.ajax({
        type: 'GET',
        url: 'https://localhost:7062/api/DatHang/danh-sach-loai-san-pham',
        contentType: 'application/json',
        success: function(data){
            HienThiLoaiSanPham(data);
        },
        error: function(error){
            console.log(error);
        }
    });

    // hiển thị số lượng sản phẩm đã có trong giỏ hàng
    LoadSoLuongSanPhamTrongGioHang();
    $.ajax({
        type: 'GET',
        url: 'https://localhost:7062/api/DatHang/danh-sach-san-pham',
        contentType: 'application/json',
        success: function(data){
            console.log("có: " + data.length);
            if(loaiSanPham === "tatca"){
                HienThiDanhSachSanPham(data);
            } else {
                HienThiDanhSachSanPhamTheoLoai(data, loaiSanPham);
            }

        },
        error: function(error){
            console.log(error);
        }
    });


    $(document).on('click', '.addCart', function () {
        openPopup();
        var productId = $(this).data('product-id');
        var tenVaAnh = null;
        $.ajax({
            type: 'GET',
            url: 'https://localhost:7062/api/DatHang/get-ten-va-hinh-anh',
            contentType: 'application/json',
            data: { id: productId },
            success: function(data){
                HienThiTenVaAnh(data);
            },
            error: function(error){
                console.log(error);
            }
        });

        $.ajax({
            type: 'GET',
            url: 'https://localhost:7062/api/DatHang/get-size-san-pham',
            contentType: 'application/json',
            data: { id: productId },
            success: function(data){
                HienThiSize(data, productId);
                console.log(data);
            },
            error: function(error){
                console.log(error);
            }
        });
    });

    function openPopup() {
        document.getElementById("cartPopup").style.display = "block";
    }

    function HienThiTenVaAnh(data){
        var imgElement = document.getElementById("anh-san-pham");
        imgElement.src = "/img-sanpham/" + data.hinhAnh;

        var tenSP = document.getElementById("ten-san-pham");
        tenSP.innerText  = data.tenSanPham;
    }
    var sizeChon;
    function HienThiSize(data, productId) {
        console.log("vao ham size");
        var sizeList = $('#sizeList');
        sizeList.empty();
        var cardHTML = '<li class="list-inline-item">Size : </li>';
        cardHTML += '<input id="productId" hidden value="' + productId + '"/>';
        sizeList.append(cardHTML);
        var isFirstSizeSelected = false;
        $.each(data, function(index, sizes){
            var cardTmp = '<li class="list-inline-item"><span data-size-id="' + sizes.idSize + '" class="btn btn-size btn-success">' + sizes.tenSize + '</span></li>';
            sizeList.append(cardTmp);
            if (!isFirstSizeSelected) {
                isFirstSizeSelected = true;
                // Xử lý sự kiện click ở đây (mặc định chọn size đầu tiên)
                console.log('Size đầu tiên', sizes.idSize);
                // Đặt màu sắc cho nút được chọn mặc định
                var sizeButton = sizeList.find('[data-size-id="' + 1 + '"]');
                sizeChon = 1;
                sizeButton.removeClass('btn-success').addClass('btn-secondary');
                $.ajax({
                    type: 'GET',
                    url: 'https://localhost:7062/api/DatHang/get-gia-theo-san-pham-va-size',
                    contentType: 'application/json',
                    data: { idSanPham: productId, idSize : 1 },
                    success: function(data){
                        var giaHienThoi = document.getElementById("gia-theo-size");
                        giaHienThoi.innerText = "Giá: " + data.toLocaleString() + "đ";
                    },
                    error: function(error){
                        console.log(error);
                    }
                });
            }
            // Thêm sự kiện click cho mỗi nút kích thước
            var sizeButton = sizeList.find('[data-size-id="' + sizes.idSize + '"]');
            sizeButton.on('click', function() {
                // Xử lý sự kiện click ở đây
                console.log('Bạn đã chọn size có id:', sizes.idSize);
                sizeChon = sizes.idSize;

                $.ajax({
                    type: 'GET',
                    url: 'https://localhost:7062/api/DatHang/get-gia-theo-san-pham-va-size',
                    contentType: 'application/json',
                    data: { idSanPham: productId, idSize : sizes.idSize },
                    success: function(data){
                        var giaHienThoi = document.getElementById("gia-theo-size");
                        giaHienThoi.innerText = "Giá: " + data.toLocaleString() + "đ";
                    },
                    error: function(error){
                        console.log(error);
                    }
                });
                // Reset màu sắc cho tất cả các nút kích thước
                sizeList.find('.btn-size').removeClass('btn-secondary').addClass('btn-success');
                // Đặt màu sắc cho nút được click
                sizeButton.removeClass('btn-success').addClass('btn-secondary');
            });
        });
    }
        $(document).on('click', '#addtocard', function () {
            console.log("them vao gio hang");
            var idSP = document.getElementById("productId").value;
            var quantitySpan = document.getElementById('var-value');

            // Lấy giá trị số lượng từ nội dung của thẻ span
            var soLuongSP = quantitySpan.textContent;

            $.ajax({
                type: 'GET',
                url: 'https://localhost:7062/api/DatHang/check-ton-tai-san-pham-trong-gh',
                contentType: 'application/json',
                data: { tenDangNhap: tenDangNhap, idSanPham: idSP, idSize : sizeChon },
                success: function(data){
                     console.log(data);
                     if(data.soLuong === 1){ //đã có sản phẩm đó trong gh
                        $.ajax({
                            type: 'GET',
                            url: 'https://localhost:7062/api/DatHang/them-so-luong-san-pham-vao-gh',
                            contentType: 'application/json',
                            data: {tenDangNhap: "ducthang", idSanPham: idSP, idSize: sizeChon, soLuong: soLuongSP },
                            success: function(data){
                                console.log("thêm số lượng thành công");
                                LoadSoLuongSanPhamTrongGioHang();
                            },
                            error: function(error){
                                console.log(error);
                            }
                        });
                     } else{ // chưa có sản phẩm đó trong gh
                        $.ajax({
                            type: 'GET',
                            url: 'https://localhost:7062/api/DatHang/them-san-pham-moi-vao-gh',
                            contentType: 'application/json',
                            data: {tenDangNhap: tenDangNhap, idSanPham: idSP, idSize: sizeChon, soLuong: soLuongSP },
                            success: function(data){
//                                console.log("thêm san phẩm thành công");
                                LoadSoLuongSanPhamTrongGioHang();
                            },
                            error: function(error){
                                console.log(error);
                            }
                        });
                     }
                },
                error: function(error){
                    console.log(error);
                }
            });
            document.getElementById("cartPopup").style.display = "none";
        });
    function LoadSoLuongSanPhamTrongGioHang(){
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
});
function HienThiDanhSachSanPham(data){
    console.log("vào hàm hiển thị sản phẩm");
    var danhSachSanPhamContainer = $('#danh-sach-san-pham');
    danhSachSanPhamContainer.empty();
    $.each(data, function(index, sanPham) {
    console.log("in danh sach sp");
        var cardHTML = '<div class="col-md-4">';
        cardHTML += '<div class="card mb-4 product-wap rounded-0">';
        cardHTML += '<div class="card rounded-0">';
        cardHTML += '<img class="card-img rounded-0 img-fluid" src="/img-sanpham/' + sanPham.hinhAnh + '">';
        cardHTML += '<div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">';
        cardHTML += '<ul class="list-unstyled">';
        cardHTML += '<li><a data-product-id="' + sanPham.idSanPham + '" class="addCart btn btn-success text-white mt-2"><i class="fas fa-cart-plus"></i></a></li>';
        cardHTML += '</ul>';
        cardHTML += '</div>';
        cardHTML += '</div>';
        cardHTML += '<div class="card-body">';
        cardHTML += '<a class="h3 text-decoration-none" style="color: #28a745; font-weight: bold;">' + sanPham.tenSanPham + '</a>';
        cardHTML += '<p class="text-center mb-0" style="color: #dc3545;">' + sanPham.giaMin.toLocaleString() + 'đ - ' + sanPham.giaMax.toLocaleString() + 'đ</p>';
        cardHTML += '</div>';
        cardHTML += '</div>';
        cardHTML += '</div>';
        // Thêm card HTML vào danh sách sản phẩm

        danhSachSanPhamContainer.append(cardHTML);
    });
    console.log($('.addCart').length + "bbnbnn");
}
function HienThiDanhSachSanPhamTheoLoai(data, loaiSanPham){
    console.log("vào hàm hiển thị sản phẩm theo loaij");
    var danhSachSanPhamContainer = $('#danh-sach-san-pham');
    danhSachSanPhamContainer.empty();
    $.each(data, function(index, sanPham) {
        console.log("in danh sach sp theo loaij");
        if(sanPham.idLoaiSanPham == loaiSanPham){
        var cardHTML = '<div class="col-md-4">';
            cardHTML += '<div class="card mb-4 product-wap rounded-0">';
            cardHTML += '<div class="card rounded-0">';
            cardHTML += '<img class="card-img rounded-0 img-fluid" src="/img-sanpham/' + sanPham.hinhAnh + '">';
            cardHTML += '<div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">';
            cardHTML += '<ul class="list-unstyled">';
            cardHTML += '<li><a data-product-id="' + sanPham.idSanPham + '" class="addCart btn btn-success text-white mt-2"><i class="fas fa-cart-plus"></i></a></li>';
            cardHTML += '</ul>';
            cardHTML += '</div>';
            cardHTML += '</div>';
            cardHTML += '<div class="card-body">';
            cardHTML += '<a class="h3 text-decoration-none" style="color: #28a745; font-weight: bold;">' + sanPham.tenSanPham + '</a>';
            cardHTML += '<p class="text-center mb-0" style="color: #dc3545;">' + sanPham.giaMin.toLocaleString() + 'đ - ' + sanPham.giaMax.toLocaleString() + 'đ</p>';
            cardHTML += '</div>';
            cardHTML += '</div>';
            cardHTML += '</div>';
            // Thêm card HTML vào danh sách sản phẩm

            danhSachSanPhamContainer.append(cardHTML);
    }

    });
    console.log($('.addCart').length + "bbnbnn");
}
function HienThiLoaiSanPham(data){
        var danhSachSanPham = $('#danh-sach-loai-san-pham');
        $.each(data, function(index, lsp){
            var cardHTML = '<li class="list-inline-item"></li>';
            cardHTML += '<a class="h3 text-dark text-decoration-none mr-3" href="/san-pham/' + lsp.idLoaiSanPham + '">' + lsp.tenLoai + '</a>';
            danhSachSanPham.append(cardHTML);
        });
    }