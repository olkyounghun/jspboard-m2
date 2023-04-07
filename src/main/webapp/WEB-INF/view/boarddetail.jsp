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
<div>
  <c:if test="${sessionScope.loginId ne null}">
    <a href="/memberdetail/${id_member}">${sessionScope.loginId}</a>님 환영합니다.
  </c:if>
</div>
<c:forEach items="${list}" var="list">
<div class="container">
  <div class="row">
    <div class="col-sm-3"></div>
    <div class="col-sm-6">
      <div class="row" style="padding-top: 20px;" >
        <h1> ${list.title_board} </h1>
      </div>
      <div class="row">
        <div class="col-sm-6">
          카테고리 : ${list.type_board}
        </div>
        <div class="col-sm-6">
          <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-4">
              <div>${list.user_board} </div>
            </div>
            <div class="col-sm-6">
              <div>
                <c:choose>
                  <c:when test="${not empty list.moddate_board}">${list.regdate_board}</c:when>
                  <c:when test="${empty list.moddate_board}">${list.moddate_board}</c:when>
                </c:choose>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row" style="padding-top: 10px;">
        <div class="com-sm-12">
                        <textarea style="outline: 1px solid gray; width: 100%; height: 6.25em; border: none; resize: none;" disabled>
                            ${list.content_board}
                        </textarea>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12" style="padding-top: 10px;"> 수정중
        </div>
      </div>
      <div class="form-check" style="padding-top: 40px;">
        <button type="button" class="btn btn-secondary" onclick="location.href='/boardmodify/${list.id_board}'">수정</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/boarddelete/${list.id_board}'">삭제</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/boardlist'">목록</button>
      </div>
    </div>
  </div>
  <div class="col-sm-3"></div>
</div>
</c:forEach>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
