<%-- 
    Document   : accueilDirecteur
    Created on : 10 oct. 2018, 18:17:05
    Author     : ibrah
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">

    <head>
        <meta  charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="dist/css/mystyle.css" />
        <link rel="stylesheet" type="text/css" href="Style/style1.css">
        <title>Surveillant</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %> 
        <div class="row navigation" block0>
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="nav">
                <div class="navbar-header">
                    <a class=" sunu_ecole" href="accueilSurv.jsp">SUNU ECOLE</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-exemple-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right"> 
                        <li id="nom">${nom}&nbsp;${prenom}</li>
                        <form action="ControleurDirecteur" method="post" class="navbar-form navbar-right"/>
                    <input type="hidden" name="action" value="rechercherParSurv"/>
                    <div class="searchBox">
                        <input type="search" name="recherche" class="form-control recherche" placeholder="Rechercher Nom"/>
                        <button class="btn" type="submit"><img src="vue/search.png" id="btn_search"/></button>
                    </div>
                    </form>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="menu-verticale">
            <div class="menuDir">
                <ul>
                    <li><a href="ControleurDirecteur?action=ajoutEleve"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;&nbsp;Ajouter Eleve</a> </li>
                    <li><a href="ControleurDirecteur?action=ajoutProf"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;&nbsp;Ajouter Professeur</a> </li>
                    <li><a href="ControleurDirecteur?action=formNote"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Consulter Notes</a> </li>
                    <li><a href="ControleurDirecteur?action=bulletin"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;&nbsp;Bulletins</a> </li>
                    <li><a href="ControleurDirecteur?action=formAffClass"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Lister Classe</a> </li>
                    <li><a href="ControleurDirecteur?action=listerProfs"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Lister Professeur</a> </li>
                    <li><a href="ControleurDirecteur?action=compte"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;Compte</a> </li>
                    <li><a href="Controleur?action=deconnection"><span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;&nbsp;Se Déconnecter</a> </li> 
                </ul>
            </div>
        </div>
        <div class="corps1">

        </div>

        <script type="text/javascript" src="dist/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="dist/js/bootstrap.js"></script>
        <script type="text/javascript" src="dist/js/myscript.js"></script>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>