<%-- 
    Document   : classes
    Created on : 17 juin 2019, 12:37:21
    Author     : Moussa Joseph D Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Classes</title>
    </head>
    <body>
         <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
        <h1>Les Classes de l'établissement</h1>
        
        <table class="table table-bordered table-hover" id="tab1" >
            <thead>
                <tr>
                    <th id="entete" class="col">Nom Classe</th>
                    <th id="entete" class="col">Régime</th>
                    <th id="entete" class="col">Détails</th>
                </tr>
            </thead>
            <tbody>
                 <c:forEach var="c" items="${classes}">
                     <tr>
                        <td>${c.nomClasse}</td>
                        <td>${c.regime}</td>
                        <td><a href="Directeur?action=detailsClasse&&nomClasse=${c.nomClasse}&&regime=${c.regime}" class="btn btn-success">Détails</a></td>
                        
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
