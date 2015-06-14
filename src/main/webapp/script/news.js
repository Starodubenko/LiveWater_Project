(function () {
    $('#save').css('display', 'none');
    $('#cancel').css('display', 'none');
    $('#add').css('display', 'none');
    $('.news-block-header .input-block').css('display', 'none');
    $('.news-block-content textarea').css('display', 'none');

    var addIsOk = $('#addIsOk').val();
    if (addIsOk == "ok"){
        $('#edit').css('display', 'none');
        $('#add').css('display', '');
        $('#cancel').css('display', '');
        $('.news-block-header div').css('display', 'none');
        $('.news-block-header .input-block').css('display', '');
        $('.news-block-content textarea').css('display', '');
        $('.news-block-content div').css('display', 'none');
    }

    $('.datepicker').datepicker({
        format: "dd.mm.yyyy",
//    daysOfWeekDisabled: "0,6",
//    todayHighlight: true,
        startDate: '+0d'
    });
})();

function startEdit(){
    $('#edit').css('display', 'none');
    $('#save').css('display', '');
    $('#cancel').css('display', '');
    $('.news-block-header div').css('display', 'none');
    $('.news-block-header .input-block').css('display', '');
    $('.news-block-content textarea').css('display', '');
    $('.news-block-content div').css('display', 'none');
}

function finishEdit(){
    $('#edit').css('display', '');
    $('#save').css('display', 'none');
    $('#cancel').css('display', 'none');
    $('.news-block-header div').css('display', '');
    $('.news-block-header .input-block').css('display', 'none');
    $('.news-block-content textarea').css('display', 'none');
    $('.news-block-content div').css('display', '');
}

function compleateEdit(){

}

$(document).ready(function () {

    $('.article-operations').on('click', '#edit', function () {
        startEdit();
    });

    $('.article-operations').on('click', '#save', function () {

        var url = window.location.href;

        $.post("save-article",$('form.news-block').serialize(),
            function (data) {
                $.get("translate",
                    {
                        value: data.message
                    },
                    function (data) {
                        alert($.trim(data));
                        window.location.href = url;
                    });
            });
    });

    $('.article-operations').on('click', '#cancel', function () {

        var addState = $('#add').css('display');
        if (addState != 'block')
            finishEdit();
        else
            window.location.href = 'news';
    });

    $('.article-operations').on('click', '#remove', function () {

        $.post("remove-article",$('form.news-block').serialize(),
            function (data) {
                $.get("translate",
                    {
                        value: data.message
                    },
                    function (data) {
                        alert($.trim(data));
                        window.location.replace("/LiveWater/do/news");
                    });
            });
    });

    $('.article-operations').on('click', '#add', function () {

        $.post("add-article",$('form.news-block').serialize(),
            function (data) {
                $.get("translate",
                    {
                        value: data.message
                    },
                    function (data) {
                        alert($.trim(data));
                        window.location.replace("/LiveWater/do/news");
                    });
            });
    });
});