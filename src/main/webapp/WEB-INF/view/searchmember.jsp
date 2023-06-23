<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-02-19
  Time: 오후 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
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
    .btn {
        background-color: white;
        border: 1px solid black;
        border-radius: 10px;
        padding: 5px 10px;
        font-size: 14px;
    }
</style>
<script>

</script>
<body>
<div>
    <c:if test="${sessionScope.id_member ne null}">
        ${sessionScope.id_member} 님 환영합니다.
    </c:if>
</div>
<div class="mainContainer">
    <form method="post" action="${pageContext.request.contextPath}/searchmember">
        <div>
            <input type="date" id="startDate" name="startDate" value="${startDate}">
            ~
            <input type="date" id="endDate" name="endDate" value="${endDate}">
            <input type="text" id="searchName" name="searchName" placeholder="아이디 + 이름 + 이메일" value="${searchName}">
            <button type="submit" >검색</button>
        </div>
    </form>
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
                            <td><a href="/managerinfo/${list.id_member}">${list.user_member}</a></td>
                            <td>${list.name_member}</td>
                            <td>${list.email_member}</td>
                            <td>${list.emailcheck_member}</td>
                            <td><fmt:formatDate value="${list.regdate_member}" pattern="yyyy-MM-dd" type="date"/></td>
                            <td>${list.rating_member}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="paging">
        <!-- 1~10까지 있는 페이지의 페이징 -->
        <c:if test="${paging.prev}">
            <a href=/searchmember/${paging.beginPage-1}?startDate=${startDate}&endDate=${endDate}&searchName=${searchName}">prev</a>
        </c:if>
        <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
            <c:choose>
                <c:when test="${paging.page==index}">
                    ${index}
                </c:when>
                <c:otherwise>
                    <a href="/searchmember/${index}?startDate=${startDate}&endDate=${endDate}&searchName=${searchName}">${index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${paging.next}">
            <a href="/searchmember/${paging.endPage+1}?startDate=${startDate}&endDate=${endDate}&searchName=${searchName}">next</a>
        </c:if>
    </div>
    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-outline-secondary" onclick="location='/boardlist'">목록</button>
        <button type="button" class="btn btn-outline-secondary" onclick="location='/logout'">로그아웃</button>
    </div>
</div>
</body>
</html>
