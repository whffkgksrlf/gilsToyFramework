<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
        <h1>�α���</h1>
        <hr>
        <form th:action="@{/login}" method="post">
            <input type="text" name="username" placeholder="account�� �Է����ּ���.">
            <input type="password" name="password" placeholder="password�� �Է����ּ���.">
            <button type="submit">�α���</button>
        </form>
    </body>
</html>