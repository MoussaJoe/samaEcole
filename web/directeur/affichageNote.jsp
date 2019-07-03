<%-- 
    Document   : affichageNote
    Created on : 12 août 2018, 01:47:41
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                 <%@include file="../../surveillant.jsp" %>
            </c:otherwise>
        </c:choose>
       
        <h1>Liste des Notes en ${nomMatiere} de la ${nomClasse} :</h1>

        <table id="tab1" class="table table-hover table-bordered" >
            <thead>
                <tr>
                    <th class="col" id="entete">Prénom</th>
                    <th class="col" id="entete">Nom</th>
                    <th class="col" id="entete">Date de Naissance</th>
                    <th class="col" id="entete">Lieu de Naissance</th>
                    <th class="col" id="entete">Devoir 1</th>
                    <th class="col" id="entete">Devoir 2</th>
                    <th class="col" id="entete">Composition</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${eleves}">
                    <tr>
                        <td>${e.prenom}</td>
                        <td>${e.nom}</td>                       
                        <td>${e.dateNaissance}</td>
                        <td>${e.lieuNaissance}</td>
                        <td><c:choose>
                                <c:when test ="${e.devoir1 == 0.0}">

                                </c:when>
                                <c:otherwise>
                                    ${e.devoir1}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:choose><c:when test ="${e.devoir2 == 0.0}">

                                </c:when>
                                <c:otherwise>
                                    ${e.devoir2}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose><c:when test ="${e.composition == 0.0}">

                                </c:when>
                                <c:otherwise>
                                    ${e.composition}
                                </c:otherwise>
                            </c:choose>
                        </td>

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
