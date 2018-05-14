$(function() {
    'use strict';

    var client;

    function showMessage(msg)
    {
        var date = msg.date.split('T')[0];
        $('#consultations').append('<tr>' +
            '<td>' + msg.patientName + '</td>' +
            '<td>' + msg.doctorName + '</td>' +
            '<td>' + date + '</td>' +
            '</tr>');
    }

    $(document).ready(function connect(){
        client = Stomp.over(new SockJS('/chat'));
        client.connect({}, function (frame) {
            $("#tableID").show();
            client.subscribe('/topic/consultations', function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    });
});