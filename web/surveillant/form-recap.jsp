<%-- 
    Document   : form-recap
    Created on : 15 sept. 2019, 02:40:46
    Author     : Moussa Joseph D Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Récapitulatif des moyennes</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {
        %>
        <%@include file="surveillant.jsp" %>
        <h1>Formulaire de Récapitulation des moyennes :</h1>
        <form method="POST" action="Surveillant">
            <input type="hidden" name="action" value="recapMoy"/>
            <table id="tab">
                <tr><th>Choisir la classe :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control">
                                <c:forEach var="p" items="${classes}">
                                    <option>${p.nomClasse}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr><th>Choisir le régime :</th>
                    <td>
                        <div class="form-group">
                            <select name="regime" class="form-control">
                                <c:forEach var="r" items="${regimes}">
                                    <option>${r}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr><th>Semestre : </th>
                    <td>
                        <div class="form-group">
                            <select name="semestre" class="form-control">
                                <option>1er_semestre</option>
                                <option>2eme_semestre</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr><th>Année-Scolaire : </th>
                    <td>
                        <div class="form-group">
                            <select name="annee" class="form-control">
                            <c:forEach var="a" items="${annees}">
                                <option>${a}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                </tr>
                <tr><th></th><td>
                        <button class="btn btn-success" type="submit">Valider</button>
                    </td></tr>
            </table>
        </form>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>          
    </body>    
</html>

