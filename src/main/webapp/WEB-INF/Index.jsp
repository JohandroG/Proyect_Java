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
<title>Tablero de Anuncios</title>
<link rel="stylesheet" href="/css/normalize.css"/>
<link rel="stylesheet" href="/css/index.css"/>
<link rel="stylesheet" href="/css/navbar.css"/>
<!-- imports y CSS personal -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Alegreya+Sans:ital,wght@0,100;0,300;0,500;1,100;1,300;1,500&display=swap" rel="stylesheet">
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
        <a href="/" class="selected">
            <div class="option">
                <i class="bi bi-house-fill" title="inicio"></i>
                <h4>Inicio</h4>
            </div>
        </a>

        <c:if test="${userInfo == null}"  >
        <a href="/iniciar/sesion">
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




    <!-- <img src="/images/2pefil.jpg"/> -->

    <main>
        <div class="search">
            <form method="POST" action="/search">
                <label for="search">Buscar</label>
                <input type="text" name="search"/>

                <label for="important">Important</label>
                <input type="checkbox" name="important"/>
                <button type="submit">Search</button>

                <c:if test="${word == 'filter' }"  >
                    <a href="/">Quitar filtro</a>
                </c:if>
            </form>
        </div>

        <div>
            <c:forEach var="info" items="${noticesInfo}">
                    <div class="info" style="margin: 40px; border: 2px solid black;">
                        
                        <c:if test="${info.getImportance() != 'off' }"  >
                            <p>Importante</p>
                        </c:if>
        
                        <c:if test="${info.getLink() != '' }"  >
                            <a href="${info.getLink()}">Link</a>
                        </c:if>
        
                        
                        <c:out value = "${info.getTitle()}" ></c:out>
                        <div id="description">
                            <c:out value = "${info.getDescription()}" ></c:out>
                        </div>
                        <c:if test="${userInfo != null}"  >
                            <a href="/editar/${info.getNotice_id()}">Edit</a>
                        </c:if>
                        <a href="/info/${info.getNotice_id()}">Ver mas</a>
                        <img style="width: 100px;" src="<c:url value="${info.getImg()}"></c:url>" />
                        <img style="width: 100px;" src="<c:url value="${info.getImg2()}"></c:url>" />
                    </div>
            </c:forEach>
        </div>
    </main>

    <script type="text/javascript" src="/js/index.js"></script>
    <script type="text/javascript" src="/js/navbar.js"></script>

</body>
</html>