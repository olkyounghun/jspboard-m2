<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-12-17
  Time: 오후 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .maincontainer{
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
    <div class="maincontainer">
        <c:if test="${sessionScope.userName ne null}">
            <c:forEach items="${list}" var="list">
                <a href="/memberdetail/${list.id_member}">${sessionScope.userName}</a>님 환영합니다.
            </c:forEach>
        </c:if>
        <form id="form" name="form" method="post" action="searchworld">
            <div class="input-group">
                <span class="input-group-text" id="basic-addon1">Let's</span>
                <input type="text" id="startword" name="startword" class="form-control" placeholder="Where We Go?">
                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">start</button>
            </div>
        </form>

    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
