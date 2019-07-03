<%-- 
    Document   : ajouterCours
    Created on : 30 mai 2019, 17:41:11
    Author     : Ouzy NDIAYE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <div class="container">
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <p class="edp_Classe">${nomClasse}</p>
                    <form method="POST" action="EDT">
                        <input type="hidden" name="connect" value="valider-ajout"/>
                        <input type="hidden" name="heure" value="${heure}"/>
                        <input type="hidden" name="jour" value="${jour}"/>
                        <input type="hidden" name="regime" value="${regime}"/>
                        <input type="hidden" name="nomClasse" value="${nomClasse}"/>
                        <div class="form-group">
                            <label>Matière</label>
                            <select class="form-control" name="nomMatiere"> 
                                <c:forEach var="mt" items="${listMatiere}">
                                    <option value="${mt}">${mt}</option>                            
                                </c:forEach>                        
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Sélectionner un professeur</label>
                            <select class="form-control" name="nomProf"> 
                                <c:forEach var="cl" items="${listerProf}">
                                    <option value="${cl.nom}///${cl.login}">
                                        ${cl.nom}
                                    </option>                            
                                </c:forEach>                        
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label>Sélectionner une salle de cours</label>
                            <select class="form-control" name="nomSalle"> 
                                <c:forEach var="cl" items="${listSalle}">
                                    <option value="${cl}">
                                        ${cl}
                                    </option>                            
                                </c:forEach>                        
                            </select>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success btn-block" type="submit">Ajouter</button>
                        </div>
                    </form>

                </div>
            </div>
            <!--////////////////////////////////////////////////////// -->
            <% } else {
            %>
            <jsp:forward page="vue/SeConnecter.jsp"/>
            <% }%>
    </body>
</html>
