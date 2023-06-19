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
<c:forEach items="${list}" var="list">
    <form method="post" name="frm" action="/mModifyAction/${list.id_member}" enctype="multipart/form-data">
    <div>
        <table>
            <tr>
                <td> 아이디 </td>
                <td>
                    <label><input type="text" id="userMember" name="userMember" value="${list.user_member}"></label>
                </td>
            </tr>
            <tr>
                <td> 가입날짜 </td>
                <td>
                    <label><input type="text" id="regdateMember" name="regdateMember" value="${list.regdate_member}"></label></td>
            </tr>
            <tr>
                <td> 이름 </td>
                <td>
                    <label><input type="text" id="nameMember" name="nameMember" value="${list.name_member}"></label>
                </td>
            </tr>
            <tr>
                <td> 성별 </td>
                <td>
                    <label><input type="text" id="genderMember" name="genderMember" value="${list.gender_member}"></label>
                </td>
            </tr>
            <tr>
                <td> 이메일 </td>
                <td>
                    <label><input type="text" id="emailMember" name="emailMember" value="${list.email_member}"></label>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <button type="submit" >수정</button>
        <button type="button" onclick="location.href='/boardlist'">목록</button>
    </div>
    </c:forEach>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
