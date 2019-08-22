<%-- 
    Document   : directeur
    Created on : 10 juil. 2018, 19:11:08
    Author     : Moussa Joseph Sarr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">

    <head>
        <meta  charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="../../dist/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="../../dist/css/mystyle.css" />
        <link rel="stylesheet" type="text/css" href="../../Style/style1.css">
        <title>Directeur</title>
    </head>
    <body>
        <%--
        <%
            if (session.getAttribute("log") != null) {

%> --%>
        <div class="row navigation" block0>
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="nav">
                <div class="navbar-header">
                    <a class="navbar-brand" href="acceuil.html">SUNU ECOLE</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-exemple-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"></li>
                        <li id="btn_deconDir"><a href="Controleur?action=deconnection"><span class="glyphicon glyphicon-lock"></span>Se Deconnecter</a> </li>     
                    </ul>
                </div>
            </nav>
        </div>
        <div class="menu-verticale">
            <div class="menuDir">
                <ul>
                    <li><a href="ControleurDirecteur?action=ajoutEleve">Ajouter Eleve</a> </li>
                    <li><a href="ControleurDirecteur?action=ajoutProf">Ajouter Professeur</a> </li>
                    <li><a href="ControleurDirecteur?action=formNote">Consulter Notes</a> </li>
                    <li><a href="ControleurDirecteur">Bulletins</a> </li>
                    <li><a href="ControleurDirecteur?action=saveClasse">Enregistrer Classe</a> </li>
                    <li><a href="ControleurDirecteur?action=saveMatiere">Enregistrer Matiére</a> </li>
                    <li><a href="ControleurDirecteur?action=listerClasse">Lister Classe</a> </li>
                    <li><a href="ControleurDirecteur?action=listerProfs">Lister Professeur</a> </li>
                </ul>
            </div>
        </div>

        <script type="text/javascript" src="../../dist/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="../../dist/js/bootstrap.js"></script>
        <script type="text/javascript" src="../../dist/js/myscript.js"></script>

        <%-- <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
<% }%> --%> 
    </body>
</html>


