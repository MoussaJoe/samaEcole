<%-- 
    Document   : barreNavCompta
    Created on : 8 mai 2019, 17:31:26
    Author     : Ouzy NDIAYE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta  charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="dist/css/mystyle.css" />
        <link rel="stylesheet" type="text/css" href="Style/style1.css">
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <div class="row navigation" block0>
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="nav">
                <div class="navbar-header">
                    <a class="sunu_ecole" href="acceuilCompta.jsp">SUNU ECOLE</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-exemple-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">                     
                        <li id="nom">${nom}&nbsp;${prenom}</li>
                        <form action="#" method="post" class="navbar-form navbar-right"/>
                        <input type="hidden" name="connect" value="rechercher"/>
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
                    <li><a href="Comptable?connect=inscrireEleve"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;&nbsp;Inscrire un éleve</a> </li>
                    <li><a href="Comptable?connect=reinscrireEleve"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;&nbsp;Réinscrire un éleve</a> </li>
                    <li><a href="Comptable?connect=mensualite"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;Mensualité</a> </li>
                    <li><a href="#"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;&nbsp;Message</a></li>
                    <li><a href="EDT?connect=accueilEDT"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;Emploi du Temps</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;Compte</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;&nbsp;Se Déconnecter</a> </li>
                </ul>
            </div>
        </div>

        <script type="text/javascript" src="dist/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="dist/js/bootstrap.js"></script>
        <script type="text/javascript" src="dist/js/myscript.js"></script>

        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>

