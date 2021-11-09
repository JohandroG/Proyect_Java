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
    <a href="/cambiar/codigo">Cambiar codigo host</a>
    |
    <a href="/editar/admin">editar admin</a>
</nav>

    <c:if test="${userInfo != null}"  >
            <a href="/logout">Logout</a>
    </c:if>

    <!-- <img src="/images/2pefil.jpg"/> -->

    <main>
        <div class="search">
            <form method="POST" action="/search">
                <label for="search">Buscar</label>
                <input type="text" name="search"/>

                <label for="important">Important</label>
                <input type="checkbox" name="important"/>
                <button type="submit">Search</button>
            </form>
        </div>

        <div>
            <c:forEach var="info" items="${noticesInfo}">
                    <div style="margin: 40px; border: 2px solid black;">
                        
                        <c:if test="${info.getImportance() != 'off' }"  >
                            <p>Importante</p>
                        </c:if>
        
                        <c:if test="${info.getLink() != '' }"  >
                            <a href="${info.getLink()}">Link</a>
                        </c:if>
        
                        
                        <c:out value = "${info.getTitle()}" ></c:out>
                        <c:out value = "${info.getDescription()}" ></c:out>
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


</body>
</html>