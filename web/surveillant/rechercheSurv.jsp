<<<<<<< HEAD:web/surveillant/rechercheSurv.jsp
<%-- 
    Document   : rechercheSurv
    Created on : 24 févr. 2019, 23:29:50
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {
        %>
        <%@include file="surveillant.jsp" %>
        <c:choose >
            <c:when test="${empty rechercheParElev}">
                <h1>${message}</h1>
            </c:when>
            <c:otherwise>
                <table class="table table-bordered" id="tab1">
                    <thead>
                        <tr>
                            <th scope="col" id="entete">Prénom</th>
                            <th scope="col" id="entete">Nom</th>
                            <th scope="col" id="entete">Date de Naissance</th>
                            <th scope="col" id="entete">Lieu de Naissance</th>
                            <th scope="col" id="entete">Téléphone</th>
                            <th scope="col" id="entete">Classe</th>
                        </tr>
                    </thead>

                    <c:forEach var="i" items="${rechercheParElev}">

                        <tbody>
                            <tr>
                                <td>${i.prenom}</td>
                                <td>${i.nom}</td>
                                <td>${i.dateNaissance}</td>
                                <td>${i.lieuNaissance}</td>
                                <td>${i.tel}</td>
                                <td>${i.classe}</td>
                            </tr>    
                        </tbody>
                    </c:forEach> 
                </table>   
            </c:otherwise>
        </c:choose>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
=======
<%-- 
    Document   : rechercheSurv
    Created on : 24 févr. 2019, 23:29:50
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {
        %>
        <%@include file="../../surveillant.jsp" %>
        <c:choose >
            <c:when test="${empty rechercheParElev}">
                <h1>${message}</h1>
            </c:when>
            <c:otherwise>
                <table class="table table-bordered" id="tab1">
                    <thead>
                        <tr>
                            <th scope="col" id="entete">Prénom</th>
                            <th scope="col" id="entete">Nom</th>
                            <th scope="col" id="entete">Date de Naissance</th>
                            <th scope="col" id="entete">Lieu de Naissance</th>
                            <th scope="col" id="entete">Téléphone</th>
                            <th scope="col" id="entete">Classe</th>
                        </tr>
                    </thead>

                    <c:forEach var="i" items="${rechercheParElev}">

                        <tbody>
                            <tr>
                                <td>${i.prenom}</td>
                                <td>${i.nom}</td>
                                <td>${i.dateNaissance}</td>
                                <td>${i.lieuNaissance}</td>
                                <td>${i.tel}</td>
                                <td>${i.classe}</td>
                            </tr>    
                        </tbody>
                    </c:forEach> 
                </table>   
            </c:otherwise>
        </c:choose>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403:build/web/vue/surveillant/rechercheSurv.jsp
