$(function() {
    'use strict';

    var client;
    $(document).ready(function connect(){
        client = Stomp.over(new SockJS('/chat'));
        client.connect({}, function (frame) {
            client.subscribe('/topic/consultations', function (message) {
            });
        });
    });

    $('#create').click(function() {
        var topic = $('#topic').val();
        client.send("/app/chat/" + topic, {}, JSON.stringify({

            doctorId: $("#doctorId").val(),
            patientId: $('#patientId').val(),
            date: $('#date').val()
        }));
    });
});