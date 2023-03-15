<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-02-02
  Time: 오후 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <meta charset="UTF-8">
  <title>Model-2 게시판</title>
</head>
<body>
<form method="post" action=" 수정하기 ">
  <c:forEach items="${list}" var="list">
    <div class="container">
      <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
          <div class="row" style="padding-top: 20px;" >
            <h1> ${list.user_member} </h1>
          </div>
          <div class="row">
            <div class="col-sm-6">
             등급 : ${list.rating_member}
            </div>
            <div class="col-sm-6">
              <div class="row">
                <div class="col-sm-4">
                  <div><input name="emailMember" value="${list.email_member}"></div>
                </div>
                <div class="col-sm-2">
                  <div><input name="nameMember" value="${list.name_member}"> </div>
                </div>
                <div class="col-sm-2">
                  <div>${list.gender_member} </div>
                </div>
                <div class="col-sm-4">
                  <div>
                    ${list.regdate_member}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="form-check" style="padding-top: 40px;">
            <button type="submit" class="btn btn-secondary" >수정</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='memberdelete?id_board=${list.id_member}'">삭제</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='list'">목록</button>
          </div>
        </div>
      </div>
      <div class="col-sm-3"></div>
    </div>
  </c:forEach>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
