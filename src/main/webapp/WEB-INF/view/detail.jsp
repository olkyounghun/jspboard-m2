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
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.sql.Timestamp" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <meta charset="UTF-8">
  <title>Model-2 게시판</title>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-sm-3"></div>
    <div class="col-sm-6">
      <div class="row" style="padding-top: 20px;" >
        <h1> <%=board.getBoard_title()%> </h1>
      </div>
      <div class="row">
        <div class="col-sm-6">
          카테고리 : <%=board.getCategory_type()%>
        </div>
        <div class="col-sm-6">
          <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-4">
              <div><%=board.getBoard_user()%> </div>
            </div>
            <div class="col-sm-6">
              <div>
                <%if(board.getBoard_moddate() == null){%> <%=boardRegdate%><%} else{%><%=boardModdate%><%}%>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row" style="padding-top: 10px;">
        <div class="com-sm-12">
                        <textarea style="outline: 1px solid gray; width: 100%; height: 6.25em; border: none; resize: none;" disabled>
                            <%=board.getBoard_content()%>
                        </textarea>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12" style="padding-top: 10px;"> 파일 :
          <% for(String bfile : chkfiles){ %>
          <a href="${pageContext.request.contextPath}/downloadForm.jsp?boardId=<%=boardId%>&fileName=<%=bfile%>">
            <%=boardFile%>
          </a>
          <%}%>
        </div>
      </div>
      <div id="commentp" style="padding-top: 40px;">
        <table>
          <thead>
          <tr>
            <td>댓글</td>
            <td></td>
            <td></td>
          </tr>
          </thead>
          <tbody>
          <%
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for(int i = 0; i < list.size(); i++){
          %>
          <tr>
            <td style="weight : 90%;">
              <label>
                <%=list.get(i).getCment_user()%>
              </label>
            </td>
            <td style="weight : 90%;">
              <label>
                <%=list.get(i).getCment_content()%>
              </label>
            </td>
            <td style="weight : 90%;">
              <label>
                <% String regDate = simple.format( list.get(i).getCment_regdate() ); %>
                <%= regDate %>
              </label>
            </td>
          </tr>
          <%
            }
          %>
          </tbody>
        </table>
        <form method="post" onsubmit="commentform_chk(this)" action="commentForm.jsp?boardId=<%=board.getBoard_id()%>" style="padding-top: 20px;">
          <div class="container">
            <div class="row">
              <div class="col-sm-6">
                ID : <input type="text" name="cmentUser">
              </div>
              <div class="col-sm-6">
                PW : <input type="password" name="cmentPw">
              </div>
            </div>
            <div class="row" style="padding: 5px;">
              <div class="col-sm-9">
                <input type="text" name="cmentContent" style="width: 100%; height: 6.25em; border: 2px solid rgb(128, 128, 128);; outline-color: #FE6B8B; resize: none;">
              </div>
              <div class="col-sm-3">
                <button type="submit" class="btn btn-secondary">작성</button>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="form-check" style="padding-top: 40px;">
        <button type="button" class="btn btn-secondary" onclick="location.href='password.jsp?boardId=<%=board.getBoard_id()%>&type=m'">수정</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='password.jsp?boardId=<%=board.getBoard_id()%>&type=d'">삭제</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='index.jsp'">목록</button>
      </div>
    </div>
  </div>
  <div class="col-sm-3"></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
