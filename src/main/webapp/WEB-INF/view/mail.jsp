<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-05-15
  Time: 오후 10:57
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<body>
<div style="margin:100px;">
  <h1> 안녕하세요.</h1>
  <h1> 알고리즘 관리 서비스 CODEBOX 입니다.</h1>
  <br>
  <p> 아래 코드를 회원가입 창으로 돌아가 입력해주세요.</p>
  <br>

  <div align="center" style="border:1px solid black; font-family:verdana;">
    <h3 style="color:blue"> 회원가입 인증 코드 입니다. </h3>
    <div style="font-size:130%" th:text="${code}"> </div>
  </div>
  <br/>
</div>


</body>
</html>
