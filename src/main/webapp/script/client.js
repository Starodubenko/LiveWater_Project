$('.datepicker').datepicker({
    format: 'mm.dd.yyyy',
    startDate: '-3d'
});

$('#datetimepicker').datetimepicker({
    pickDate: false
});

//change class the clicked row (active or warning)
$(document).ready(function () {
    $('#Today').children('input[type="checkbox"]').mousedown(function () {
        $("tr:eq(" + ind + ")").addClass('bg-success');
    }).mouseup(function () {
        $("tr:eq(" + ind + ")").removeClass('bg-success');
        $("tr:eq(" + ind + ")").toggleClass('bg-info');
    });
});

$('#browse-orders').click(function () {

    if (!$('#collapseOne').hasClass('in')) {
        $.get("browseOrders2",
            function (data) {
                $('.orderList').html(data);
            });
    }
});

$('form input[type=file]').change(function () {
    var form = document.getElementById('image');
    var formData = new FormData(form);

    formData.append("image", $("#image")[0].files[0]);

    $.ajax({
        url: "ajaxShowCurrentAvatar",
        type: "POST",
        data: formData,
        mimeType: "multipart/form-data",
        contentType: false,
        cache: false,
        processData: false,
        success: function (data) {
            $('.image-block').html(data);
        }
    });
});

$('.orderList').on('click', '.order-function-button', function () {

    var className = this.className.split(/\s+/)[1];
    var id = this.value;
    var button = this;

    $.get("orderOperation2",
        {
            actor: "client",
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
                    $($(button).closest('tr').children('td').get(count - 3)).children('label').text(data.trim());
                    if (operation == "canceled")
                        $($(button).closest('tr').children('td').get(count - 1)).children('a').get(0).setAttribute('href', "/do/editOrder?id=" + id);
                    else $($(button).closest('tr').children('td').get(count - 1)).children('a').get(0).removeAttribute('href');
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

$('.orderList').on('click', 'button.repeat', function () {

    var className = this.className.split(/\s+/)[1];
    var id = this.value;

    $.get("orderOperation2",
        {
            actor: "client",
            typeOfOperation: className,
            id: id
        },
        function (data) {
            location.href = "shoppingCart";
        });
});

$('.order-successful-message > button').click(function () {
    $(this).closest('div').slideUp();
});

$('#browse-orders').click(function () {
    if ($('#browse-orders').closest('#accordion').children('div').children('.collapse').hasClass('in'))
        $(this).children('img').prop('src', "/style/img/plus.png");
    else $(this).children('img').prop('src', "/style/img/minus.png");
});


