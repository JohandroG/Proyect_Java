<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Publica Anuncio</title>
</head>
<body>

    <nav>
        <a href="/">Back</a>
    </nav>
    
    <main>
        <form action="/publish" method="POST" enctype="multipart/form-data"> 
            <div class="left">

            <label for="topic">Tema:</label>
            <input type="text" name="topic"/>

            <label for="desc">Descripción:</label>
            <textarea type="text" name="desc"></textarea>

            </div>
            
            <div class="right">
                <div class="images">
                <label>Incluye Imagen</label>
                <input type="file" id="newImage1" name="newImage1" accept="image/png, image/jpeg"/>
                
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

</body>
</html>