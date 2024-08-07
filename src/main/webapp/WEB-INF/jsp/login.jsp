<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <title>Login Page</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
</head>
<body>
<span>
        <h1>Система управления студентами и их успеваемостью</h1>
    </span>
<div class="navP">
    <p class="p1">Вход в систему</p>
</div>
<form action="/login" method="post">
    <div class="input1"><label>Логин <input type="text" name="login"></label></div>
    <div class="input2"><label>Пароль <input type="text" name="password"></label></div>
    <div class="input3"><label>Права
        <select name="roleId">
            <c:forEach items="${roles}" var="r">
                <option value="${r.id}">${r.name}</option>
            </c:forEach>
        </select>
    </label></div>
    <div class="button2">
        <button>Войти</button>
    </div>
</form>

<c:if test="${error eq 1}">
    <h4 class="p4">Ошибка во введённых данных!</h4>
</c:if>
</body>
</html>
