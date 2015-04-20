$(document).ready(function () {
    $('.table-data-base').on('click', '.order-function-button', function () {

        var className = this.className.split(/\s+/)[1];
        var id = this.value;
        var button = this;

        $.get("orderOperation2",
            {
                actor: "dispatcher",
                typeOfOperation: className,
                id: id
            },
            function (data) {
                var operation = data.newStatus;
                $.get("translate",
                    {
                        value: data.newStatus
                    },
                    function (data) {
                        var count = $(button).closest('tr').children('td').size();
                        $($(button).closest('tr').children('td').get(count-2)).children('label').text(data.trim());
                        $.get("getOperationButton",
                            {
                                operation: operation,
                                id: id
                            },
                            function (data) {
                                $(button).closest('td').html(data.trim());
                            });
                    });
            });
    });

    $('.dataBase').on('click', '.rowsCount', function () {
        changeEntityName();
    });

    $('#confirmModal').on('click', '#confirmSave', function () {

        var table = $('#entityName').val();

        $.post("save" + table + "Data", $('#editForm').serialize(),
            function (data) {
                $('#saveMessage').html(data.errorMessage);
            })
    });

    $(function () {
        $('body').on('click', '.datepicker', function () {
            $(this).datepicker(
                {
                    format: "dd.mm.yyyy",
                    startDate: '+0d'
                }).focus();
        });
    });

    $(function () {
        $('body').on('click', '.datepickerNotBlocked', function () {
            $(this).datepicker(
                {
                    format: "dd.mm.yyyy"
                }).focus();
        });
    });

    $('.dataBase').on('click', '.createOrder', function () {
        var id = $(this).val();
        $.get("setUserAndCart", {userId: id}, function (data) {
            $('#orderForm').html(data);
        })
    });

    $('#orderForm').on('keyup', '#searchGoods', function () {

        var searchGoods = $('#searchGoods').val();

        $.get("findGoodsOnCreateFormForDispatcher",
            {
                searchGoods: searchGoods
            },
            function (data) {
                $('.goodsForDispatcher').html(data);
            })
    });


    var GoodsName;
    $('#orderForm').on('click', 'tr', function () {
        $('#orderForm tr').removeClass('info');
        $(this).addClass('info');

        GoodsName = $($(this).children('td').get(0)).text().trim();
    });

    $('#orderForm').on('click', '.ra-button.add', function () {
        $.post("addGoodsDispatcher", {goods_name: GoodsName},
            function (data) {
                $('.orderedGoodsForDispatcher').html(data);
                $.post("cartCalculate",
                    function (data) {
                        $("#totalPrice").html(data.total);
                        $("#discount").html(data.discount);
                        $("#totalPriceWithDiscount").html(data.totalWithDiscount);
                    });
            });
    });

    $('#orderForm').on('click', '.ra-button.remove', function () {
        $.post("removeGoodsDispatcher", {goods_name: GoodsName},
            function (data) {
                $('.orderedGoodsForDispatcher').html(data);
                $.post("cartCalculate",
                    function (data) {
                        $("#totalPrice").html(data.total);
                        $("#discount").html(data.discount);
                        $("#totalPriceWithDiscount").html(data.totalWithDiscount);
                    });
            });
    });

    $('#orderForm').on('keyup', '.goodsCount', function () {
        var goodsname = $($($(this).parents('tr').get(0)).children('td').get(0)).text().trim();
        var goodscount = $(this).val().trim();

        $.get("set-goods-count-dispatcher",
            {
                goods_name: goodsname,
                goods_count: goodscount
            },
            function (data) {
                $("#goodsPrice" + data.id + "").html(data.cost);
                $("#totalPrice").html(data.total);
                $("#discount").html(data.discount);
                $("#totalPriceWithDiscount").html(data.totalWithDiscount);
            });
    });
});

