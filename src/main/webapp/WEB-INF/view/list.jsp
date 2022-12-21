<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Model-2 게시판</title>
</head>
<style>
    .maincontainer{
        width: 50%;
        height: 50%;
        transform: translate(-50%,-50%);
    }
</style>
<body>
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
    <div class="maincontainer">
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
                            <td>${list.board_id}</td>
                            <td>${list.category_type}</td>
                            <td><a href="/board/list?board_id='${list.board_id}'">${list.board_title}</a></td>
                            <td>${list.board_user}</td>
                            <td>${list.board_views}</td>
                            <td><fmt:formatDate value="${list.board_regdate}" pattern="yyyy-MM-dd  HH:mm:ss" type="date"/></td>
                            <td><fmt:formatDate value="${list.board_moddate}" pattern="yyyy-MM-dd  HH:mm:ss" type="date"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
