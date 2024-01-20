
// Wait for the document to be ready
$(document).ready(function () {
    // Attach click event to the login button
    $("#loginButton").click(function () {
        // Get username and password values
        var username = $("#username").val();
        var password = $("#password").val();
        // Create a data object to send with the AJAX request
        var data = {
            username: username,
            password: password
        };
        console.log(data);
        // Send AJAX request
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/login",  // Replace with your actual server endpoint
            data: JSON.stringify(data),
            dataType: "json",
            success: function (data) {
                // Handle the server response (success)
                console.log(data);
                // You can redirect or perform other actions based on the response
                if (data.data === "ok") {
                    alert("Đăng nhập thành công");
                    window.location.href = "";
                }
                else {
                    $("#notification").html(data.data);
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
        window.location.href = "/signup"
    });
    $("#forgot").click(function () {
        window.location.href = "/forgot"
    });
});