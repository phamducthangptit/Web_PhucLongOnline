
$(document).ready(function () {
    // Attach click event to the login button
    $("#submitButton").click(function () {
        var email = $("#email").val();
        if (email.trim() === "") {
            $("#notification").html("Vui lòng nhập thông tin");
            return;
        }
        var data = {
            email: email
        }
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/forgot",  // Replace with your actual server endpoint
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                // Handle the server response (success)
                console.log(response.message);
                if (response.status === "ok") {
                    $("#email").css("display", "none");
                    $("#password").css("display", "block");
                    $("#code").css("display", "block");
                    $("#submitButton").css("display","none");
                    $("#submitButton2").css("display","block");
                    $("#notification").html("Vui lòng nhập mã xác nhận");
                } else {
                    $("#notification").html("Email không tồn tại");
                }
                // You can redirect or perform other actions based on the response
            },
            error: function (error) {
                // Handle the server response (error)
                console.error(error);
                // You can show an error message or perform other actions based on the error
            }
        });


    })
    $("#submitButton2").click(function () {
        var email = $("#email").val();
        var password = $("#password").val();
        var code = $("#code").val();

        if (password.trim() === "") {
            $("#notification").html("Vui lòng nhập đầy đủ thông tin");
            return;
        }
        if (code.trim() === "") {
            $("#notification").html("Vui lòng nhập đầy đủ thông tin");
            return;
        }
        var data = {
            email: email,
            password: password,
            code:code
        }
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/reset",  // Replace with your actual server endpoint
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                // Handle the server response (success)
                console.log(response.message);
                if (response.status === "ok") {
                    alert("Cập nhật mật khẩu thành công");
                    window.location.href = "/";
                } else {
                    $("#notification").html(response.message);
                }
                // You can redirect or perform other actions based on the response
            },
            error: function (error) {
                // Handle the server response (error)
                console.error(error);
                // You can show an error message or perform other actions based on the error
            }
        });
    });
});