<%-- 
    Document   : formNote
    Created on : 12 août 2018, 00:51:11
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulaire Note</title>
    </head>
    
        <%
            if (session.getAttribute("log") != null) {
               // String profils = (String) session.getAttribute("profils");
        %>
        <c:choose>
            <c:when test="${profils eq 'Directeur'}">
                 <%@include file="accueilDirecteur.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="../surveillant.jsp" %>
            </c:otherwise>
        </c:choose>
       
        <h1>Formulaire à renseigner pour afficher les Notes :</h1>

        <form action="ControleurDirecteur" method="Post">
            <table id="tab">
                <input type="hidden" name="action" value="voirNote">
                <tr>
                    <th>Classe :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control" required="">
                                <c:forEach var="p" items="${classes}">
                                    <option>${p.nomClasse}</option>       
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Régime :</th>
                    <td>
                        <div class="form-group">
                            <select name="regime" class="form-control" required="">
                                <c:forEach var="r" items="${regimes}">
                                    <option>${r}</option>       
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Matiere :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomMatiere" class="form-control" required="">
                                <c:forEach var="m" items="${matieres}">
                                    <option>${m}</option> 
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Semestre :</th>
                    <td>
                        <div class="form-group">
                            <select name="semestre" class="form-control" required="">                         
                                <option>1er_semestre</option> 
                                <option>2eme_semestre</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Annee :</th>
                    <td>
                        <div class="form-group">
                            <select name="annee" class="form-control" required="">
                                <c:forEach var="a" items="${annees}">
                                    <option>${a}</option> 
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <th>
                        <button class="btn btn-success">Valider</button>
                    </th>
                </tr>

            </table>
        </form>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 


    
</html>
