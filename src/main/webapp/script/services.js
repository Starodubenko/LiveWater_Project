//$(function () {
//    $("li.gNumbered[value=" + 1 + "]").addClass("active");
//});

var page;
var rows;
var orderBy;
var entityName;
var filterForm;

function getAdditionalParameters() {
    entityName = "goodsG";
    page = $('#pageNumber').val();
    rows = $('.rowsCount').parents('form').children().children('input').val();
    filterForm = $('#filterForm').serialize();

    //var by = $('#orderBy')[0];
    //var type = $('#orderByType')[0];
    //if (by.options[by.selectedIndex].value != "")
    //    orderBy = by.options[by.selectedIndex].value + " " +type.options[type.selectedIndex].value;
    //else orderBy = "";
}

function backNext() {
    if ($('.numbered').size() == 1 && page == 1) {
        $('#back').addClass("disabledd");
        $('#next').addClass("disabledd");
    } else if (page < $('.numbered').size() && page > 1) {
        $('#back').removeClass("disabledd");
        $('#next').removeClass("disabledd");
    } else if (page < 2) {
        $('#back').addClass("disabledd");
        $('#next').removeClass("disabledd");
    } else if (page > $('.numbered').size() - 1) {
        $('#next').addClass("disabledd");
        $('#back').removeClass("disabledd");
    }

    $('.numbered').removeClass("active");
    $("li.numbered[value=" + page + "]").addClass("active");
}

$(function(){
    page = $('#pageNumber').val();
    backNext()
});

$(document).ready(function () {

    $("#Goods").on('click', '.adding-button', function () {

        var button = $(this);

        $.post("addGoods", $(this).parents('form').serialize(),
            function (data) {
                if (data.addSuccess != null) {
                    $('#goodscountincart').text(data.currentGoodsCount);
                    $(".total").html(data.total);
                    $.get("translate",
                        {
                            value: "button.in.cart"
                        },
                        function (data) {
                            button.text(data.trim());
                            button.css("background-color", "#FF7A00");
                            button.css("color", "#FFFFFF");
                            button.removeClass("adding-button");
                            button.addClass("go-to-cart");
                        });
                }
            })
    });

    $("#Goods").on('click', '.go-to-cart', function () {
        location.href = "shoppingCart";
    });


    $('#Goods-block').on('click', '.rowsCount', function () {

        getAdditionalParameters();

        $.get("changeEntity?" + filterForm,
            {
                entityName: entityName,
                rows: rows,
                page: page,
                orderBy: orderBy
            },
            function (data) {
                $('#Goods-block').html(data);
            });
    });

    $('#Goods-block').on('click', '#back', function () {
        getAdditionalParameters();

        page = page - 1;
        backNext(page);

        $.get("changeEntity?" + filterForm,
            {
                page: page,
                rows: rows,
                entityName: entityName,
                changePage: "true",
                orderBy: orderBy
            },
            function (data) {
                $('.entitiesList').html(data);
            })
    });

    $('#Goods-block').on('click', '.numbered', function () {
        getAdditionalParameters();

        page = $(this).attr('value');
        backNext(page);
        $.get("changeEntity?" + filterForm,
            {
                page: page,
                rows: rows,
                entityName: entityName,
                changePage: "true",
                orderBy: orderBy
            },
            function (data) {
                $('.entitiesList').html(data);
            })
    });


    $('#Goods-block').on('click', '#next', function () {
        getAdditionalParameters();

        page = page - 1 + 2;
        backNext(page);

        $.get("changeEntity?" + filterForm,
            {
                page: page,
                rows: rows,
                entityName: entityName,
                changePage: "true",
                orderBy: orderBy
            },
            function (data) {
                $('.entitiesList').html(data);
            })
    });
});