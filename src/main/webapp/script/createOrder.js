$('.datepicker').datepicker({
    format: "dd.mm.yyyy",
//    daysOfWeekDisabled: "0,6",
//    todayHighlight: true,
    startDate: '+0d'
});

$('#create').click(function () {

    var goodsname = $('.goodsname').val();
    var goodscount = $('.goodscount').val();

    $.get("fastCreateOrder?"+$("#createForm").serialize(),
        {
            goodsname: goodsname,
            goodscount: goodscount
        },
        function (data) {
            $('.final-message').html(data);
            if(data.personalCabinet != null)
                window.location.href = data.personalCabinet;
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
