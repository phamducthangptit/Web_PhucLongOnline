var url = 'http://localhost:8080/'
$(document).ready(function () {
    $('#btnNext').click(function (e) {
        e.preventDefault();

        var inputNL = [];
        $(".form-check-input:checked").each(function() {
            var inputVal = $(this).val();
            inputNL.push(inputVal);
        })
        window.location.href = url+'dat-nguyen-lieu?inputNL=' + inputNL;
    });
});

