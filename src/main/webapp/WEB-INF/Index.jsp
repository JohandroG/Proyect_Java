<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tablero de Anuncios</title>

</head>
<body>

<nav>
    <a href="/publicar">Publicar Anuncio</a>
    |
    <a href="/registrarse">Registrarse</a> 
    | 
    <a href="/iniciar/sesion">Iniciar Sesion</a>
    |
    <a href="/hostcode">Cambiar codigo host</a>
    |
    <a href="/editar/admin">editar admin</a>
</nav>

    <c:if test="${userInfo != null}"  >
            <a href="/logout">Logout</a>
    </c:if>

    <!-- <img src="/images/2pefil.jpg"/> -->

    
        <c:forEach var="info" items="${noticesInfo}">
            <div>
                <img style="width: 100px;" src="<c:url value="${info.getImg()}"></c:url>" />
                <img style="width: 100px;" src="<c:url value="${info.getImg2()}"></c:url>" />
            </div>
        </c:forEach>
    


</body>
</html>