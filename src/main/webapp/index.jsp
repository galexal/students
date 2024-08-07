<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/style.css">
    <title>Title Page</title>
</head>
<body>
    <span>
        <h1>Система управления студентами и их успеваемостью</h1>
            <c:choose>
                <c:when test="${isAuthorised eq true}">
                    <p>Привет, ${login}!</p>
                    <a class="Logout" href="/logout">Logout</a>
                </c:when>
                <c:otherwise>
                    <a class="Logout" href="/login">Login</a>
                </c:otherwise>
            </c:choose>
    </span>

    <div class="a3">
        <a class="a3_1" href="/students">Студенты</a>
        <a class="a3_2" href="disciplines’List.html">Дисциплины</a>
        <a class="a3_3" href="termsList.html">Семестры</a>
    </div>

</body>
</html>