<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script>
        $(function(){
            $("#loginBtn").click(function(){
                location.href='../login.html';
            })
        })
    </script>
    <title>아이디 찾기</title>
</head>
<body>
<div>
    <div>
        <h3>아이디 찾기 검색결과</h3>
    </div>
    <div>
        <h5>
            ${ id }
        </h5>
        <p>
            <button type="button" id=loginBtn >Login</button>
        </p>
    </div>
</div>
</body>
</html>