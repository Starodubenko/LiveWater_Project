$('.datepicker').datepicker({
    format: "dd.mm.yyyy",
//    daysOfWeekDisabled: "0,6",
//    todayHighlight: true,
    startDate: '+0d'
});

$(function(){
    $('textarea').html("");
});

$('#create').click(function () {

    $('div').removeClass("has-error");
    $('p').html("");

    $.get("createFullOrder?" + $("#createForm").serialize(),
        function (data) {
            if (typeof data != "object") {
                $('.final-message').html(data);
            } else {
                if (data.goToPC != null) window.location.replace(data.goToPC);
                else {
                    $.each(data, function (key, value) {
                        $.get("translate",
                            {
                                value: value
                            },
                            function (data) {
                                $("#" + key + "Div").addClass("has-error");
                                $("#" + key + "Input").html(data);
                            })
                    });
                }
            }
        });
});

$('.param').change(function () {

    var goodsname = $('.goodsname').val();
    var goodscount = $('.goodscount').val();

    $.get("calculate-order-cost",
        {
            goodsname: goodsname,
            goodscount: goodscount
        },
        function (data) {
            $('.order-cost').html(data.cost);
        });
})
;
