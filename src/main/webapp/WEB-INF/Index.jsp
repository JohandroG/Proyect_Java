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
<link rel="stylesheet" href="/css/navbar.css"/>
<link rel="stylesheet" href="/css/index.css"/>
<!-- imports y CSS personal -->
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

    <main>
        <div class="search">
            <form method="POST" action="/search">
                <div class="searchf">
                    <div class="searchdiv">
                        <input id="searchform" type="text" placeholder="Buscar" name="search"/>
                        <button id="searchbutt" type="submit"><i class="bi bi-search"></i></button>
                    </div>
    
                    <div class="searchdiv imp">
                        <input id="chek" type="checkbox" name="important"/>
                        <label id="chekinfo" for="important">Importantes</label>
                        
                        <c:if test="${word == 'filter' }"  >
                        <a id="fil" href="/">Quitar filtro</a>
                        </c:if>
                    </div>

                    </div>
            </form>
        </div>

        <div class="notices">
            <c:forEach var="info" items="${noticesInfo}">
                    <div class="info">
                        
                        <div class="top">



                            <!-- <div class="anuncio">
                                <h4>Anuncio</h4>
                            </div> -->


                            <div>
                                <c:if test="${info.getImportance() != 'off' }"  >
                                <div class="important">
                                        <p>Importante!</p>
                                </div>
                                </c:if>
                            </div>

                            
                            
                            <div>
                                <c:if test="${userInfo != null}"  >
                                <div class="edit">
                                    <a href="/editar/${info.getNotice_id()}">
                                        <div class="edit">
                                            <i class="bi bi-pencil-square"></i>
                                            <h4>Editar</h4>
                                        </div>
                                    </a>
                                </div>
                                </c:if>
                            </div>

                        </div>

                        <div class="noticeinfo">
                            
                            <div id="imgcontainer">
                                <img alt="Cargando Imagen" id="img" src="<c:url value="${info.getImg()}"></c:url>" />
                            </div>
                            
                            <div class="generalinfo">
                                <div class="tianddesc">
                                    <div class="title">
                                                ${info.getTitle()}
                                    </div>
                                    
                                    <div id="description">
                                        ${info.getDescription()}
                                    </div>
                                </div>
                                
                                <div class="linkandmore">

                                    
                                        <c:if test="${info.getLink() != '' }"  >
                                        <div>
                                            <a href="${info.getLink()}">
                                                <div class="link">
                                                    <i class="bi bi-link-45deg"></i>
                                                    <h4>Enlace</h4>
                                                </div>
                                            </a>
                                        </div>
                                        </c:if>
                                    
                                    
                                    
                                    <div class="more2">
                                        <a href="/info/${info.getNotice_id()}">
                                            <div class="more">
                                                <i class="bi bi-card-heading"></i>
                                                <h4>Ver más</h4>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                        
                        
                        
                        
                        

                    </div>
            </c:forEach>
        </div>
    </main>

    <script type="text/javascript" src="/js/index.js"></script>
    <script type="text/javascript" src="/js/navbar.js"></script>

</body>
</html>