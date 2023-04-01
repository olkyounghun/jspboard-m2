<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

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
        <c:if test="${error ne null}">
            <div>
                ${error} 관련
                ${errorMessage}
            </div>
        </c:if>
        <form method="post" action="errorMove">
            <input type="hidden" name="errorMove" id="errorMove" value="${errorMove}">
            <button type="submit" class="btn btn-outline-secondary">확인</button>
        </form>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
