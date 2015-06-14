$(document).ready(function(){

    $('.non-edit div').on('click', '#edit', function(){
        $('.about-content.non-edit').css('display', 'none');
        $('.about-content.edit').css('display', '');
    });

    $('.edit').on('click', '#save', function(){
        $.post("save-about",$('.edit form').serialize(),
            function (data) {
                $.get("translate",
                    {
                        value: data.message
                    },
                    function (data) {
                        alert($.trim(data));
                        window.location.href = window.location.href;
                    });
            });
    });

    $('.edit').on('click', '#cancel', function(){
        $('.about-content.non-edit').css('display', '');
        $('.about-content.edit').css('display', 'none');
    });

});