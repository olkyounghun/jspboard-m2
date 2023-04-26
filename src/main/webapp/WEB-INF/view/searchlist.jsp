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
    <div>
        <c:if test="${sessionScope.loginId ne null}">
            <a href="/memberdetail/${id_member}">${sessionScope.loginId}</a> 님 환영합니다.
        </c:if>
    </div>
    <div class="mainContainer">
        <form method="get" action="/searchlist">
            <div>
                <input type="date" id="startDate" name="startDate" value="${startDate}">
                ~
                <input type="date" id="endDate" name="endDate" value="${endDate}">
                <select id="searchType" name="searchType">
                    <option value="ALL" ${searchType == 'ALL' ? 'selected' : ''}>모든 카테고리</option>
                    <option value="JAVA" ${searchType == 'JAVA' ? 'selected' : ''}>JAVA</option>
                    <option value="Javascript" ${searchType == 'Javascript' ? 'selected' : ''}>Javascript</option>
                    <option value="Database" ${searchType == 'Database' ? 'selected' : ''}>Database</option>
                </select>
                <input type="text" id="searchName" name="searchName" placeholder="제목 + 작성자 + 내용" value="${searchName}">
                <button type="submit" >검색</button>
            </div>
        </form>
        <div>
            <div>
                <table>
                    <thead>
                        <tr>
                            <td>글 번호</td>
                            <td>카테고리</td>
                            <td>제목</td>
                            <td>작성자</td>
                            <td>조회수</td>
                            <td>작성날짜</td>
                            <td>수정날짜</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="list">
                            <tr>
                                <td>${list.id_board}</td>
                                <td>${list.type_board}</td>
                                <td><a href="/boarddetail/${list.id_board}">${list.title_board}</a></td>
                                <td>${list.user_board}</td>
                                <td>${list.views_board}</td>
                                <td><fmt:formatDate value="${list.regdate_board}" pattern="yyyy-MM-dd" type="date"/></td>
                                <td><fmt:formatDate value="${list.moddate_board}" pattern="yyyy-MM-dd" type="date"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="paging">
            <!-- 1~10까지 있는 페이지의 페이징 -->
            <c:if test="${paging.prev}">
                <a href=/searchlist/${paging.beginPage-1}?startDate=${startDate}&endDate=${endDate}&searchType=${searchType}&searchName=${searchName}">prev</a>
            </c:if>
            <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
                <c:choose>
                    <c:when test="${paging.page==index}">
                        ${index}
                    </c:when>
                    <c:otherwise>
                        <a href="/searchlist/${index}?startDate=${startDate}&endDate=${endDate}&searchType=${searchType}&searchName=${searchName}">${index}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.next}">
                <a href="/searchlist/${paging.endPage+1}?startDate=${startDate}&endDate=${endDate}&searchType=${searchType}&searchName=${searchName}">next</a>
            </c:if>
        </div>
        <div class="btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-outline-secondary" onclick="location.href='/home'">메인</button>
            <button type="button" class="btn btn-outline-secondary" onclick="location.href='/boardposting'">작성</button>
            <c:choose>
                <c:when test="${sessionScope.loginId ne null}"><button type="button" class="btn btn-outline-secondary" onclick="location.href='/logout'">로그아웃</button></c:when>
                <c:otherwise><button type="button" class="btn btn-outline-secondary" onclick="location.href='/login'">로그인</button></c:otherwise>
            </c:choose>
            <c:if test="${sessionScope.loginId eq 'admin'}">
                <button type="button" class="btn btn-outline-secondary" onclick="location.href='/manager'">회원관리</button>
            </c:if>
        </div>
    </div>
</body>
</html>
