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
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="surveillant.jsp" %>
        <h1>Liste des Professeurs :</h1>

        <table class="table table-bordered table-hover" id="tab1" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">Prénom</th>
                    <th id="entete" class="col">Adresse</th>
                    <th id="entete" class="col">Téléphone</th>
                        <%--  <th id="entete" class="col">Désactiver</th> --%>
                    <th id="entete" class="col">Modifier</th>
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
                        <td>
                            <a href="Surveillant?action=modifierProf&&loginProf=${p.personne.login}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                        </td>
                        <td>
                            <a href="Surveillant?action=detailProf&&loginProf=${p.personne.login}" class="btn btn-primary">Détails</a>
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
<script type="text/javascript">
    function confirmation() {
        var msg = confirm("tu veux supprimer cette ligne");
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
</script>