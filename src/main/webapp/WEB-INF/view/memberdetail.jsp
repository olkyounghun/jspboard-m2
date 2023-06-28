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
<style>
  .btn {
    background-color: white;
    border: 1px solid black;
    border-radius: 10px;
    padding: 5px 10px;
    font-size: 14px;
    color: black;
  }
</style>
<body>
<c:forEach items="${list}" var="list">
<form method="post" action="/membermodify/${list.id_member}">
    <input type="hidden" id="idMember" name="idMember" value="${list.id_member}">
    <div class="container">
      <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
          <div class="row" style="padding-top: 20px;" >
            <h1> ${list.user_member} </h1>
          </div>
          <div class="row">
            <div class="col-sm-6">
             등급 : ${list.rating_member == 1 ? 'admin' : (list.rating_member == 2 ? 'member' : '')}
            </div>
            <div class="col-sm-6">
              <div class="row">
                <div class="col-sm-4">
                  <div><input id="emailMember" name="emailMember" value="${list.email_member}"></div>
                </div>
                <div class="col-sm-2">
                  <div><input id="nameMember" name="nameMember" value="${list.name_member}"> </div>
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
            <button type="submit" class="btn btn-secondary" >개인정보수정</button>
            <button type="button" class="btn btn-secondary" onclick="deleteCheck(${list.id_member})">탈퇴</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='/boardlist'">목록</button>
          </div>
        </div>
      </div>
      <div class="col-sm-3"></div>
    </div>
</form>
</c:forEach>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
  function deleteCheck(num) {
    if (confirm("정말 탈퇴하시겠습니까??") == true){    //확인
      location.href='/memberdelete/'+num;
    }else{   //취소
      return false;
    }
  } // 로그인아이디가 작성한 글이 있기때문에 글은 아이디정보를 참조하고있어서 해당 아이디를 삭제시 삭제오류가 발생한다. 참조오류
</script>
</body>
</html>