<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-12-29
  Time: 오후 6:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
  .container{
    padding: 5px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
  }
  .input{
    width:50px;
    height:200px;
    font-size:20px;
  }
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
  <title>Model-2 게시판</title>
</head>
<body>
<div>
  <div class="container">
    <div class="row">
      <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon1">아이디</span>
        <input type="text" name="userMember" class="form-control" placeholder="USER ID">
      </div>
    </div>
    <div class="row">
      <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon2">비밀번호</span>
        <input type="password" name="userPw" class="form-control" placeholder="비밀번호">
      </div>
      <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon3">재확인</span>
        <input type="password" name="userPw2" class="form-control" placeholder="작성하신 비밀번호를 다시 한번 입력해주세요.">
      </div>
    </div>
    <div class="row">
      <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon4">닉네임</span>
        <input type="text" name="userMember" class="form-control" placeholder="닉네임">
      </div>
      <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon5">성별</span>
        &nbsp;
        <div class="form-check-label">
          <input class="form-check-input" type="radio" name="userGender" id="userGender1">
          <label class="form-check-label" for="userGender1">
            남자
          </label>
          <input class="form-check-input" type="radio" name="userGender" id="userGender2">
          <label class="form-check-label" for="userGender2">
            여자
          </label>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Username" aria-label="Username">
        <span class="input-group-text">@</span>
        <input type="text" class="form-control" placeholder="Server" aria-label="Server">
      </div>
      <div class="input-group mb-3">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="emailChk" id="emailChk">
          <label class="form-check-label" for="emailChk">
            이메일 수신 거부
          </label>
        </div>
      </div>
    </div>
    <div class="row align-items-end">
      <div class="input mb-3" style="margin: auto;">
        <button type="button" class="btn btn-secondary">가입하기</button>
        &nbsp;
        <button type="button" class="btn btn-secondary"> 목록 </button>
      </div>
    </div>
  </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
