$(document).ready(function() {
    $.get("blogentries/listitems", function(data) {
        $('#home').show();
        $('#detail').hide();
        $('#create').hide();

        console.log(data);
        for (let i = 0; i < data.length; i++) {
            let title = '<h3>'+data[i].title+'</h3>';
            let sights = data[i].sights;
            let author = data[i].author;

            let body = '<div id="'+data[i].id+'" class="entry"><strong id="title">'+title+'</strong><br>Sights: <span id="sight">'+sights+'</span><br>Written by: <span id="author">'+author+'</span></div>';

            $('#entries').append(body);
        }
    });
});