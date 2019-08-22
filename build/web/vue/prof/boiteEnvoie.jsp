<%-- 
    Document   : reclamation
    Created on : 29 sept. 2018, 11:44:46
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link href="dist/css/mystyle.css" rel="stylesheet"/>
        <link href="Style/style1.css" rel="stylesheet"/>
        <title>Réclamation</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="acceuil.jsp" %>
        <div>
            <h1>${message}</h1>
                <c:forEach var="r" items="${listReclation}">
                    <table  border="3" bordercolor="#F1C40F" id="tab">
                        <div class="mes"><h6><span class="glyphicon glyphicon-book"></span>&nbsp;${r.date}</h6></div>
                        <tbody>
                            <tr>
                                <td class="reclamation">
                                    <h3 align="center">${r.enTete}</h3>
                                    <h5>${r.message}</h5>
                                    <p align="center"><a href="ControleurProf?action=repondre&loginEleve=${r.loginEleve}&loginProf=${log}&idReclamation=${r.idReclamation}">Répondre</a>
                                    </p>
                                </td> 
                            </tr>
                        </tbody>
                    </table>
                </c:forEach>
        </div>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
