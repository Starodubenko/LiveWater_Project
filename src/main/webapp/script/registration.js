$('#goRegistration').click(function () {

    $('form.registration').children('div').removeClass("has-error");
    $('form.registration').children('div').children('p').html("");

    $.post("ajaxClientRegistration", $('#regForm').serialize(), function (data) {

        if (typeof data != "object") {
            $('#message').html(data);
        } else {
            if (data.goToPC != null) {
                //window.location.replace(data.goToPC);
                $('#Reg-successful').modal('toggle');
            }
            else {

                $(".input-block").addClass("has-success");

                $.each(data, function(key, value) {
                    $.get("translate",
                        {
                            value:value
                        },
                        function (data) {
                            $("#"+key+"Div").addClass("has-error");
                            $("#"+key+"Input").html(data);
                            //$("#"+key+"Input").show().delay(1500).fadeOut();
                        })
                });
            }
        }
    })
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$('#Reg-Go-To-PC').click(function(){
   location.href = "client";
});

$('#Reg-Ok').click(function(){
    location.href = "welcome";
});
