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
<title>Registrate</title>
<link rel="stylesheet" href="/css/normalize.css"/>
<link rel="stylesheet" href="/css/navbar.css"/>
<link rel="stylesheet" href="/css/register.css"/>
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
        <a href="/iniciar/sesion">
            <div class="option" >
                <i class="bi bi-person-badge-fill"></i>
                <h4>Iniciar Sesión</h4>
            </div>
        </a>
        </c:if>

        <c:if test="${userInfo == null}"  >
        <a href="/registrarse" class="selected">
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
    <h1 id="theme">Registrarse</h1>
    
    <div class="regform">
        <form method="POST" action="/register">

            <p class="flashmjs">
                <c:out value="${rerrorMessage2}"></c:out>
            </p>

            <div class="spaces">
                <div class="left">
                    
                    <div class="twofi">
                        <div class="field">
                            <label for="name">Nombre:</label>
                            <input class="inputext" type="text" name="name" placeholder="Nombre"/>
                            <p class="flashmjs">
                                <c:out value="${rerrorMessage3}"></c:out>
                            </p>
                        </div>
                        
                        <div class="field">
                            <label for="username">Usuario:</label>
                            <input class="inputext" type="text" name="username" placeholder="Usuario"/>
                            <p class="flashmjs">
                                <c:out value="${rerrorMessage1}"></c:out>
                            </p>
                            <p class="flashmjs">
                                <c:out value="${rerrorMessage4}"></c:out>
                            </p>
                        </div>
                    </div>
                
                    <div class="twofi">
                        <div class="field">
                            <label for="password">Contraseña:</label>
                            <input class="inputext" type="password" name="password" placeholder="Contraseña"/>
                            <p class="flashmjs">
                                <c:out value="${rerrorMessage5}"></c:out>
                            </p>
                        </div>
                        
                        <div class="field">
                            <label for="confpassword">Confirmación:</label>
                            <input class="inputext" type="password" name="confpassword" placeholder="Confirma Contraseña"/>
                            <p class="flashmjs">
                                <c:out value="${rerrorMessage6}"></c:out>
                            </p>
                        </div>
                    </div>

                    <div class="field code">
                        <label for="code">Codigo de Administrador:</label>
                        <input class="inputext inputcode" type="password" name="code" placeholder="Codigo"/>
                        <p class="flashmjs">
                            <c:out value="${rerrorMessage7}"></c:out>
                        </p>
                    </div>

                </div>
                
                <div class="right">
                    <p>Al contrario del pensamiento popular, el texto de Lorem Ipsum no es simplemente texto aleatorio. Tiene sus raices en una pieza cl´sica de la literatura del Latin, que data del año 45 antes de Cristo, haciendo que este adquiera mas de 2000 años de antiguedad. Richard McClintock, un profesor de Latin de la Universidad de Hampden-Sydney en Virginia, encontró una de las palabras más oscuras de la lengua del latín, "consecteur", en un pasaje de Lorem Ipsum, y al seguir leyendo distintos textos del latín, descubrió la fuente indudable.</p>
                </div>
            </div>

            <button id="reg" type="submit">Registrarse</button>
        </form>
    </div>
</main>

<script type="text/javascript" src="/js/navbar.js"></script>
</body>
</html>