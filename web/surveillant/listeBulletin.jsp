<%-- 
    Document   : listeBulletin
    Created on : 16 oct. 2018, 18:54:56
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="listeBulletin.css">
        <title>Bulletin</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="surveillant.jsp" %>
        <h1>Liste des Classes :</h1>
        <script>
            <c:if test="${!empty mess}">
            alert("Le bulletin a été créer avec succés");
            </c:if>
        </script>
        <table  id="tab1" class="table table-bordered table-hover">
            <tr>
                <th class="col" id="entete">Nom</th>
                <th class="col" id="entete">Prénom</th>
                <th class="col" id="entete">Bulletin</th>

            </tr>
            <!--<tr>-->
            <c:forEach var="e" items="${eleves}">
                <tr>
                    <td>${e.nom}</td>
                    <td>${e.prenom}</td>
                    <td>
                        <form action="Surveillant" method="Post">
                            <input type="hidden" name="login" value="${e.login}">
                            <input type="hidden" name="annee" value="${annee}">
                            <input type="hidden" name="semestre" value="${semestre}">
                            <input type="hidden" name="regime" value="${e.regime}">
                            <input type="hidden" name="action" value="creerBulletin">
                            <button class="btn btn-primary">Imprimer</button>
                        </form>
                    </td>



                    <c:if test="${(!empty mess) && (login eq e.login)}">
                        <td><a href="../bulletin/${e.nom}${e.prenom}.pdf">voir fichier</a></td>    
                    </c:if> 
                </tr>
            </c:forEach>
            <!--</tr>-->
        </table>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
