<%--
  Created by IntelliJ IDEA.
  User: kutsenkovitaly
  Date: 09.12.2019
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form:form action="/book" modelAttribute="book" method="post">
        <form:hidden path="id"/> <br/>
        <form:input path="name"/> <br/>
        <form:input path="author"/> <br/>
        <form:input path="isbn"/> <br/>
        <input type="submit" value="Сохранить"/>
    </form:form>

</body>
</html>
