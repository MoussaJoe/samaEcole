<%-- 
    Document   : listeAbsence
    Created on : 8 sept. 2019, 15:35:30
    Author     : Moussa Joseph D Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="surveillant.jsp" %>
        <h1>Liste des absences :</h1>
        <table class="table table-bordered table-hover" id="tab1" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom</th>
                    <th id="entete" class="col">Prénom</th>
                    <th id="entete" class="col">Total absences</th>
                    <th id="entete" class="col">Total retards</th>
                    <th id="entete" class="col">Détails</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="abs" items="${absences}">
                    <tr style="text-align: center;">
                        <td>${abs.nom}</td>
                        <td>${abs.prenom}</td>
                        <td>${abs.totalAbsence}h</td>
                        <td>${abs.totalRetard}</td>
                        <td><a href="Surveillant?action=detailsAbsence&&login=${abs.login}" class="btn btn-primary">Détails</a></td>
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
