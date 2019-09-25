<%-- 
    Document   : accueilCompta
    Created on : 8 mai 2019, 17:25:15
    Author     : Ouzy NDIAYE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Intendante | Acceuil</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavCompta.jsp" %> 
        <script>
            <c:if test="${! empty payementReussit}">
            alert("Payement effectué avec success");
            </c:if>
        </script>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
