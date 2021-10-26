$(document).on('click','.homeBtn',function () {
    $('#home').show();
    $('#detail').hide();
    $('#create').hide();
});

$(document).on('click','#createBtn',function () {
    $('#home').hide();
    $('#detail').hide();
    $('#create').show();
});

$(document).on('click','#createBlogBtn',function () {
    let x = $('#createBlogForm').serializeArray();
    let blog = {};
    let sights = [];
    $.each(x, function(i, field) {
        switch (field.name) {
            case 'title':
                blog.title = field.value;
                break;
            case 'text':
                blog.text = field.value;
                break;
            case 'publicationDate':
                let date = new Date(field.value);
                blog.publicationDate = date.toISOString();
                break;
            case 'author':
                blog.author = {};
                blog.author.id = field.value;
                break;
            default:
                break;
        }
    });

    blog.sights = sights;

    console.log(blog);

    $.ajax({
        type: 'POST',
        url: 'blogentries',
        headers: {
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(blog),
        processData: false,
        success: function (data, status) {
            if (status === 'success') {
                $('#createBlogForm')[0].reset();
                fetchBlogEntries();
            }
            else {
                alert('Something went wrong, please try again');
            }
        }
    }).done(
        console.log('post request finished')
    );
});

function fetchBlogEntries() {
    $.get("blogentries/listitems", function(data) {
        $('#entries').empty();
        for (let i = 0; i < data.length; i++) {
            let title = '<h3>'+data[i].title+'</h3>';
            let author = data[i].author;

            let body = '<div id="'+data[i].id+'" class="entry"><strong id="title">'+title+'</strong><br>Written by: <span id="author">'+author+'</span></div>';

            $('#entries').append(body);
        }
    });
}