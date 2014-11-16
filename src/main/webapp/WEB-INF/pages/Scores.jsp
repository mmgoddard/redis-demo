<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Redis Demo</title>
    <link href="/redis-demo/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/redis-demo/resources/css/stylesheet.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <h1 style="color:rgba(36,93,183,1)">redis-demo</h1>
    <div>
        <h3><small>High Scores</small></h3>
        <form class="form-inline" action="searchScores" method="post">
            <div class="form-group">
                <label for="search">Search: </label>
                <input type="form-control" id="search" name="search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <p>${rank.get()}</p>
        <p>${score.get()}</p>
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
        <p style="text-align: center">by Mark Goddard & Tom Butterwith</p>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"/>
<script src="/redis-demo/resources/bootstrap/js/bootstrap.min.js"/>
</body>
</html>
