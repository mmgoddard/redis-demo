<%@ page import="org.joda.time.DateTime" %>
<%@ page import="org.joda.time.format.DateTimeFormat" %>
<%@ page import="org.joda.time.format.DateTimeFormatter" %>
<%@ page import="redis.clients.jedis.Response" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h3><small>Articles</small></h3>
        <form class="form-inline" action="addArticle" method="post">
            <div class="form-group">
                <label for="article">Add Article: </label>
                <input type="form-control" id="article" name="article">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <table class="table table-striped table-condensed">
            <thead>
            <tr>
                <th>#</th>
                <th>Date</th>
                <th>Article Name</th>
            </tr>
            </thead>
            <tbody>
            <%int i = 1;
                DateTimeFormatter formatter = DateTimeFormat.forPattern("kk:mm:ss dd/MM/yy");
            %>
            <c:set var="j" value="0" scope="page"/>
            <c:forEach items="${list}" var="item">

                <tr>
                    <td><%=i%></td>
                    <td>${formattedList.get(j)}</td>
                    <td>${values.get(item).get()}</td>
                        <% i++; %>
                    <c:set var="j" value="${count + 1}" scope="page" />
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="footer container">
    <p style="text-align: center">by Mark Goddard & Tom Butterwith</p>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"/>
<script src="/redis-demo/resources/bootstrap/js/bootstrap.min.js"/>
</body>
</html>
