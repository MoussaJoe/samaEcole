<%-- 
    Document   : formAffClasse
    Created on : 21 févr. 2019, 14:19:26
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulaire Aff. Classe</title>
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

        <h1>Formulaire d'affichage de Classe :</h1>

        <form action="Directeur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="listerClasse" />
                <tr>
                    <td>Nom Classe :</td>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control" required="">
                                <c:forEach var="p" items="${classes}">
                                    <c:choose>
                                        <c:when test="${p.nomClasse eq nomCl}">
                                            <option selected >${p.nomClasse}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>${p.nomClasse}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                &nbsp;&nbsp;
                <tr>
                    <td>Régime :</td> 
                    <td>
                        <div class="form-group">
                            <select name="regime" class="form-control" required="">
                                <option>Privee</option>
                                <option>Public</option>
                            </select>
                        </div>
                    </td>
                </tr>
                &nbsp;&nbsp;
                <tr>
                    <td>Année Scolaire :</td>
                    <td>
                        <div class="form-group">
                            <select name="year" class="form-control" required="">
                                <c:forEach var="a" items="${annees}">
                                    <c:choose>
                                        <c:when test="${a eq an}">
                                            <option selected >${a}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>${a}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                &nbsp;&nbsp;
                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-success">Valider</button>
                    </td>
                </tr>

            </table>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
