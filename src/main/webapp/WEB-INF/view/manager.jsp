<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-02-19
  Time: 오후 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Model-2 게시판</title>
</head>
<style>
    .mainContainer{
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
    }
</style>
<script>

</script>
<body>
<div class="mainContainer">
    <form method="get">
        <div>
            <input type="date" name="startDate">
            ~
            <input type="date" name="endDate">
            <input type="text" name="searchName" placeholder="제목 + 작성자 + 내용">
            <button type="submit" >검색</button>
        </div>
    </form>
    <div>
        체크유저
    </div>
    <div>
        <div>
            <table>
                <thead>
                <tr>
                    <td> 번호</td>
                    <td> 아이디</td>
                    <td> 이름</td>
                    <td> 이메일</td>
                    <td> 회원가입날짜</td>
                    <td> 등급</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="list">
                    <tr>
                        <td>${list.id_member}</td>
                        <td>${list.user_member}</td>
                        <td>${list.name_member}</td>
                        <td>${list.email_member}</td>
                        <td>${list.emailcheck_member}</td>
                        <td><fmt:formatDate value="${list.regdate_member}" pattern="yyyy-MM-dd  HH:mm:ss" type="date"/></td>
                        <td>${list.rating_member}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
