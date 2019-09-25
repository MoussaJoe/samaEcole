<<<<<<< HEAD
<%-- 
    Document   : detailProf
    Created on : 11 oct. 2018, 18:58:25
    Author     : Moussa Joseph Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Infos Professeurs</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Informations Professeur</p>
                    <table class="table table-bordered table-hover table-responsive">
                        <thead>
                            <tr>
                                <th id="entete">Classe</th>
                                <th id="entete">Régime</th>
                                <th id="entete">Matière</th>
                                <th id="entete">Année Scolaire</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="d" items="${detailProf}">
                                <tr>

                                    <td>${d.nomClasse}</td>
                                    <td>${d.regime}</td>
                                    <td>${d.nomMatiere}</td>
                                    <td>${d.annee}</td>
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
=======
<%-- 
    Document   : detailProf
    Created on : 11 oct. 2018, 18:58:25
    Author     : Moussa Joseph Sarr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Le Professeur</title>
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
       
        <h1>Professeur :</h1>
        <table  id="tab1" class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th id="entete">Classe</th>
                    <th id="entete">MatiÃ¨re</th>
                    <th id="entete">AnnÃ©e Scolaire</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${detailProf}">
                    <tr>

                        <td>${d.nomClasse}</td>
                        <td>${d.nomMatiere}</td>
                        <td>${d.annee}</td>
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
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
