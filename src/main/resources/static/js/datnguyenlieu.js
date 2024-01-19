

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
        var giaMoi = gia.split(', ')[1];
        document.getElementById('dongia' + idNL).innerText = formatMoney(giaMoi);
}

function formatMoneyToNumber(formattedMoney) {
    const numericValue = formattedMoney.replace(/[^\d]/g, '');

    return parseFloat(numericValue);
}

function sumDon() {
    var trElements = document.getElementsByClassName("ds-nl");
    var sum = 0;
    // Lặp qua từng phần tử <tr>
    for (var i = 0; i < trElements.length; i++) {
        var trElement = trElements[i];
        var soLuong = trElement.querySelector("input[name='soLuong']").value;
        var gia = trElement.querySelector("span[name='donGia']").innerText;
        var soLuongInt = parseInt(soLuong);
        var donGia = formatMoneyToNumber(gia);
        sum += soLuongInt * donGia;
    }
    document.getElementById('tong-tien').innerText = formatMoney(sum);
}
document.addEventListener('DOMContentLoaded', function() {
        var selectNCCs = document.getElementsByClassName("selectNCC");
        for (var i = 0; i < selectNCCs.length; i++) {
            var idNCC = selectNCCs[i].id;
            updateDonGia(idNCC.split('-')[1], selectNCCs[i].value)
        }
        var selectNLs = document.getElementsByClassName("ds-nl");
        for (var i = 0; i < selectNCCs.length; i++) {
            var soLuong = selectNCCs[i].id;
        }
        sumDon();
});

$(document).ready(function() {
    $('#btnDat').on('click', function() {
        var listNL = [];
        var ngayDat = $('#ngayDat').val();
        $('tr.ds-nl').each(function() {
            var nl = {};
            var idNguyenLieu = $(this).find('.nl1').val();
            var soLuong = $(this).find('input[name="soLuong"]').val();
            var maNCC = $(this).find('.selectNCC').val();
            var maNCC1 = maNCC.split(', ')[0];
            nl.idNguyenLieu = idNguyenLieu;
            nl.maNCC = maNCC1;
            nl.soLuong = soLuong;
            listNL.push(nl);
        });

        var don = JSON.stringify(listNL);
        console.log(don)
        $.ajax({
            url: 'http://localhost:8080/dat-nguyen-lieu/api?ngay='+ngayDat,
            method: 'POST',
            data: don,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data)
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
});