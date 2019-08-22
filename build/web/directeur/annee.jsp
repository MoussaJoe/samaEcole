<%-- 
    Document   : annee
    Created on : 15 nov. 2018, 22:51:43
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Année-Scolaire</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
        <h1>Année Scolaire :</h1>
        <table class="table table-bordered table-hover" id="tab1" >
            <tr><th id="entete" class="col">Année Scolaire</th></tr>
                    <c:forEach var="p" items="${annees}">
                <tr><td>${p}</td></tr>
            </c:forEach>
        </table>

        <form action="Directeur" method="Post">
            <input type="hidden" name="action" value="ajoutAnnee">
            <table id="tab">
                <tr><th>
                        Ajouter une nouvelle Année Scolaire : </th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nv-annee" value="" placeholder="format 2018-2019" maxlength="9" onkeypress=" return event.charCode >= 48 && event.charCode <=57 || event.charCode === 45" class="form-control" required="">
                        </div></td>
                </tr>
                <tr><th>
                    </th>
                    <td><button class="btn btn-success">Valider</button></td>
                </tr>
            </table>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
