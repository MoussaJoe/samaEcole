<%-- 
    Document   : anEleve
    Created on : 22 nov. 2018, 17:21:52
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("login") != null) {

        %>
        <%@include file="menuParent.jsp" %>
         
        <h1>Eleve : ${el.prenom} ${el.nom}</h1>
        <h1>Choisir l'année Scolaire :</h1>
        <table class="table table-hover" id="tab1">
            <c:forEach var="a" items="${annees}">
                <tr>
                    <td><a class="btn btn-success" href="ControleurParent?annee=${a}&&loginEleve=${el.login}&&action=note">${a}</a></td> 
                </tr>
            </c:forEach>
        </table>

        
        <% } else {
        %>
        <jsp:forward page="acceuilParent.jsp"/>
        <% }%>
    </body>
</html>
