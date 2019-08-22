<%-- 
    Document   : listerNote
    Created on : 23 juil. 2018, 14:50:45
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link href="dist/css/mystyle.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Parent</title>
    </head>
    <body>
        <%
            if (session.getAttribute("login") != null) {

        %>
        <%@include file="menuParent.jsp" %>

        <c:choose >
            <c:when test="${empty eleve4}">
                <h1>${message}</h1>
            </c:when>
            <c:otherwise>
                <h1 id="parent"> ${el.prenom}  ${el.nom}  ${el.nomClasse}  ${annee} </h1>
                <form method="post" action="Controleur">
                    <table class="table table-bordered" id="tab1">
                        <thead>
                            <tr>
                                <th scope="col" id="entete">Matière</th>
                                <th scope="col" id="entete">Semestre</th>
                                <th scope="col" id="entete">Devoir 1</th>
                                <th scope="col" id="entete">Devoir 2</th>
                                <th scope="col" id="entete">Composition</th>
                                <th scope="col" id="entete">Moyenne</th>
                            </tr>
                        </thead>

                        <c:forEach var="i" items="${eleve4}">

                            <tbody>
                                <tr>
                                    <td>${i.matiere}</td>
                                    <td>${i.semestre}</td>
                                    <td>${i.devoir1}</td>
                                    <td>${i.devoir2}</td>
                                    <td>${i.composition}</td>
                                    <td>${i.moyenne}</td>
                                </tr>    
                            </tbody>
                        </c:forEach> 
                    </table>
                </form>    
            </c:otherwise>
        </c:choose>

        <% } else {
        %>
        <jsp:forward page="acceuilParent.jsp"/>
        <% }%>
    </body>
</html>
