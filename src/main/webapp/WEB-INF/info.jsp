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
<title>Información</title>
<link rel="stylesheet" href="/css/normalize.css"/>
<link rel="stylesheet" href="/css/info.css"/>
<!-- imports y CSS personal -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body id="body">


    
    <div class="background">
        <div class="back">
            <a href="/">Atras</a>
        </div>
    </div>

    <div class="info">
        <c:if test="${noticesInfo.getImg() != null }"  >
            <div class="carrusel">
                <ul class="carrusel-fotos">
                <li id="foto1"><img id="foto" src="<c:url value="${noticesInfo.getImg()}"></c:url>" alt=""></li> 
                <c:if test="${noticesInfo.getImg2() != null }"  >
                <li id="foto2"><img id="foto" src="<c:url value="${noticesInfo.getImg2()}"></c:url>" alt=""></li>
                <!-- <li id="foto1"><img id="foto" src="https://i.ibb.co/JR95j0T/yoimiya.png" alt=""></li> -->
                <!-- <li id="foto2"><img id="foto" src="https://i.ibb.co/hXyJXqG/PRACTICA-LABORATORIO-MANTENIMIENTO-Johandro-Gonzalez-Arrieta.jpg" alt=""></li> -->
                </c:if>
                </ul>
                <ul class="carrusel-menu">
                    <c:if test="${noticesInfo.getImg2() != null }"  >
                <li><a style="text-decoration: none;" id="fotos2" href="#foto1"><h4 id="num">1</h4></a></li>
                <li><a style="text-decoration: none;" id="fotos2" href="#foto2"><h4 id="num">2</h4></a></li>
                    </c:if>
                </ul>
            </div>
        </c:if>


<div class="right">
    
    <div class="titleanddesc">
                    <div class="title">
                        <h1>
                            <c:out value="${noticesInfo.getTitle()}"></c:out>
                        </h1>
                    </div>
        
                    <div class="descr">
                        <p>
                            <c:out value="${noticesInfo.getDescription()}"></c:out>
                        </p>
                    </div>
    </div>

    <div class="options">
                    <c:if test="${noticesInfo.getLink() != '' }"  >
                            <a href="${noticesInfo.getLink()}" class="link">
                                    <i class="bi bi-link-45deg"></i>
                                    <h4>Enlace</h4>
                            </a>
                    </c:if>
                    <c:if test="${userInfo != null}"  >
                        <a href="/editar/${noticesInfo.getNotice_id()}" class="edit">
                                <i class="bi bi-pencil-square"></i>
                                <h4>Editar</h4>
                        </a>
                    </c:if>
    </div>
    
</div>
        <div class="space"></div>  

    </div>

    <script type="text/javascript" src="/js/info.js"></script>
</body>
</html>