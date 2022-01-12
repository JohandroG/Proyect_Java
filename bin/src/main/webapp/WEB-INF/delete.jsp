<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Eliminar</title>
<link rel="stylesheet" href="/css/normalize.css"/>
<link rel="stylesheet" href="/css/delete.css"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body id="body">

    <div class="background">
        <div class="back">
            <a href="/editar/admin">Cancelar</a>
        </div>
    </div>

    <main>

        <div class="title">
            <h1>Advertencia!!</h1>
        </div>

        <div class="desc">
            <p><span>Esta acción borrará tu cuenta permanentemente.</span> Por lo que te recomendamos usarla con cuidado.</p>
            <p>Si desea continuar de todas formas ingrese el codigo de administrador y presione eliminar</p>
        </div>

        <div class="delete">
            <form action="/delete/myuser" method="POST">
                <div class="code">
                    <label>Codigo de admin</label>
                    <input name="code" placeholder="Codigo" type="password"/>
                    <p class="flashmjs">
                        <c:out value="${deleteMessage}"></c:out>
                    </p>
                </div>

                <button id="delete" type="submit">Eliminar definitivamente</button>
            </form>
        </div>

    </main>

</body>
</html>