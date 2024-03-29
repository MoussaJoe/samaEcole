<%-- 
    Document   : listerNote
    Created on : 23 juil. 2018, 14:50:45
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link href="dist/css/mystyle.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste Note</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {
                
        %>
        <%@include file="acceuil.jsp" %>
        <div>
            <form method="post" action="ControleurProf">
                <table class="table table-bordered" id="tab1">
                    <thead>
                        <tr>
                            <th scope="col" id="entete">Prénom</th>
                            <th scope="col" id="entete">Nom</th>
                            <th scope="col" id="entete">Semestre</th>
                            <th scope="col" id="entete">Composition</th>
                            <th scope="col" id="entete">Modifier</th>
                        </tr>
                    </thead>
                    <c:forEach var="i" items="${eleve}">
                        <tbody>
                            <tr>
                                <td>${i.prenom}</td>
                                <td>${i.nom}</td>
                                <td>${i.semestre}</td>
                                <td>${i.composition}</td>
                                <td><a href="ControleurProf?action=modifierCompo&prenom=${i.prenom}&nom=${i.nom}&composition=${i.composition}&loginEleve=${i.matriculeEleve}&matiere=${i.matiere}&semestre=${i.semestre}&annee=${i.annee}&classe=${i.classe}&idEvaluation=${i.idEvaluation}"><img src="modifier.png" alt="Modifier" id="modifier"/></a></td>
                            </tr>    
                        </tbody>
                    </c:forEach> 
                </table>
            </form>  
        </div>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
