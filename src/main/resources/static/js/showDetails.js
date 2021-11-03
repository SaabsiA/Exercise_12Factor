$(document).on("click", '.entry', function() {
    let entryId = $(this).attr('id');
    let url = 'blogentries/' + entryId.toString();

    console.log('Retrieve BlogEntry data for id ' + entryId);

    $('#home').hide();
    $('#detail').show();
    $('#create').hide();

    $('#detailBody').empty();

    $.get(url, function(data) {
        console.log(data);
        let title = data.title;
        let text = data.description;
        let date = data.publicationDate;
        let author = data.author;

        let body = '<h1>'+title+'</h1><p id="text">'+text+'</p><p><br>Written by</strong>: '+author+'<br><strong>Publication Date</strong>: '+date+'</p>';

        $('#detailBody').append(body);
    });
});