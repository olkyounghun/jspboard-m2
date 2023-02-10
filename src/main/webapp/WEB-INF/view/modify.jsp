<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-02-03
  Time: 오후 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.text.SimpleDateFormat" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title> Model-2 게시판</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<form method="post" name="frm" onsubmit="return modifyform_check(this)" action="modifyForm.jsp" enctype="multipart/form-data">
    <div>
        <table>
            <tr>
                <td> 카테고리 </td>
                <td>
                    ${list.type_board}
                </td>
            </tr>
            <tr>
                <td> 등록일자 </td>
                <td> ${list.regdate_board} </td>
            </tr>
            <tr>
                <td> 수정일자 </td>
                <td>
                    <c:choose>
                        <c:when test="${list.moddate_board} eq null">${list.moddate_board}</c:when>
                        <c:when test="!${list.moddate_board} eq null">${list.regdate_board}</c:when>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td> 작성자 </td>
                <td> ${list.user_board} </td>
            </tr>
            <tr>
                <td> 제목 </td>
                <td> <label><input type="text" id="boardTitle" name="boardTitle" value="${list.title_board}"></label></td>
            </tr>
            <tr>
                <td> 내용 </td>
                <td> <label><input type="text" id="boardContent" name="boardContent" value="${list.content_board}"></label></td>
            </tr>
        </table>
    </div>
    <div>
        <button type="submit" >수정</button>
        <button type="button" onclick="location.href='list.jsp'">목록</button>
    </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
