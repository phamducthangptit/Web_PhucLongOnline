

function formatMoney(amount) {
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
        minimumFractionDigits: 0,
    });
    return formatter.format(amount).replace(/\s/g, '');
}
function updateDonGia(idNL, gia) {
        if (!gia) return;
        document.getElementById('dongia-' + idNL).innerText = formatMoney(gia);
}


function formatMoneyToNumber(formattedMoney) {
    const numericValue = formattedMoney.replace(/[^\d]/g, '');

    return parseFloat(numericValue);
}

function sumDon() {
    var trElements = document.getElementsByClassName("ds-nl");
    var sum = 0;
    var btnDat = document.getElementById("btnDat");
    // Lặp qua từng phần tử <tr>
    for (var i = 0; i < trElements.length; i++) {
        var trElement = trElements[i];
        var soLuong = trElement.querySelector("input[name='soLuong']").value;
        var gia = trElement.querySelector("span[name='donGia']").innerText;
        var soLuongInt = parseInt(soLuong);
        var donGia = formatMoneyToNumber(gia);
        sum += soLuongInt * donGia;
    }
    if (sum == 0) {
        btnDat.disabled = true;
    } else {
        btnDat.disabled = false;
    }
    document.getElementById('tong-tien').innerText = formatMoney(sum);
}
document.addEventListener('DOMContentLoaded', function() {
        var trElements = document.getElementsByClassName("ds-nl");
        for (var i = 0; i < trElements.length; i++) {
            var trElement = trElements[i];
            var gia = trElement.querySelector("span[name='donGia']").innerText;
            var idDon = trElement.querySelector("span[name='donGia']").id;
            console.log(parseFloat(gia));
            console.log(idDon);
            updateDonGia(idDon.split('-')[1], parseFloat(gia));
        }
        sumDon();
});

$(document).ready(function() {
    $('#btnDat').on('click', function() {
        var listNL = [];
        var ngayDat = $('#ngayDat').val();
        $('tr.ds-nl').each(function() {
            var nl = {};
            var idCTDonDatHang = $(this).find('.nl1').val();
            var idNguyenLieu = $(this).find('.nl2').val();
            var soLuong = $(this).find('input[name="soLuong"]').val();
            var gia = $(this).find('input[name="donGia"]').val();
            nl.idCTDonDatHang = idCTDonDatHang;
            nl.idNguyenLieu = idNguyenLieu;
            nl.soLuong = soLuong;
            nl.gia = gia;
            listNL.push(nl);
        });

        var don = JSON.stringify(listNL);
        console.log(don)
        $.ajax({
            url: 'http://localhost:8080/phieu-nhap/api?ngay='+ngayDat,
            method: 'POST',
            data: don,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data)
                if (data == '1') {
                    window.location.href = "http://localhost:8080/don-phieu-nhap";
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
});