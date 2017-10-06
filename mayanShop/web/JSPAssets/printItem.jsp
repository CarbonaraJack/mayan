<%-- 
    Document   : printItem
    Created on : 28-set-2017, 9.56.16
    Author     : root
--%>
<%-- 
    Parametri GET:
i=immagine
n=nome
a=autore/produttore
p=prezzo
s=stelline
id=id
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="itemBox">
    <div class="itemImageContainer">
        <a href="../item.jsp?id=${param.id}">
            <img class="itemImage" src="img/${param.i}"/>
        </a>
    </div>
    <div class="itemName">
        <a href="../item.jsp?id=${param.id}">
            <c:out value="${param.n}"></c:out>
        </a>
    </div>
    <div class="itemAuthor"><c:out value="${param.a}"></c:out></div>
    <div class="itemStars"><c:out value="${param.s}"></c:out></div>
    <div class="itemPrice"><c:out value="${param.p}"></c:out></div>
    
</div>