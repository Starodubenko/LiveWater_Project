$('#Payment').on('click', '#Pay', function () {

    $.post("payment",$('#payment-form').serialize(),
        function (data) {
            var cardVal = data.cardVal;
            $.get("translate",
                {
                    value:data.message
                },
                function (data) {
                    var result;
                    if (cardVal != null) result = data + ' '+ cardVal;
                    else result = data;
                    $('.payment-message').html(result);
                });
            $('.balance-value').html(data.balanceVal);
        })
});