(function () {
    $('#save').css('display', 'none');
    $('#cancel').css('display', 'none');
    $('.news-block-header .input-block').css('display', 'none');
    $('.news-block-content textarea').css('display', 'none');
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
        finishEdit();
    });

    $('.article-operations').on('click', '#cancel', function () {
        finishEdit();
    });

    $('.article-operations').on('click', '#remove', function () {
        window.location.replace("/LiveWater/do/news");
    });
});