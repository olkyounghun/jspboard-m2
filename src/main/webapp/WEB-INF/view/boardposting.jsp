<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-01-30
  Time: 오후 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Model-2 게시판</title>
</head>
<style>
    #wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }
    #wrapper1 {
        position: absolute;
        top: 100%;
        left: 50%;
        transform: translate(-50%,-50%);
    }
</style>
<body>
<form method="post" name="frm" action="postingAction" enctype="multipart/form-data"  >
    <div id="wrapper">
        <div>
            <div class="form-check" style= "text-align : center;">
                <label>게시물 등록</label>
            </div>
            <div class="form-check">
                <label>카테고리</label>
                <label>
                    <select id="typeBoard" name="typeBoard" class="form-control">
                        <option value="All" selected>전체 카테고리</option>
                        <option value="JAVA" >JAVA</option>
                        <option value="Javascript" >Javascript</option>
                        <option value="Database" >Database</option>
                    </select>
                </label>
                <div id="type_ck"></div>
            </div>
            <div class="form-check">
                <label> 제목 </label>
                <label>
                    <input type="text" id="titleBoard" name="titleBoard" class="form-control">
                </label>
            </div>
            <div class="form-check">
                <label> 내용 </label>
                <label>
                    <textarea id="contentBoard" name="contentBoard" class="form-control"></textarea>
                </label>
            </div>
            <div id="wrapper1" class="form-check">
                <button type="submit" class="btn btn-secondary">저장</button>
                <button type="button" class="btn btn-secondary" onclick="location.href='boardlist'">목록</button>
            </div>
        </div>
    </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
