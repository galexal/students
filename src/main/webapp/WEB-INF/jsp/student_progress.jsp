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
    <title>Students Progress Page</title>
    <script src="../../resources/js/functions.js"></script>
</head>
<body>
<div>
    <h1>Система управления студентами и их успеваемостью</h1>
    <a class="Logout" href="">Logout</a>
</div>

<div class="navP">
    <nav>
        <a href="/">На главную</a>
        <a href="/students">Назад</a>
    </nav>
    <p class="p1">Отображена успеваемость для следующего студента:</p>
</div>

<div>
    <table class="table1" border="1">
        <tr>
            <th class="th1">Фамилия</th>
            <th class="th2">Имя</th>
            <th class="th3">Группа</th>
            <th>Дата поступления</th>
        </tr>
        <tr>
            <td>${student.surname}</td>
            <td>${student.name}</td>
            <td>${student.group.name}</td>
            <td>${student.date}</td>
        </tr>
    </table>
</div>


<div class="tableSelect">
    <div class="qqq">
        <table class="table2" border="1">
            <tr>
                <th class="th11">Дисциплина</th>
                <th class="th12">Оценка</th>
            </tr>
            <c:forEach items="${grades}" var="g">
                <tr>
                    <td>${g.discipline.name}</td>
                    <td>${g.value}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="Select">
        <form action="/student_progress">
            <label>
                Выбрать семестр
                <select name="termId">
                    <c:forEach items="${terms}" var="t">
                        <c:choose>
                            <c:when test="${t.id==selectedTerm.id}">
                                <option selected value="${t.id}">${t.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${t.id}">${t.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </label>
            <button class="button1">Выбрать</button>
            <input type="hidden" name="idForProgress" value="${student.id}"/>
        </form>
        <br>
        <p>Средняя оценка за семестр: <input type="hidden"></p>
    </div>
</div>
</body>
</html>
