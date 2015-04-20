
$(document).ready(function () {
    function doLogin(){
        $.post("ajaxLogin", $('#loginform').serialize(),
            function (data) {
                if (data.loginError == null) location.href = data.roleView;
                else $.get("translate",
                    {
                        value:data.loginError
                    },
                    function (data) {
                        $('#errorLogin').show().delay(2500).fadeOut();
                        $('#errorLogin').html(data);
                    })

            })
    }

    $('#goLogin').click(function () {
        doLogin();
    });

    $('#loginform').keydown(function () {
        if (event.keyCode == 13) {
            doLogin();
        }
    });
});

