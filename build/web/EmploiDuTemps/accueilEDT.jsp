<%-- 
    Document   : accueilEDT
    Created on : 30 mai 2019, 15:19:02
    Author     : Ouzy NDIAYE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Acceuil</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavEDT.jsp" %> 
        <!--////////////////////////////////////////////////////// -->
        <h1>Bienvenue</h1>
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
        <form method="POST" action="EDT">
              
            <input type="hidden" name="connect" value="creerEDT"/>
            <input type="hidden" name="connect" value="afficherEDTClasse"/>
            <div class="form-group">
                    <label>Sélectionner une classe</label>
                    <select class="form-control" name="nomClasse"> 
                        <c:forEach var="cl" items="${listClasse}">
                            <option value="${cl.nomClasse}///${cl.regime}">${cl.nomClasse} (${cl.regime})</option>                             
                        </c:forEach>                     
                    </select>                    
            </div>
            <div class="form-group">
                <button class="btn btn-success btn-block" type="submit">Valider</button>
            </div>
        </form>
            
        </div>
        <!--////////////////////////////////////////////////////// -->
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
