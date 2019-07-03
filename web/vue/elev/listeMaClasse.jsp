<%-- 
    Document   : listeMaClasse
    Created on : 21 août 2018, 01:25:16
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ma Classe</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {
                ArrayList<Eleve> eleves = (ArrayList<Eleve>) request.getAttribute("eleves");
        %>
        <%@include file="../../eleve.jsp" %>
        <h1>Liste de Ma Classe :</h1>
        <table class="table table-bordered table-hover" id="tab1">
            <thead>
                <tr>
                    <th class="col" id="entete">Nom</th>
                    <th class="col" id="entete">Prénom</th>
                    <th class="col" id="entete">Adresse</th>
                    <th class="col" id="entete">Téléphone</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <c:forEach var="e" items="${eleves}">
                    <tr>
                        <c:if test="${e.login == login}">
                            <td>(Moi) ${e.prenom}</td>
                            <td>${e.nom}</td>
                            <td>${e.adresse}</td>
                            <c:if test="${!empty e.tel}">
                                <td>${e.tel}</td>
                            </c:if>
                            <c:if test="${empty e.tel}">
                                <td>Néant</td>
                            </c:if>

                        </c:if>
                        <c:if test="${e.login != login}">
                            <td>${e.prenom}</td>
                            <td>${e.nom}</td>
                            <td>${e.adresse}</td>
                            <c:if test="${!empty e.tel}">
                                <td>${e.tel}</td>
                            </c:if>
                            <c:if test="${empty e.tel}">
                                <td>Néant</td>
                            </c:if>
                        </c:if>

                    </tr>
                </c:forEach>
                </tr>
            </tbody>
        </table>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  

    </body>
</html>
