<%-- 
    Document   : listeSurv
    Created on : 15 mars 2019, 15:41:24
    Author     : ibrah
--%>
<%@page import="model.Professeur"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des Professeurs de l'etablissement</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
                <%@include file="accueilDirecteur.jsp" %>

        <h1>Liste des Surveillants:</h1>

        <table class="table table-bordered table-hover" id="tab1" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">Prénom</th>
                    <th id="entete" class="col">Adresse</th>
                    <th id="entete" class="col">Téléphone</th>
                    <%-- <th id="entete" class="col">Désactiver</th> --%>
                    <th id="entete" class="col">Modifier</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${surv}">
                    <tr>
                        <td>${p.nom}</td>
                        <td>${p.prenom}</td>
                        <td>${p.adresse}</td>
                        <td>${p.telephone}</td>
                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                        <td>
                            <a href="Directeur?action=modifierSurv&&loginSurv=${p.login}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
