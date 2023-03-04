<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    div{
        padding: 5px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
    }
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
    <title>Model-2 게시판</title>
</head>
<body>
    <div>
        아이디 : ${sessionScope.userName}
    </div>
    <div>
        <form method="post" action="login">
            <div class="input-group" >
                <span class="input-group-text" id="basic-addon1">ID</span>
                <input id="loginId" name="loginId" type="text" class="form-control">
            </div>
            <div class="input-group" style=>
                <span class="input-group-text" id="basic-addon2">PW</span>
                <input type="password" id="loginPw" name="loginPw" class="form-control">
            </div>
            <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" class="btn btn-outline-secondary" onclick="location='home'">메인</button>
                <button type="button" class="btn btn-outline-secondary" onclick="location='signup'">가입</button>
                <button type="submit" class="btn btn-outline-secondary" >시작</button>
            </div>
        </form>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>