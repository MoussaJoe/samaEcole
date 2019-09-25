<<<<<<< HEAD
<%-- 
    Document   : listeProfs
    Created on : 20 juil. 2018, 03:10:38
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Professeur"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${profils} | Nos Professeurs</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Liste des professeurs de l'établissement </p>

                    <table class="table table-bordered table-hover table-responsive" >
                        <thead>
                            <tr>
                                <th id="entete" class="col">Nom</th>
                                <th id="entete" class="col">Prénom</th>
                                <th id="entete" class="col">Adresse</th>
                                <th id="entete" class="col">Téléphone</th>
                                    <%--  <th id="entete" class="col">Désactiver</th> --%>
                                    <c:if test="${profils eq 'surveillant'}">
                                    <th id="entete" class="col">Modifier</th>
                                    </c:if>
                                <th id="entete" class="col">Détails</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${profs}">
                                <tr>
                                    <td>${p.personne.nom}</td>
                                    <td>${p.personne.prenom}</td>
                                    <td>${p.personne.adresse}</td>
                                    <td>${p.personne.tel}</td>
                                    <%-- <td><button><a href="ControleurDirecteur?action=desactiverProf&&idProf=${p.personne.idPersonne}">Désactiver</a></button></td> --%>
                                    <c:if test="${profils eq 'surveillant'}">
                                        <td>
                                            <a href="Directeur?action=modifierProf&&idProf=${p.personne.idPersonne}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                        </td>
                                    </c:if>
                                    <td>
                                        <a href="Directeur?action=detailProf&&loginProf=${p.personne.login}" class="btn btn-primary">Détails</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
<script type="text/javascript">
    function confirmation() {
        var msg = confirm("tu veux supprimer cette ligne");
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
=======
<%-- 
    Document   : listeProfs
    Created on : 20 juil. 2018, 03:10:38
    Author     : Moussa Joseph Sarr
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
        <c:choose>
            <c:when test="${profils eq 'Directeur'}">
                <%@include file="accueilDirecteur.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="../surveillant.jsp" %>
            </c:otherwise>
        </c:choose>

        <h1>Liste des Professeurs :</h1>

        <table class="table table-bordered table-hover" id="tab1" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">PrÃ©nom</th>
                    <th id="entete" class="col">Adresse</th>
                    <th id="entete" class="col">TÃ©lÃ©phone</th>
                    <%--  <th id="entete" class="col">DÃ©sactiver</th> --%>
                        <c:if test="${profils eq 'surveillant'}">
                        <th id="entete" class="col">Modifier</th>
                        </c:if>
                    <th id="entete" class="col">DÃ©tails</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${profs}">
                    <tr>
                        <td>${p.personne.nom}</td>
                        <td>${p.personne.prenom}</td>
                        <td>${p.personne.adresse}</td>
                        <td>${p.personne.tel}</td>
                        <%-- <td><button><a href="ControleurDirecteur?action=desactiverProf&&idProf=${p.personne.idPersonne}">DÃ©sactiver</a></button></td> --%>
                        <c:if test="${profils eq 'surveillant'}">
                            <td>
                                <a href="Directeur?action=modifierProf&&idProf=${p.personne.idPersonne}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                            </td>
                        </c:if>
                        <td>
                            <a href="Directeur?action=detailProf&&loginProf=${p.personne.login}">DÃ©tails</a>
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
<script type="text/javascript">
    function confirmation() {
        var msg = confirm("tu veux supprimer cette ligne");
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
</script>