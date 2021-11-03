$(document).ready(function() {
    $.get("blogentries/listitems", function(data) {
        $('#home').show();
        $('#detail').hide();
        $('#create').hide();

        console.log(data);
        for (let i = 0; i < data.length; i++) {
            let title = '<h3>'+data[i].title+'</h3>';
            let author = data[i].author;

            let body = '<div id="'+data[i].oid+'" class="entry"><strong id="title">'+title+'</strong><br>Written by: <span id="author">'+author+'</span></div>';

            $('#entries').append(body);
        }
    });
});