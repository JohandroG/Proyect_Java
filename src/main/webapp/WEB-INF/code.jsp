<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Code</title>
</head>
<body>
    <nav>
        <a href="/">Back</a>
    </nav>

    <main>
        <div class="form">
            <h1>Cambiar codigo de administrador</h1>
            <form method="POST" action="/new/code">
                <label for="last">Codigo Anterior:</label>
                <input type="text" name="last"/>

                <label for="new">Nuevo Codigo:</label>
                <input type="text" name="new"/>

                <input type="hidden" value="1" name="code_id"/>
                <button type="submit">Cambiar</button>
            </form>
            <p class="flashmjs">
                <c:out value="${cerrorMessage1}"></c:out>
            </p>
            <p class="flashmjs">
                <c:out value="${cerrorMessage2}"></c:out>
            </p>
            <p class="flashmjs">
                <c:out value="${cerrorMessage3}"></c:out>
            </p>
            <p class="flashmjs">
                <c:out value="${cerrorMessage4}"></c:out>
            </p>
        </div>
    </main>
</body>
</html>