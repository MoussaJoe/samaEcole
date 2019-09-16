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
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>

        <h1>Directeur des études:</h1>
        <table class="table table-bordered table-hover" id="tabAdmin" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">Prénom</th>
                    <th id="entete" class="col">Adresse</th>
                    <th id="entete" class="col">Téléphone</th>
                        <%-- <th id="entete" class="col">Désactiver</th> --%>
                    <th id="entete" class="col">Modifier</th>
                    <th id="entete" class="col">Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${pers1}">
                    <tr>
                        <td>${p.nom}</td>
                        <td>${p.prenom}</td>
                        <td>${p.adresse}</td>
                        <td>${p.tel}</td>
                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                        <td>
                            <a href="Directeur?action=modifierAdmin&&login=${p.login}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                        </td>
                        <td>
                            <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <h1>Surveillant Général:</h1>
        <table class="table table-bordered table-hover" id="tabAdmin" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">Prénom</th>
                    <th id="entete" class="col">Adresse</th>
                    <th id="entete" class="col">Téléphone</th>
                        <%-- <th id="entete" class="col">Désactiver</th> --%>
                    <th id="entete" class="col">Modifier</th>
                    <th id="entete" class="col">Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${pers3}">
                    <tr>
                        <td>${p.nom}</td>
                        <td>${p.prenom}</td>
                        <td>${p.adresse}</td>
                        <td>${p.tel}</td>
                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                        <td>
                            <a href="Directeur?action=modifierAdmin&&login=${p.login}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                        </td>
                        <td>
                            <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h1 style="margin-left: 700px;">Surveillant:</h1>
        <table class="table table-bordered table-hover" id="tabAdmin" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">Prénom</th>
                    <th id="entete" class="col">Adresse</th>
                    <th id="entete" class="col">Téléphone</th>
                        <%-- <th id="entete" class="col">Désactiver</th> --%>
                    <th id="entete" class="col">Modifier</th>
                    <th id="entete" class="col">Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${pers2}">
                    <tr>
                        <td>${p.nom}</td>
                        <td>${p.prenom}</td>
                        <td>${p.adresse}</td>
                        <td>${p.tel}</td>
                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                        <td>
                            <a href="Directeur?action=modifierAdmin&&login=${p.login}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                        </td>
                        <td>
                            <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h1 style="margin-left: 700px;">Comptable:</h1>
        <table class="table table-bordered table-hover" id="tabAdmin" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">Prénom</th>
                    <th id="entete" class="col">Adresse</th>
                    <th id="entete" class="col">Téléphone</th>
                        <%-- <th id="entete" class="col">Désactiver</th> --%>
                    <th id="entete" class="col">Modifier</th>
                    <th id="entete" class="col">Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${pers4}">
                    <tr>
                        <td>${p.nom}</td>
                        <td>${p.prenom}</td>
                        <td>${p.adresse}</td>
                        <td>${p.tel}</td>
                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                        <td>
                            <a href="Directeur?action=modifierAdmin&&login=${p.login}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                        </td>
                        <td>
                            <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
