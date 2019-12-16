<%--
  Created by IntelliJ IDEA.
  User: kutsenkovitaly
  Date: 09.12.2019
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "sec" uri= "http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Список доступных книг.</h1>
<div>
    <c:forEach items="${books}" var="book">
        <a href="/book/${book.isbn}">${book.author}. ${book.name} </a> <br/>
    </c:forEach>


</div>
</body>
</html>
