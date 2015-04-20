$(document).ready(function () {

    $("#updatePasswordForm").on("click", "#updatePassword", function () {

        $('#updatePasswordForm').children('div').removeClass("has-error");
        $('#updatePasswordForm').children('div').children('p').html("");

        $.post("updatePassword", $("#updatePasswordForm").serialize(),
            function (data) {
                if (typeof data != "object") {
                    $("#passwordmessage").html(data);
                    $('#updatePasswordForm')[0].reset();
                } else {
                    $.each(data, function (key, value) {
                        $.get("translate",
                            {
                                value: value
                            },
                            function (data) {
                                $("#" + key + "Div").addClass("has-error");
                                $("#" + key + "Input").html(data);
                                $("#" + key + "Input").show().delay(1500).fadeOut();
                            })
                    });
                }
            })
    });

    $("#updateFullNameForm").on("click", "#updateFullName", function () {

        $('#updateFullNameForm').children('div').removeClass("has-error");
        $('#updateFullNameForm').children('div').children('p').html("");

        $.post("updateFullName", $("#updateFullNameForm").serialize(),
            function (data) {
                if (typeof data != "object") {
                    $("#fullnamemessage").html(data);
                } else {
                    $.each(data, function (key, value) {
                        $.get("translate",
                            {
                                value: value
                            },
                            function (data) {
                                $("#" + key + "Div").addClass("has-error");
                                $("#" + key + "Input").html(data);
                                $("#" + key + "Input").show().delay(1500).fadeOut();
                            })
                    });
                }
            })
    });

    $("#updateContactDetailsForm").on("click", "#updateContactDetails", function () {

        $('#updateContactDetailsForm').children('div').removeClass("has-error");
        $('#updateContactDetailsForm').children('div').children('p').html("");

        $.post("updateContactDetails", $("#updateContactDetailsForm").serialize(),
            function (data) {
                if (typeof data != "object") {
                    $("#contactdetailsmessage").html(data);
                } else {
                    $.each(data, function (key, value) {
                        $.get("translate",
                            {
                                value: value
                            },
                            function (data) {
                                $("#" + key + "Div").addClass("has-error");
                                $("#" + key + "Input").html(data);
                                $("#" + key + "Input").show().delay(1500).fadeOut();
                            })
                    });
                }
            })
    });

    $("#updatePositionForm").on("change", "#position_name", function () {

        $.post("updatePosition", $("#updatePositionForm").serialize(),
            function (data) {
                $("#positionmessage").html(data);
                $("#positionmessage").show().delay(1500).fadeOut();
            })
    });

    $("#updateDiscountForm").on("change", "#discount", function () {

        $.post("updateDiscount", $("#updateDiscountForm").serialize(),
            function (data) {
                $("#discountmessage").html(data);
                $("#discountmessage").show().delay(1500).fadeOut();
            })
    });

    $("#updateEmployeeDetailsForm").on("click", "#updateEmployeeDetails", function () {

        $('#updateEmployeeDetailsForm').children('div').removeClass("has-error");
        $('#updateEmployeeDetailsForm').children('div').children('p').html("");

        $.post("updateEmployeeDetails", $("#updateEmployeeDetailsForm").serialize(),
            function (data) {
                if (typeof data != "object") {
                    $("#empldetailsmessage").html(data);
                } else {
                    $.each(data, function (key, value) {
                        $.get("translate",
                            {
                                value: value
                            },
                            function (data) {
                                $("#" + key + "Div").addClass("has-error");
                                $("#" + key + "Input").html(data);
                                $("#" + key + "Input").show().delay(1500).fadeOut();
                            })
                    });
                }
            })
    });

    $(".edit-block").on("click", "#ban", function () {

        var banned = (this.checked) ? "true" : "false";

        $.post("userBan",
            {
                deleted:banned
            },
            function (data) {
                $("#banmessage").html(data);
                $("#banmessage").show().delay(1500).fadeOut();
            })
    });

    $("#image-form").on("click",".image-block",function(){
        $(".image").trigger('click');
    });

    $("#goods-form").on("click",".image-block",function(){
        $(".image").trigger('click');
    });

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.image-block').attr('src', e.target.result);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(".image").change(function(){
        readURL(this);
    });

    $("#image-form").on("click", "#updateAvatar", function () {

        var form = document.getElementById('image-form');
        var formData = new FormData(form);
        formData.append("image", $(".image")[0].files[0]);

        $.ajax({
            url: "updateUserAvatar",
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                $('#avatarInput').html(data);
                $("#avatarInput").show().delay(1500).fadeOut();
            }
        });
    });

    $(".edit-field-block-button").on("click", "#updateGoods", function () {

        var id = $('#id').val();

        $.post("updateGoods?"+$("#goods-form").serialize(),
            {
                id:id
            },
            function (data) {
                $('#goodsmessage').html(data);
            })
    });

    $(".edit-field-block-button").on("click", "#addGoods", function () {

        var form = document.getElementById('goods-form');
        var formData = new FormData(form);
        formData.append("goodsImageFileName", $(".image")[0].files[0]);

        //$.post("addGoods?"+$("#goods-form").serialize(),
        //    function (data) {
        //        $('#goodsmessage').html(data);
        //    })

        $.ajax({
            url: "addGoodsToDataBase",
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                $('#goodsmessage').html(data);
            }
        });
    });
});
