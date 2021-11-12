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
<title>Entrar</title>
<link rel="stylesheet" href="/css/normalize.css"/>
<link rel="stylesheet" href="/css/navbar.css"/>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

</head>
<body id="body">

   	
   	<header>
        <div class="icon__menu">
            <i class="bi bi-list" id="btn_open"></i>
        </div>
        <div class="logo">
            <h1>Tablero de Anuncios</h1>
        </div>
</header>

<div class="menu__side" id="menu_side">
    <div class="main__page">
        <i class="bi bi-newspaper"></i>
        <h4>Anuncios</h4>
    </div>

    <div class="options__menu">
        <a href="/">
            <div class="option">
                <i class="bi bi-house-fill" title="inicio"></i>
                <h4>Inicio</h4>
            </div>
        </a>

        <c:if test="${userInfo == null}"  >
        <a href="/iniciar/sesion" class="selected">
            <div class="option">
                <i class="bi bi-person-badge-fill"></i>
                <h4>Iniciar Sesión</h4>
            </div>
        </a>
        </c:if>

        <c:if test="${userInfo == null}"  >
        <a href="/registrarse">
            <div class="option">
                <i class="bi bi-person-lines-fill"></i>
                <h4>Registrarse</h4>
            </div>
        </a>
        </c:if>

        <c:if test="${userInfo != null}"  >
        <a href="/publicar">
            <div class="option">
                <i class="bi bi-file-earmark-post-fill"></i>
                <h4>Publicar Anuncio</h4>
            </div>
        </a>
        </c:if>

        <c:if test="${userInfo != null}"  >
            <a href="/editar/admin">
                <div class="option">
                    <i class="bi bi-person-check-fill"></i>
                    <h4>My Info</h4>
                </div>
            </a>
        </c:if>

            <c:if test="${userInfo != null}"  >
                <a href="/cambiar/codigo">
                    <div class="option">
                        <i class="bi bi-key-fill"></i>
                        <h4>Cambiar Codigo</h4>
                    </div>
                </a>
            </c:if>

        <c:if test="${userInfo != null}"  >
        <a href="/logout">
            <div class="option">
                <i class="bi bi-x-square-fill"></i>
                <h4>Cerrar Sesión</h4>
            </div>
        </a>
        </c:if>

    </div>
</div>
   	
   	
   	
    
    <main>
        <h1>Iniciar Sesion</h1>
        
        <div class="logform">
            <form method="POST" action="/login">
                <p class="flashmjs">
                    <c:out value="${lerrorMessage1}"></c:out>
                </p>
    
                <div class="left">

                    <label for="username">Usuario:</label>
                    <input type="text" name="username" placeholder="Usuario"/>
                    <p class="flashmjs">
                        <c:out value="${lerrorMessage2}"></c:out>
                    </p>
    
                    <label for="password">Contraseña:</label>
                    <input type="text" name="password" placeholder="Contraseña"/>
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
    
<script type="text/javascript" src="/js/navbar.js"></script>
</body>
</html>