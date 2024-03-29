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
<style>
  .btn {
    background-color: white;
    border: 1px solid black;
    border-radius: 10px;
    padding: 5px 10px;
    font-size: 14px;
  }
</style>
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
        <div class="col-sm-12" style="padding-top: 10px;">댓글
          <div>
            ${sessionScope.loginId} 님
          </div>
          <div>
            내용 : <input type="text">
          </div>
        </div>
      </div>
      <div class="form-check" style="padding-top: 40px;">
        <button type="button" class="btn btn-secondary" name="modify" id="modify" onclick="modifyCheck(${id_member},${list.id_member},${list.id_board})">수정</button>
        <button type="button" class="btn btn-secondary" name="delete" id="delete" onclick="deleteCheck(${id_member},${list.id_member},${list.id_board})">삭제</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/boardlist'">목록</button>
      </div>
    </div>
  </div>
</c:forEach>
  <div class="list-group">
    <c:forEach items="${prev}" var="prev">
      <c:choose>
        <c:when test="${empty prev.fucku}">
          <a href='/boarddetail/${prev.fucku}' class="list-group-item list-group-item-action <c:if test="${empty prev.fucku}">disabled</c:if>">
            <span style="font-weight: bold;">이전글</span> │이전글이 없습니다.</a>
        </c:when>
        <c:otherwise>
          <a href='/boarddetail/${prev.fucku}' class="list-group-item list-group-item-action">
            <span style="font-weight: bold;">이전글</span> │
            <span style="color: blue;">${prev.what_title}</span></a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:forEach items="${next}" var="next">
      <c:choose>
        <c:when test="${empty next.fucku}">
          <a href='/boarddetail/${next.fucku}' class="list-group-item list-group-item-action <c:if test="${empty next.fucku}">disabled</c:if>">
          <span style="font-weight: bold;">다음글</span> │다음글이 없습니다.</a>
          </c:when>
          <c:otherwise>
          <a href='/boarddetail/${next.fucku}' class="list-group-item list-group-item-action">
            <span style="font-weight: bold;">다음글</span> │
              <span style="color: blue;">${next.what_title}</span></a>
          </c:otherwise>
        </c:choose>
    </c:forEach>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript">
  function removeCheck(num) {
    if (confirm("정말 삭제하시겠습니까??") == true){    //확인
        location.href='/boarddelete/'+num;
    }else{   //취소
      return false;
    }
  }
  function deleteCheck(loginnum,idnum,num){
    if(loginnum == idnum) {
      removeCheck(num);
    }else if(loginnum == 1){
      removeCheck(num);
    }else{
      alert("해당 글의 작성자가 아니십니다. \n 삭제권한이 없습니다.");
    }
  }
  function modifyCheck(loginnum,idnum,num){
    if(loginnum == idnum){
      location.href='/boardmodify/'+num;
    }else if(loginnum == 1){
      location.href='/boardmodify/'+num;
    }else{
      alert("해당 글의 작성자가 아니십니다. \n 수정권한이 없습니다.");
    }
  }
</script>
</body>
</html>
