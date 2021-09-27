$('#submit-btn').on('click', function() {
    let datas = {title: $('#title_input').val(), genre: $('#genre_input').val(), length: $('#length_input').val(), artist: $('#artist_input').val() };
    $.ajax({
        url: '/create',
        type: 'post',
        data : datas,
        success: function(resp) {
            $('#song-table').html(resp);
            $('input').val('');
        },
    });
});

$('#UUID-btn').on('click', function() {
    $.ajax({
        url: '/search/'+$('#UUID_input').val(),
        type: 'get',
        success: function(resp) {
            $('#UUID_table').html(resp);
            $('input').val('');
        },
    });
});

$('#edit-submit-btn').on('click', function() {
    let datas = {title: $('#edit_title_input').val(), genre: $('#edit_genre_input').val(), length: $('#edit_length_input').val(), artist: $('#edit_artist_input').val() };
    $.ajax({
        url: '/edit/'+$('#id_input').val(),
        type: 'put',
        data: datas,
        success: function(resp) {
            $('#song-table').html(resp);
            $('input').val('');
        },
    });
});

$('#artistlist-btn').on('click', function() {
    $.ajax({
        url: '/artistslist',
        type: 'get',
        success: function(resp) {
            $('#artist-list-table').html(resp);
            $('input').val('');
        },
    });
});

function editSong(id) {
    $('#id_input').val(id);
}

function deleteSong(id) {
    $.ajax({
       url: '/delete/'+id,
       type: 'get',
        success: function(resp) {
            $('#song-table').html(resp);
            $('input').val('');
        }
    });
}