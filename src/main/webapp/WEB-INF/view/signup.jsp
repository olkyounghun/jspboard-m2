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
<form method="post" action="signup" >
  <div>
    <div class="container">
      <div class="row">
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">아이디</span>
          <input type="text" id="userMember" name="userMember" class="form-control" placeholder="USER ID">
        </div>
      </div>
      <div class="row">
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon2">비밀번호</span>
          <input type="password" id="userPw" name="userPw" class="form-control" placeholder="비밀번호">
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon3">재확인</span>
          <input type="password" id="userPw2" name="userPw2" class="form-control" placeholder="작성하신 비밀번호를 다시 한번 입력해주세요.">
        </div>
      </div>
      <div class="row">
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon4">닉네임</span>
          <input type="text" id="userName" name="userName" class="form-control" placeholder="닉네임">
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon5">성별</span>
          &nbsp;
          <div class="form-check-label">
            <input class="form-check-input" type="radio" name="userGender" id="userGenderM" value="male">
            <label class="form-check-label" for="userGenderM">
              남자
            </label>
            <input class="form-check-input" type="radio" name="userGender" id="userGenderF" value="female">
            <label class="form-check-label" for="userGenderF">
              여자
            </label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="input-group mb-3">
          <label for="userEmail1" id="mailTxt">이메일을 입력해주세요</label>
          <input type="text" class="form-control" id="userEmail1" name="userEmail1" placeholder="Username" aria-label="Username">
          <span class="input-group-text">@</span>
          <input type="text" class="form-control" id="userEmail2" name="userEmail2" placeholder="Server" aria-label="Server">
        </div>
        <div class="mail-check-box">
          <label for="check-code-input" id="memailconfirmTxt">인증번호를 입력해주세요</label>
          <input class="form-control mail-check-input" id="check-code-input" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
        </div>
        <div class="input-group-addon">
          <button type="button" class="btn btn-primary" id="mail-Check-Btn">인증번호받기</button>
          <button type="button" class="btn btn-primary" id="code-Check-Btn">인증하기</button>
        </div>
      </div>
        <div class="input-group mb-3">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" name="emailChk" id="emailChk" value="False">
            <label class="form-check-label" for="emailChk">
              이메일 수신 거부
            </label>
          </div>
        </div>
      </div>
      <div class="row align-items-end">
        <div class="input mb-3" style="margin: auto;">
          <button type="submit" class="btn btn-secondary">가입하기</button>
          &nbsp;
          <button type="button" class="btn btn-secondary" onclick="location='/'"> 뒤로가기 </button>
        </div>
      </div>
    </div>
  </div>
</form>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
<script type="text/javascript">
  // 이메일 인증번호
  $checkEmail.click(function() {
    $.ajax({
      type : "POST",
      url : "login/mailConfirm",
      data : {
        "email" : $memail.val()
      },
      success : function(data){
        alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
        console.log("data : "+data);
        chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt);
      }
    })
  })

  // 이메일 인증번호 체크 함수
  function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
    $memailconfirm.on("keyup", function(){
      if (data != $memailconfirm.val()) { //
        emconfirmchk = false;
        $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
        $("#emconfirmchk").css({
          "color" : "#FA3E3E",
          "font-weight" : "bold",
          "font-size" : "10px"

        })
        //console.log("중복아이디");
      } else { // 아니면 중복아님
        emconfirmchk = true;
        $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")

        $("#emconfirmchk").css({
          "color" : "#0D6EFD",
          "font-weight" : "bold",
          "font-size" : "10px"

        })
      }
    })
  }
</script>
