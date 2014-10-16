<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Redis Demo</title>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/stylesheet.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <h1>redis-demo</h1>
    <div>
        <h3><small>High Scores</small></h3>
        <table class="table table-striped table-condensed">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Score</th>
            </tr>
            </thead>
            <tbody>
            <%int i = 1; %>
            <c:forEach items="${elements}" var="item">
                <tr>
                    <td><%=i%></td>
                    <td>${item.element}</td>
                    <td><fmt:formatNumber pattern="000000" value="${item.score}" /></td>
            <% i++; %>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="footer container">
        <p style="text-align: center">by mark goddard & tom butterwith</p>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"/>
<script src="/resources/bootstrap/js/bootstrap.min.js"/>
</body>
</html>
