<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar</title>
</head>
<body>

    <nav>
        <a href="/">Back</a>
    </nav>
    
    <main>
        <form action="/update" method="POST" enctype="multipart/form-data"> 
            <p class="flashmjs">
                <c:out value="${errorMessage2}"></c:out>
            </p>

            <div class="left">

            <label for="topic">Tema:</label>
            <input type="text" value="${noticeInfo.getTitle()}" name="topic"/>
            <p class="flashmjs">
                <c:out value="${errorMessage3}"></c:out>
            </p>
            

            <label for="desc">Descripción:</label>
            <textarea type="text" name="desc">${noticeInfo.getDescription()}</textarea>
            <p class="flashmjs">
                <c:out value="${errorMessage4}"></c:out>
            </p>

            </div>
            
            <div class="right">
                <div class="images">
                <label>Incluye Imagen</label>
                <input type="file" value="${noticeInfo.getImg()}"  id="newImage1" name="newImage1" accept="image/png, image/jpeg"/>
                <p class="flashmjs">
                    <c:out value="${errorMessage1}"></c:out>
                </p>

                <label>Imagen Extra</label>
                <input type="file" id="newImage2" name="newImage2" accept="image/png, image/jpeg"/>
                </div>

                <div class="link">
                    <label for="link">Enlace:</label>
                    <input type="text" value="${noticeInfo.getLink()}" name="link"/>
                </div>
            </div>
            <div class="button">
                <input type="checkbox" name="important">
                <label for="important">Importante</label>
            </div>

            <input type="hidden" value="${noticeInfo.getImg2()}" name="img2"/>
            <input type="hidden" value="${noticeInfo.getImg()}" name="img1"/>
            <input type="hidden" value="${noticeInfo.getNotice_id()}" name="notice_id"/>
            <button type="submit">Publicar</button>
        </form>

        <form method="POST" action="/delete">
            <input type="hidden" value="${noticeInfo.getNotice_id()}" name="notice_id"/>
            <input type="hidden" value="${noticeInfo.getImg2()}" name="img2"/>
            <input type="hidden" value="${noticeInfo.getImg()}" name="img1"/>
            <button type="submit">Eliminar</button>
        </form>
        
    </main>

</body>
</html>