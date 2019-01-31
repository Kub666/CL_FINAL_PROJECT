<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.2.1/darkly/bootstrap.min.css"
          crossorigin="anonymous">
    <title>Users</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index"><h3>GAMING BACKLOG</h3></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03"
            aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor03">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="backlog">Your backlog</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="otherBacklogs">Others backlogs</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="filter">Games filter</a>
            </li>
        </ul>
        <div class="navbar-nav mr-auto">
            <div class="navbar-item">Logged in as <b>${sessionScope.user}</b></div>
        </div>
        <div class="navbar-nav mr-auto">
            <a href="login" class="navbar-item">Logout</a></div>
        <form class="form-inline my-2 my-lg-0" action="search" method="post">
            <input class="form-control mr-sm-2" type="text" name="query" placeholder="Game name">
            <button class="btn btn-secondary my-2 my-sm-0" type="submit">Find a game</button>
        </form>
    </div>
</nav>
<div class="container">
    <BR><BR>
    <h3 class="text-center">Choose a user to see their backlog</h3>
    <BR><BR>
    <div class="row justify-content-md-center">
        <div class="col col-lg-2">
            <table class="table table-hover">
                <tbody>
                <c:forEach items="${users}" var="user">
                <tr>
                    <td class="text-center"><a href="/otherBacklog?id=${user.id}">
                        <button type="button" class="btn btn-secondary">${user.name}</button>
                    </a>
                    </td>
                </tr>
                </c:forEach></tbody></table>
        </div>
    </div>

<br><br>
<p class="text-secondary text-center">All data from <a href="http://www.giantbomb.com">GiantBomb</a>.</p>
</div>
</body>
</html>