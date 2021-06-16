<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Registration</title>

    <link href="resources/css/main.css" rel="stylesheet">
</head>

<body>

    <style>
        @media (max-width: 600px) {
            footer div:not(:last-child){
                margin-bottom: 15px;
            }
        }
    </style>

    <main class="page-component" style="display: flex; justify-content: center;">

        <div class="register-form">
            <div class="logo-cont" style="margin-bottom: 18px; display: flex; justify-content: center;">
                <span>"ЛОГОТИП КОМПАНИИ"</span>
            </div>
            <div class="register-form__header">Создать профиль</div>

            <form:form method="POST" modelAttribute="userForm">
                <spring:bind path="email">
                    <div class="form-field ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="email" class="form-input" placeholder="Email"
                                    autofocus="true"></form:input>
                        <form:errors path="email"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-field ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="password" class="form-input" placeholder="Пароль"></form:input>
                        <form:errors path="password"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="passwordConfirm">
                    <div class="form-field ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="passwordConfirm" class="form-input"
                                    placeholder="Подтверждение пароля"></form:input>
                        <form:errors path="passwordConfirm"></form:errors>
                    </div>
                </spring:bind>

                <div class="trigger-btn-cont" style="display: flex; justify-content: center;">
                    <button type="submit" class="register-btn">Зарегистрироваться</button>
                </div>
            </form:form>

            <p style="margin-top: 15px; font-size: 14px; word-wrap: break-word;">Продолжая, вы соглашаетесь с правилами использования сервиса</p>
            <div style="margin-top: 45px;">Уже зарегистрированы? <a href="/login" style="color: blue;">Войти</a></div>
        </div>
    </main>

    <div style="height: 500px;"></div>

    <footer style="color: #fff;">
        <div>
            <a href="">Условия использования</a>
        </div>
        <div>
            <a href="">Уведомление о конфиденциальности</a>
        </div>
        <div>
            <a href="">Помощь</a>
        </div>
    </footer>
</body>
</html>