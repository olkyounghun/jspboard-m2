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
        <h1> ${list.user_member} </h1>
      </div>
      <div class="row">
        <div class="col-sm-6">
         등급 : ${list.rating_member}
        </div>
        <div class="col-sm-6">
          <div class="row">
            <div class="col-sm-4">
              <div>${list.email_member}</div>
            </div>
            <div class="col-sm-2">
              <div>${list.name_member} </div>
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
        <button type="button" class="btn btn-secondary" onclick="location.href='/managermodify/${list.id_member}'">수정</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/managerdelete/${list.id_member}'">삭제</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/manager'">목록</button>
      </div>
    </div>
  </div>
  <div class="col-sm-3"></div>
</div>
</c:forEach>
<div class="list-group">
  <c:forEach items="${prev}" var="prev">
    <c:choose>
      <c:when test="${empty prev.fucku}">
        <a href='/managerinfo/${prev.fucku}' class="list-group-item list-group-item-action <c:if test="${empty prev.fucku}">disabled</c:if>">
          <span style="font-weight: bold;">이전 회원</span> │ 이전 회원이 없습니다.</a>
      </c:when>
      <c:otherwise>
        <a href='/managerinfo/${prev.fucku}' class="list-group-item list-group-item-action">
          <span style="font-weight: bold;">이전 회원</span> │
          <span style="color: blue;">${prev.what_user}</span></a>
      </c:otherwise>
    </c:choose>
  </c:forEach>
  <c:forEach items="${next}" var="next">
    <c:choose>
      <c:when test="${empty next.fucku}">
        <a href='/managerinfo/${next.fucku}' class="list-group-item list-group-item-action <c:if test="${empty next.fucku}">disabled</c:if>">
          <span style="font-weight: bold;">다음 회원</span> │ 다음 회원이 없습니다.</a>
      </c:when>
      <c:otherwise>
        <a href='/managerinfo/${next.fucku}' class="list-group-item list-group-item-action">
          <span style="font-weight: bold;">다음 회원</span> │
          <span style="color: blue;">${next.what_user}</span></a>
      </c:otherwise>
    </c:choose>
  </c:forEach>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
