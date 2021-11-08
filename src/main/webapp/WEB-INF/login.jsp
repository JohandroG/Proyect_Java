<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    <nav>
        <a href="/">Back</a>
    </nav>
    
    <main>
        <h1>Iniciar Sesion</h1>
        
        <div class="logform">
            <form method="POST" action="/login">
                <p class="flashmjs">
                    <c:out value="${lerrorMessage1}"></c:out>
                </p>
    
                <div class="left">

                    <label for="username">Usuario:</label>
                    <input type="text" name="username"/>
                    <p class="flashmjs">
                        <c:out value="${lerrorMessage2}"></c:out>
                    </p>
    
                    <label for="password">Contraseña:</label>
                    <input type="text" name="password"/>
                    <p class="flashmjs">
                        <c:out value="${lerrorMessage3}"></c:out>
                    </p>

                </div>
    
                <div class="right">
                    <p>Description</p>
                </div>
    
                <button type="submit">Entrar</button>
            </form>
        </div>
    </main>

</body>
</html>