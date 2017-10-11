<%-- 
    Document   : login
    Created on : 11-ott-2017, 11.47.32
    Author     : jack
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="loginCard">
    <div> Login </div>
    <form action="userAuth.jsp" method="POST">
        <div>
            <input type="email" placeholder="Email" name="mail" >
        </div>
        <div>
            <input type="password" placeholder="Password" name="password">
        </div>
        <div>
            <input type="checkbox" name="token"> Tienimi loggato
        </div>
        <div>
            <input type="submit" value="Log in">
        </div>
    </form>

</div>