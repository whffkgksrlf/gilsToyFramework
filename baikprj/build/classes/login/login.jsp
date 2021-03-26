<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
        <h1>로그인</h1>
        <hr>
        <form th:action="@{/login}" method="post">
            <input type="text" name="username" placeholder="account를 입력해주세요.">
            <input type="password" name="password" placeholder="password를 입력해주세요.">
            <button type="submit">로그인</button>
        </form>
    </body>
</html>