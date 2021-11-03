$(document).ready(function () {
    $.get("authors", function(data) {
        console.log(data);

        for (let i = 0; i < data.length; i++) {
            let id = data[i].oid;
            let firstname = data[i].firstName;
            let lastname = data[i].lastName;

            let body = '<option value="'+id+'">'+firstname+' '+lastname+'</option>';

            $('#author').append(body);
        }
    });
});