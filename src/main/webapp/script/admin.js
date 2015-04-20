$('#datepickerLog').datepicker({

});
$('#datepickerReport').datepicker({

});

$("#entityName").html($("#entityName option").sort(function (a, b) {
    return a.text == b.text ? 0 : a.text < b.text ? -1 : 1
}));

var page;
var rows;
var orderBy;
var entityName;
var filterForm;

function getAdditionalParameters(){
    var e = document.getElementById("entityName");
    entityName = e.options[e.selectedIndex].value;
    page = $('#pageNumber').val();
    rows = $('.rowsCount').parents('form').children().children('input').val();
    filterForm = $('#filterForm').serialize();

    var by = $('#orderBy')[0];
    var type = $('#orderByType')[0];
    if (by.options[by.selectedIndex].value != "")
        orderBy = by.options[by.selectedIndex].value + " " +type.options[type.selectedIndex].value;
    else orderBy = "";
}

function changeEntityName(needFilter){

    getAdditionalParameters();

    $.get("entityOrderBy", {entityName: entityName},
        function (data) {
            $('#orderBy').html(data);
        });                      ////////////////
    var actionRow = "changeEntity";
    if(needFilter) actionRow = actionRow + "?" + filterForm;
                                /////////////////

    $.get("changeEntity", {entityName: entityName, page: page, rows: rows, orderBy: orderBy},
        function (data) {
            $('.table-data-base').html(data);
            $.get("entityFilter", {entityName: entityName},
                function (data) {
                    $('.filter').html(data);
                });
        });
}

changeEntityName();

