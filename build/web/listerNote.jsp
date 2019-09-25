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
            <form method="post" action="Controleur">
                <table class="table table-bordered" id="tab1">
                    <thead>
                        <tr>
                            <th scope="col" id="entete">Prénom</th>
                            <th scope="col" id="entete">Nom</th>
                            <th scope="col" id="entete">Semestre</th>
                            <th scope="col" id="entete">Devoir 1</th>
                            <th scope="col" id="entete">Devoir 2</th>
                            <th scope="col" id="entete">Composition</th>
                            <th scope="col" id="entete">Moyenne</th>
                            <th scope="col" id="entete">Action</th>
                        </tr>
                    </thead>
                    <c:forEach var="i" items="${eleve}">
                        <tbody>
                            <tr>
                                <td>${i.prenom}</td>
                                <td>${i.nom}</td>
                                <td>${i.semestre}</td>
                                <td>${i.devoir1}</td>
                                <td>${i.devoir2}</td>
                                <td>${i.composition}</td>
                                <td>${i.moyenne}</td>
                                <td><a href="Controleur?action=modifier&prenom=${i.prenom}&nom=${i.nom}&devoir1=${i.devoir1}&devoir2=${i.devoir2}&composition=${i.composition}&loginEleve=${i.matriculeEleve}&matiere=${i.matiere}&semestre=${i.semestre}&annee=${i.annee}&classe=${i.classe}"><img src="modifier.png" alt="Modifier" id="modifier"/></a></td>
                            </tr>    
                        </tbody>
                    </c:forEach> 
                </table>
            </form>  
        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
