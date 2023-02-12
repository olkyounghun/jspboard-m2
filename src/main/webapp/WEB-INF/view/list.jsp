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
                <select name="searchType">
                    <option value="ALL" selected>모든 카테고리</option>
                    <option value="JAVA">JAVA</option>
                    <option value="Javascript">Javascript</option>
                    <option value="Database">Database</option>
                </select>
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
                                <td>${list.type_board}</td>
                                <td><a href="/detail?id_board='${list.id_board}'">${list.title_board}</a></td>
                                <td>${list.user_board}</td>
                                <td>${list.views_board}</td>
                                <td><fmt:formatDate value="${list.regdate_board}" pattern="yyyy-MM-dd  HH:mm:ss" type="date"/></td>
                                <td><fmt:formatDate value="${list.moddate_board}" pattern="yyyy-MM-dd  HH:mm:ss" type="date"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="paging">
        <!-- 1~10까지 있는 페이지의 페이징 -->
        <c:if test="${paging.prev}">
            <a href=list?page=${paging.beginPage-1}">prev</a>
        </c:if>
        <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
            <c:choose>
                <c:when test="${paging.page==index}">
                    ${index}
                </c:when>
                <c:otherwise>
                    <a href="list?page=${index}">${index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${paging.next}">
            <a href="list?page=${paging.endPage+1}">next</a>
        </c:if>
    </div>
</body>
</html>
