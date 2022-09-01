<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <title>Insert title here</title>
<%--    <%@ include file="/apps/common/include/https://meet.google.com/uky-pumf-ycq"%>--%>
    <style>
        .body-chat{
            padding-bottom: 0;
        }
        .chat-header,
        .chat-header a {
            background-color: #a1c0d6;
            color: black;
        }

        .chat-header {
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
        }
        .chat {
            background-color: #a1c0d6;
            height: 100vh;
            padding-top: 15px;
            padding-left:10px;
            padding-right: 10px;
        }

        .chat .date-divider {
            text-align: center;
            font-size: 12px;
            color: rgba(0, 0, 0, 0.5);
            margin-bottom: 15px;
        }

        .chat__message{
            margin-bottom: 10px;
            display: flex;
        }

        .chat__message-from-me {
            justify-content: flex-end;
            align-items: flex-end;
        }

        .chat__message-to-me {
            justify-content: flex-start;
            align-items: flex-start;
        }
        .chat__message-time {
            font-size: 10px;
            color : rgba(0, 0, 0, 0.5);
        }

        .chat__message-from-me .chat__message-body{
            background-color: #ffe934;
            padding : 10px 5px;
            border-radius: 2px;
            margin-right: 10px;
            margin-left: 10px;
            font-size: 15px;
        }

        .chat__message-to-me img{
            height: 35px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .chat__message-username{
            font-size: 12px;
            font-weight: 600;
            margin-bottom: 5px;
        }

        .chat__message-to-me .chat__message-body {
            background-color: white;
            padding : 10px 5px;
            border-radius: 2px;
            font-size: 15px;
        }

        .chat__message-center {
            display: flex;
            flex-direction: column;
        }

        .chat__message-time {
            margin-top: 50px;
            margin-left: 5px;
        }

        .type-message {
            width: 100%;
            bottom:0;
            position: fixed;
            height: 45px;
            background-color: #eeeeee;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding-left:  10px;
            padding-right: 5px;
        }

        .type-message .fa-plus {
            color: #b4b4b4;
        }

        .type-message__input {
            width: 100%;
            display: flex;
            align-items: center;
        }

        .type-message__input input {
            width: 100%;
            padding: 10px;
            border-style: none;
            border-bottom-left-radius: 5px;
            border-top-left-radius: 5px;
        }

        .type-message__input #btnSend {
            color: #523737;
            background-color: #ffe934;
            padding: 10px;
            margin-right: 5px;
            width: 13%;
        }

        .type-message__input .record-message i {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .header__column {
            height: 20px;
        }

        #receiver {
            text-align: center;
            margin-left: 233px;
            width: 5em;
            background-color: #a1c0d6;
            height: 1.5em;
        }

        .enter {
            text-align: center;
            color: gray;
        }

    </style>
    <script language="javascript">

        var socket = null;
        connect();

        function connect() {
            var ws = new WebSocket("ws://localhost:8080/ercg/echo.do");
            socket = ws;
            //이벤트 헨들러
            ws.onopen = function() {
                console.log('Info: connection opened.');
                socket.send("1" + "," + gUserId + "," + "ENTER");
            };

            //소켓한테 메시지 받아옴
            ws.onmessage = function (event) {
                var sm = event.data;
                //sl에는 sendId, content가 들어있음
                var sl = sm.split(',');
                let sendId = sl[0];
                let content = sl[1];
                let html = $("#nextMsg").html();
                if(content == "ENTER") {
                    html += "<div class='enter'>" + sendId + "님이 들어오셨습니다.</div>";
                } else if(content == "OUT") {
                    html += "<div class='enter'>" + sendId + "님이 나가셨습니다.</div>";
                } else if(sendId != gUserId){
                    let currT = new Date().getHours() + ":" + new Date().getMinutes();
                    html += '<div class="chat__message chat__message-to-me"><div class="chat__message-center"><h3 class="chat__message-username">' + sendId
                        + '</h3><span class="chat__message-body">' + content + '</span></div><span class="chat__message-time">'
                        + currT + '</span></div>';
                }

                $("#nextMsg").html(html);

                console.log("ReceiveMessage:" + event.data+'\n');
            };

            ws.onclose = function (event) {
                console.log('Info: connection closed');
                //setTimeout( function() {connect(); }, 1000); // retry connection!!
            };

            ws.onerror = function (err) { console.log('Error:', err); };
        };

        $(document).ready(function() {
            $('#backChats').on('click', function(evt) {
                location.replace("chats.jsp");
                socket.send("1," + gUserId + "," + "OUT");
            });

            $('#btnSend').on('click', function(evt) {
                let currT = new Date().getHours() + ":" + new Date().getMinutes();
                let msg = $('#msg').val();
                evt.preventDefault();
                if (socket.readyState !== 1 ) return;

                //protocol: RoomNum, 보내는id, 내용
                socket.send("1," + gUserId + "," + msg);

                let html = $("#nextMsg").html() + '<div class="chat__message chat__message-from-me"><span class="chat__message-time">'
                    + currT + '</span><span class="chat__message-body">' + msg + '</span></div>';
                $("#nextMsg").html(html);
                $("#msg").val("");
            });

            $('#wsClose').on('click', function(e) {
                socket.onclose();

            });
        });

    </script>
</head>
<body class="body-chat">
<header class= "top-header chat-header">
    <div class="header__column">
        <i class="fa fa-chevron-left fa-lg" id="backChats"></i>
    </div>
    <div class="header__column">
        <span class="header__text">ROOM1</span>
    </div>
</header>
<main class="chat">
    <div class="date-divider">
        <span class="date-divider__text">화요일, 2018년 6월 19일</span>
    </div>

    <div id="nextMsg"></div>

</main>
<div class="type-message">
    <div class="type-message__input">
        <input type="text" id="msg"/>
        <span id="btnSend">전송</span>
    </div>
</div>
</body>
</html>