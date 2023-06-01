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
  <script
          src="https://code.jquery.com/jquery-3.7.0.min.js"
          integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
          crossorigin="anonymous"></script>
  </body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
          crossorigin="anonymous"></script>
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
          <span class="input-group-text" for="userPw2" id="basic-addon3" name="basic-addon3">재확인</span>
          <input type="password" id="userPw2" name="userPw2" class="form-control" placeholder="작성하신 비밀번호를 다시 한번 입력해주세요.">
          <label name="pwchk" id="pwchk"></label>
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
          <label for="userEmail" id="mailTxt">이메일을 입력해주세요</label>
          <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="Username" aria-label="email">
        </div>
        <div class="mail-check-box">
          <label for="checkcodeinput" name="memailconfirmTxt" id="memailconfirmTxt">인증번호를 입력해주세요</label>
          <input class="form-control mail-check-input" name="checkcodeinput" id="checkcodeinput" >
          <label name="codechk" id="codechk"></label>
        </div>
        <div class="input-group-addon">
          <button type="button" class="btn btn-primary" name="mailCheckBtn" id="mailCheckBtn">인증하기</button>
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
          <button type="submit" class="btn btn-secondary" name="signup" id="signup">가입하기</button>
          &nbsp;
          <button type="button" class="btn btn-secondary" onclick="location='/'"> 뒤로가기 </button>
        </div>
      </div>
    </div>
  </div>
</form>
<script>
  // 이메일 인증번호
  $(document).ready(function(){

    $('#signup').click(signupConfirm())

    $('#userPw2').keyup(function(){
      $('#userPw2').keyup(chkPWConfirm())
    })
    $('#mailCheckBtn').click(function() {
      //alert("AJAX checking Test please god tell me what's problem to button") // 정상작동 확인!
      $.ajax({
        type : "POST",
        url : "code/mailConfirm",
        data : {
          "email" : $('#userEmail').val()
        },
        success : function(data){
          alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
          console.log("data : "+data);
          chkEmailConfirm(data);
        }
      })
    })

    // 비밀번호 일치여부 와 이메일인증에 통과하지않으면 가입이 안되도록
    function signupConfirm(){
      if(pwconfirmchk = false, emconfirmchk = false){
        alert("확인이 필요한 부분이 남아있습니다. \n 확인부탁드립니다.")
        $('#signup').off("click")
      }
    }

    // 비밀번호 재확인 체크 함수
    function chkPWConfirm(){
      if($('#userPw').val() != $('#userPw2').val() ){
        pwconfirmchk = false;
        $('#pwchk').html("<span id='pwconfimchk'>비밀번호가 일치하지않습니다.</span>")
        $('#pwconfimchk').css({
          "color" : "#FA3E3E",
          "font-weight" : "bold",
          "font-size" : "10px"

        })
      } else {
        pwconfirmchk = true;
        $('#pwchk').html("<span id='pwconfimchk'>비밀번호가 일치합니다.</span>")
        $('#signup').on("click")
        $('#pwconfimchk').css({
          "color" : "#0D6EFD",
          "font-weight" : "bold",
          "font-size" : "10px"

        })
      }
    }
    // 이메일 인증번호 체크 함수
    function chkEmailConfirm(data){
      $('#checkcodeinput').on("keyup", function(){
        if (data != $('#checkcodeinput').val()) { //
          emconfirmchk = false;
          $('#codechk').html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
          $('#emconfirmchk').css({
            "color" : "#FA3E3E",
            "font-weight" : "bold",
            "font-size" : "10px"

          })
          //console.log("중복아이디");
        } else { // 아니면 중복아님
          emconfirmchk = true;
          $('#codechk').html("<span id='emconfirmchk'>인증번호 확인 완료</span>")
          $('#checkcodeinput').prop("disabled", true)
          $('#userEmail').prop("disabled", true)
          $('#mailCheckBtn').off("click")
          $('#signup').on("click")
          $('#emconfirmchk').css({
            "color" : "#0D6EFD",
            "font-weight" : "bold",
            "font-size" : "10px"

          })
        }
      })
    }
  })
</script>
</body>
</html>
