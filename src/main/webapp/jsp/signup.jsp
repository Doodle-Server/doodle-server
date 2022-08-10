<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
</head>
<body>
<h1>
    회원가입
</h1>

<script type="text/javascript">
    $(function() {
        $("#no-id-alert-danger").hide();
        $("#id-alert-success").hide();
        $("#id-alert-danger").hide();
        $("#idCheck").click(function(){
            $.ajax({
                type: 'GET',
                data: {
                    "userid": $("#userid").val()},
                url : "getUserById.do",
                success : function(data) {
                    console.log(data);
                    var userid = $("#userid").val();
                    if(!userid || userid.length<7 || userid.length>14 ){
                        $("#no-id-alert-danger").show();
                        $("#id-alert-success").hide();
                        $("#id-alert-danger").hide();
                    }else if(data=='0') {
                        $("#no-id-alert-danger").hide();
                        $("#id-alert-success").show();
                        $("#id-alert-danger").hide();
                    } else if (data=="1") {
                        $("#no-id-alert-danger").hide();
                        $("#id-alert-success").hide();
                        $("#id-alert-danger").show();
                    }
                }
            })
        })
    });
</script>
<form action="/save" method="post">
    <p>
        <label for="userName">이름</label>
        <input type="text" id="username" name="username"/>
    </P>
    <p>
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" name="nickname"/>
    </P>
    <p>
        <label for="email">이메일</label>
        <input type="email" id="email" name="email"/>
    </P>
    <p>
        <label for="userid">아이디</label>
        <input type="text" id="userid" name="userid" required="required" placeholder="7~14자 이내" />
        <button type="button" id="idCheck">아이디확인</button>
         <div class="alert alert-danger" id="no-id-alert-danger" style="color: red">아이디를 7~14자 이내로 입력하세요.</div>
        <div class="alert alert-success" id="id-alert-success" style="color: blue" >해당 아이디 사용가능.</div>
        <div class="alert alert-danger" id="id-alert-danger" style="color: red">중복된 아이디 존재.</div>
    </P>

    <p>
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password"/>
    </P>
    <p>
        <label for="password2">비밀번호 확인</label>
        <input type="password" id="password2" name="password2"/>
    </P>
    <button type="submit">회원가입하기</button>
    <%--    <p><a href="/users/login">로그인하러가기</a></p>--%>
</form>
</body>
</html>