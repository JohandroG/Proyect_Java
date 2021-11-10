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
<title>Publicar </title>
<link rel="stylesheet" href="/css/normalize.css"/>
<link rel="stylesheet" href="/css/navbar.css"/>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

</head>
<body id="body">

   	
   	<header>
    <div class="icon__menu">
        <i class="bi bi-menu-button-wide-fill" id="btn_open"></i>
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
        <a href="/iniciar/sesion">
            <div class="option" >
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
        <a href="/publicar" class="selected">
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


    <nav>
        <a href="/">Back</a>
    </nav>
    
    <main>
        <form action="/publish" method="POST" enctype="multipart/form-data"> 
            <p class="flashmjs">
                <c:out value="${errorMessage2}"></c:out>
            </p>

            <div class="left">

            <label for="topic">Tema:</label>
            <input type="text" name="topic"/>
            <p class="flashmjs">
                <c:out value="${errorMessage3}"></c:out>
            </p>

            <label for="desc">Descripción:</label>
            <textarea type="text" name="desc"></textarea>
            <p class="flashmjs">
                <c:out value="${errorMessage4}"></c:out>
            </p>

            </div>
            
            <div class="right">
                <div class="images">
                <label>Incluye Imagen</label>
                <input type="file" id="newImage1" name="newImage1" accept="image/png, image/jpeg"/>
                <p class="flashmjs">
                    <c:out value="${errorMessage1}"></c:out>
                </p>

                <label>Imagen Extra</label>
                <input type="file" id="newImage2" name="newImage2" accept="image/png, image/jpeg"/>
                </div>

                <div class="link">
                    <label for="link">Enlace:</label>
                    <input type="text" name="link"/>
                </div>
            </div>
            <div class="botton">
                <input type="checkbox" name="important">
                <label for="important">Importante</label>
            </div>
            <button type="submit">Publicar</button>
        </form>
    </main>

<script type="text/javascript" src="/js/navbar.js"></script>


</body>
</html>