$(document).ready(function () {
    $('#entityName').change(function () {
        changeEntityName(false);
    });

    $('.table-data-base').on('click', 'input[type="checkbox"].edit', function () {
        var visText = (this.checked) ? "" : "none";
        var visLabel = (this.checked) ? "none" : "";

        var boxNum = $(this).parents('tr').get(0).rowIndex;

        var labels = $(".table-data-base tr:eq(" + boxNum + ") > td > label.field");

        for (var i = 0; i < labels.size(); i++) {

            var width = labels[i].offsetWidth + 30;
            var forL = labels[i].getAttribute("for");

            var editField = $("table>tbody>tr>td>#" + forL);
            if (forL != null) {
                labels[i].style.display = visLabel;
                editField.get(0).style.width = width;
                editField.get(0).style.display = visText;
                if (!this.checked){
                    var value = editField.val();
                    $(labels[i]).text(value);
                }
            }
        }

        if (!this.checked){
            var fields = $(this).closest('tr').find('[name]').serialize();
            var id = $(this).closest('tr').children('td').children('button').val();

            getAdditionalParameters();
            $.post("update-entity?"+fields,
                {
                    id:id,
                    entityName:entityName
                },
                function (data) {
                })
        }
    });

    $('.table-data-base').on('click', '.edit-field[type="checkbox"]', function(){
        $(this).val($(this).prop('checked'));
    });

    $('.dataBase').on('click', '#filter-button', function () {
        var $btn = $(this).button('loading');

        getAdditionalParameters();

        $.get("changeEntity?"+filterForm,
            {
                entityName:entityName,
                rows:rows,
                orderBy: orderBy
            },
            function (data) {
                $('.table-data-base').html(data);
            });

        $btn.button('reset')
    });

    $('.table-data-base').on('click', '.rowsCount', function () {

        getAdditionalParameters();

        $.get("changeEntity?"+filterForm,
            {
                entityName:entityName,
                rows:rows,
                orderBy: orderBy
            },
            function (data) {
                $('.table-data-base').html(data);
            });
    });

    $('.dataBase').on('click', '#filter-reset-button', function () {
        $('#filterForm')[0].reset();

        getAdditionalParameters();

        $.get("changeEntity?"+filterForm,
            {
                entityName:entityName,
                rows:rows,
                orderBy: orderBy
            },
            function (data) {
                $('.table-data-base').html(data);
            })
    });

    $('#orderBy').change(function () {
        getAdditionalParameters();

        $.get("changeEntity?"+filterForm,
            {
                entityName:entityName,
                rows:rows,
                orderBy: orderBy
            },
            function (data) {
                $('.table-data-base').html(data);
            })
    });
    $('#orderByType').change(function () {
        getAdditionalParameters();

        $.get("changeEntity?"+filterForm,
            {
                entityName:entityName,
                rows:rows,
                orderBy: orderBy
            },
            function (data) {
                $('.table-data-base').html(data);
            })
    });

    var rowNumber;

    function hideShow(rowNum, operation) {
        var addFields = $(".table-data-base tr:eq(" + rowNum + ') > td >input.edit-field');

        for (var i = 0; i < addFields.size(); i++) {
            addFields[i].style.display = operation;
        }
    }

    $('.table-data-base').on('click', '#add', function () {
        rowNumber = $(this).parents('tr').get(0).rowIndex;

        var show = "";
        var hide = "none";

        hideShow(rowNumber, "");
        $("#add").get(0).style.display = hide;
        $("#save").get(0).style.display = show;
    });

    $(document).keyup(function (e) {
        if (e.keyCode == 27) {
            var show = "";
            var hide = "none";

            hideShow(rowNumber, "none");
            $("#add").get(0).style.display = show;
            $("#save").get(0).style.display = hide;
        }
    });


    $('.table-data-base').on('click', '#save', function () {

        rowNumber = $(this).parents('tr').get(0).rowIndex;
        getAdditionalParameters();

        var form = document.getElementById('saveForm');
        var formData = new FormData(form);
        var formSerialize = $("#saveForm").serialize();

        var Data = $(".table-data-base tr:eq(" + rowNumber + ") > td .edit-field").serializeArray();
        var field = {name: "entityName", value: entityName};
        Data.push(field);
        field = {name: "page", value: page};
        Data.push(field);
        field = {name: "rows", value: rows};
        Data.push(field);

        formData.append("filename", $("#filename")[0].files[0]);
        formData.append("entityName", entityName);

        $.each(Data, function (i, field) {
            formData.append(field.name, field.value);
        });

        $.ajax({
            url: "save-entity",
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                $('.table-data-base').html(data);
            }
        });

    });


    //$('.table-data-base').on('click', '.edit', function () {
    //
    //    if (!this.checked) {
    //        var entityName = $('#entityName').val();
    //        rowNumber = $(this).parents('tr').get(0).rowIndex;
    //
    //        var field = {};
    //        var data = $(".table-data-base tr:eq(" + rowNumber + ") > td .edit-field").serializeArray();
    //        data.push(field["entityName"] = entityName);
    //
    //        //$(".table-data-base tr:eq("+rowNumber+") > td .edit-field").each(function(){
    //        //    var field = {};
    //        //    field[(this).getAttribute("name")] = (this).value;
    //        //    data.push(field);
    //        //});
    //
    //        //$.post("save-entity", $('#entity-line'+rowNumber).serialize() +"&entityName=" + entityName,
    //        //    function (data) {
    //        //        $('#result').html(data.result);
    //        //    })
    //    }
    //});

    $('.table-data-base').on('click', '.delete', function () {

        var id = $(this).val();
        getAdditionalParameters();

        var Data = [];
        var field = {name: "entityName", value: entityName};
        Data.push(field);
        field = {name: "page", value: page};
        Data.push(field);
        field = {name: "rows", value: rows};
        Data.push(field);
        field = {name: "id", value: id};
        Data.push(field);

        $.post("delete-entity", Data,
            function (data) {
                $('#result').html(data.result);
            })
    });

    $('.table-data-base').on('click', '#back', function () {
        getAdditionalParameters();

        page = page - 1;
        backNext(page);
        $('.numbered').removeClass("active");
        $("li.numbered[value=" + page + "]").addClass("active");

        $.get("changeEntity?"+filterForm,
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

    $('.table-data-base').on('click', '.numbered', function () {
        getAdditionalParameters();

        $('.numbered').removeClass("active");
        $(this).addClass("active");
        page = $(this).attr('value');
        backNext(page);
        $.get("changeEntity?"+filterForm,
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


    $('.table-data-base').on('click', '#next', function () {
        getAdditionalParameters();

        page = page - 1 + 2;
        backNext(page);
        $('.numbered').removeClass("active");
        $("li.numbered[value=" + page + "]").addClass("active");

        $.get("changeEntity?"+filterForm,
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

    function backNext(page) {
        if (page < $('.numbered').size() && page > 1) {
            $('#back').removeClass("disabledd");
            $('#next').removeClass("disabledd");
        } else if (page < 2) {
            $('#back').addClass("disabledd");
            $('#next').removeClass("disabledd");
        } else if (page > $('.numbered').size() - 1) {
            $('#next').addClass("disabledd");
            $('#back').removeClass("disabledd");
        }
    }

    $('.table-data-base').on('click', '#addGoods', function () {
        location.href = "editGoods?purpose=add";
    });
});

