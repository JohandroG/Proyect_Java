<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrate</title>
</head>
<body>

<nav>
    <a href="/">Back</a>
</nav>

<main>
    <h1>Registrarse</h1>
    
    <div class="regform">
        <form method="POST" action="/register">

            <p class="flashmjs">
                <c:out value="${rerrorMessage2}"></c:out>
            </p>

            <div class="left">
                
                <label for="name">Nombre Completo:</label>
                <input type="text" name="name"/>
                <p class="flashmjs">
                    <c:out value="${rerrorMessage3}"></c:out>
                </p>

                <label for="username">Usuario:</label>
                <input type="text" name="username"/>
                <p class="flashmjs">
                    <c:out value="${rerrorMessage1}"></c:out>
                </p>
                <p class="flashmjs">
                    <c:out value="${rerrorMessage4}"></c:out>
                </p>

                <label for="password">Contraseña:</label>
                <input type="text" name="password"/>
                <p class="flashmjs">
                    <c:out value="${rerrorMessage5}"></c:out>
                </p>

                <label for="confpassword">Confirmar Contraseña:</label>
                <input type="text" name="confpassword"/>
                <p class="flashmjs">
                    <c:out value="${rerrorMessage6}"></c:out>
                </p>

                <label for="code">Codigo de Administrador:</label>
                <input type="text" name="code"/>
                <p class="flashmjs">
                    <c:out value="${rerrorMessage7}"></c:out>
                </p>

            </div>

            <div class="right">
                <p>Description</p>
            </div>

            <button type="submit">Registrarse</button>
        </form>
    </div>
</main>

</body>
</html>