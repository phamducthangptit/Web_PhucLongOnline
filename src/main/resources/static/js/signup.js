
function getSelectedGender() {
    // Lấy tất cả các input radio có name là "gender"
    var radioButtons = document.getElementsByName("gender");

    // Duyệt qua từng input radio và kiểm tra xem nó đã được chọn hay chưa
    for (var i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            // Nếu đã chọn, in giá trị của input radio đó
            return radioButtons[i].value; // Dừng vòng lặp khi đã tìm thấy input radio đã chọn
        }
    }
}

$(document).ready(function () {
    // Attach click event to the login button
    $("#closeModal").click(function(){
        $("#modal").hide();
    })

    $("#submitCode").click(function(){
        var code = $("#code").val();
        if(code.trim() === ""){
            $("#notificationModal").html("Vui lòng nhập mã code đã gửi");
        }
        var data={
            code:code
        }
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/checkCode",  // Replace with your actual server endpoint
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                // Handle the server response (success)
                console.log(response.status);
                // You can redirect or perform other actions based on the response
                if(response.status === "failed"){
                    console.log(response.message);
                }
                else{
                    console.log(response.message);
                    // window.location.href = "/";
                }
            },
            error: function (error) {
                // Handle the server response (error)
                console.error(error);
                // You can show an error message or perform other actions based on the error
            }
        });
    });

    $("#signup").click(function () {
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var gender = getSelectedGender();
        var address = $("#address").val();
        var sdt = $("#sdt").val();
        var email = $("#email").val();
        var mk = $("#password").val();
        if (firstname.trim() === "") {
            $("#notification").html("Vui lòng điền đầy đủ thông tin");
            return;
        }

        if (lastname.trim() === "") {
            $("#notification").html("Vui lòng điền đầy đủ thông tin");
            return;
        }

        if (gender === null) {
            $("#notification").html("Vui lòng điền đầy đủ thông tin");
            return;
        }

        if (address.trim() === "") {
            $("#notification").html("Vui lòng điền đầy đủ thông tin");
            return;
        }

        if (sdt.trim() === "") {
            $("#notification").html("Vui lòng điền đầy đủ thông tin");
            return;
        }

        if (email.trim()==="") {
            $("#notification").html("Vui lòng điền đầy đủ thông tin");
            return;
        }

        if (mk.trim() === "") {
            $("#notification").html("Vui lòng điền đầy đủ thông tin");
            return;
        }
        var data = {
            firstname: firstname,
            lastname: lastname,
            gender: gender,
            address: address,
            sdt: sdt,
            email: email,
            mk: mk
        }
        console.log(data)


        // Send AJAX request
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/signup",  // Replace with your actual server endpoint
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                // Handle the server response (success)
                console.log(response.status);
                // You can redirect or perform other actions based on the response
                if(response.status === "failed"){
                    console.log("Email đã tồn tại");
                }
                else{
                    console.log("Lấy thông tin khách hàng thành công");
                    $("#modal").show();
                }
            },
            error: function (error) {
                // Handle the server response (error)
                console.error(error);
                // You can show an error message or perform other actions based on the error
            }
        });
    });
});