<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Model-2 게시판</title>
</head>
<body>
    <div class="maincontainer">
        <div>
            <table>
                <thead>
                    <tr>
                        <td>글 번호</td>
                        <td>제목</td>
                        <td>작성자</td>
                        <td>작성날짜</td>
                        <td>수정날짜</td>
                    </tr>
                </thead>
                <tbody>
                    <%for(int i = 0; i<1; i++){ %>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
