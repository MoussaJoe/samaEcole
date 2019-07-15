<%-- 
    Document   : mensualite
    Created on : 20 mai 2019, 21:43:10
    Author     : Ouzy NDIAYE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta  charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="dist/css/mystyle.css" />
        <link rel="stylesheet" type="text/css" href="Style/style1.css">
        <title>Acceuil</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavCompta.jsp" %> 
        <!--///////////Liste Classe Privee/////////////// -->
        <c:if test="${(! empty classePrivee) && (empty eleve)}">
            <script>
            <c:if test="${! empty payementReussit}">
            alert("Payement effectué avec success");
            </c:if>
                </script>
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
                    <table id="tab2" class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th scope="col" style="text-align: center">Nom classe</th>
                                <th scope="col" style="text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach var="cl" items="${classePrivee}">
                                    <td>
                                        ${cl} 
                                    </td>
                            
                            <td>
                                <a href="Comptable?connect=choixClasse&nomClasse=${cl}">
                                <button class="btn btn-success btn-block">Voir classe</button>
                                </a>
                            </td>
                        </c:forEach>                            
                        </tr>
                        </tbody>

                    </table>
            </div>
        </c:if>
        <!--///////////Fin Liste Classe Privee/////////////// -->
        <!--///////////Liste des eleves de la classe X/////////////// -->
        <c:if test="${! empty eleve}">
            <div class="col-lg-3"></div>
            <form method="POST" action="Comptable">
                <input type="hidden" name="connect" value="payerMensuel"/>
                <div class="col-lg-9">
                    <h2 style="text-align: center">Liste des élèves de la classe de ${nomClasse}</h2>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th scope="col" style="text-align: center">Prénom</th>
                                <th scope="col" style="text-align: center">Nom</th>
                                <th scope="col" style="text-align: center">Date de Naissance</th>
                                <th scope="col" style="text-align: center">Lieu de Naissance</th>
                                <th scope="col" style="text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="cl" items="${eleve}">
                                <tr>
                                    <td>${cl.prenom}</td>                                    
                                    <td>${cl.nom}</td>
                                    <td>${cl.dateNaissance}</td>
                                    <td>${cl.lieuNaissance}</td>
                            <input type="hidden" name="login" value="${cl.login}"/>
                            <input type="hidden" name="nomClasse" value="${nomClasse}"/>                            
                            <td><button class="btn btn-success btn-block" type="submit">Voir élève</button></td>
                            </tr>
                        </c:forEach>                            

                        </tbody>

                    </table>
                </div>
            </form>
        </c:if>

        <!--///////////Fin Liste des eleves de la classe X/////////////// -->
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>                                                    
        <% }%>
    </body>
</html>
