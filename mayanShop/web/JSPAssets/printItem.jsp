<%-- 
    Document   : printItem
    Created on : 28-set-2017, 9.56.16
    Author     : root
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:out value="${param.n}"></c:out><br><%-- n=nome               --%>
<c:out value="${param.i}"></c:out><br><%-- i=immagine           --%>
<c:out value="${param.s}"></c:out><br><%-- s=stelline           --%>
<c:out value="${param.p}"></c:out><br><%-- p=prezzo             --%>
<c:out value="${param.v}"></c:out><br><%-- v=vendor             --%>
<c:out value="${param.a}"></c:out><br><%-- a=autore/produttore  --%>
<c:out value="${param.id}"></c:out><br><%-- id=id                 --%>

