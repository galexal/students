<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <title>Students Page</title>
    <script src="../../resources/js/functions.js"></script>
</head>
<body>
<div>
    <h1>Система управления студентами и их успеваемостью</h1>
    <a class="Logout" href="">Logout</a>
</div>
<div class="a_aButton">
    <div class="a">
        <nav>
            <a href="/">На главную</a>
        </nav>
    </div>
    <div class="eeerrr">
        <div class="e"><a href="studentProgress.html">
            <button class="e11"> Просмотреть успеваемость выбранных студентов</button>
        </a></div>
        <div class="ee"><a>
            <form action="/student_create" method="get">
                <button class="e22">Создать студента...</button>
            </form>
        </a> <br></div>
        <br>
        <div class="r"><a>
            <button onclick="modifyStudents()" class="r11">Модифицировать выбранного студента...</button>
        </a></div>
        <div class="rr"><a>
            <button onclick="deleteStudents()" class="r22">Удалить выбранных студентов</button>
        </a></div>
    </div>
</div>
<div>
    <table class="studentsListTable" border="1">
        <caption>Список студентов</caption>
        <tr>
            <th></th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        <c:forEach items="${students}" var="s">
            <tr>
                <form>
                    <label>
                        <td><input type="checkbox" value="${s.id}" name="studentId"></td>
                        <td>${s.surname}</td>
                        <td>${s.name}</td>
                        <td>${s.group.name}</td>
                        <td><f:formatDate value="${s.date}" pattern="d/MM/yyyy"/></td>
                    </label>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<form action="/student_delete" method="post" id="deleteForm">
    <input type="hidden" name="idsForDelete" id="idsForDelete">
</form>
<form action="/student_modify" method="get" id="modifyForm">
    <input type="hidden" name="idForModify" id="idForModify">
</form>
</html>