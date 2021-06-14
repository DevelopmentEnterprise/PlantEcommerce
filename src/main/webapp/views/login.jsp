<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Вход в аккаунт</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">

      <link rel="stylesheet" href="resources/css/main.css">
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
              <div class="register-form__header">Войти в профиль</div>

              <form:form method="POST" action="/login" modelAttribute="login">
                  <spring:bind path="username">
                      <div class="form-field ${status.error ? 'has-error' : ''}">
                      <form:label path="username" cssClass="form-label">
                          Email
                      </form:label>
                          <form:input type="text" path="username" class="form-input" placeholder="Email"
                                      autofocus="true"></form:input>
                          <form:errors path="username"></form:errors>
                      </div>
                  </spring:bind>

                  <spring:bind path="password">
                      <div class="form-field ${status.error ? 'has-error' : ''}">
                          <form:label path="password" cssClass="form-label">
                              Пароль
                          </form:label>
                          <form:input type="password" path="password" class="form-input" placeholder="Пароль"></form:input>
                          <form:errors path="password"></form:errors>
                      </div>
                  </spring:bind>
                  <div class="trigger-btn-cont" style="display: flex; justify-content: center;">
                      <button type="submit" class="register-btn">Войти</button>
                  </div>
              </form:form>

              <p style="margin-top: 15px; font-size: 14px; word-wrap: break-word;">Продолжая, вы соглашаетесь с правилами использования сервиса</p>
              <div style="margin-top: 45px;">Уже зарегистрированы? <a href="" style="color: blue;">Войти</a></div>
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
