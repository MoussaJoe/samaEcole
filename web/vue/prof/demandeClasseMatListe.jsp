<%-- 
    Document   : demandeMatClasse
    Created on : 17 juil. 2018, 09:37:24
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link href="dist/css/mystyle.css" rel="stylesheet"/>
        <title>Professeur</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="acceuil.jsp" %>
        <h1 align="center">Veuillez renseigner la classe et la matière concernées</h1>
        <script>
            <c:if test="${!empty message}">
            alert("Veuillez vérifier les informations saisies");
            </c:if>
        </script>
        <%--  <h2 align="center">${message}</h2> --%>
        <form action="ControleurProf" method="POST">
            <input type="hidden" name="action" value="demandeMatClasseListe" />
            <input type="hidden" name="login" value="${login}" />
            <div id="tab">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td>Classe</td>
                            <td> 
                                <div class="form-group">
                                    <select name="nomClasse" class="form-control">
                                        <c:forEach var="i" items="${listClasse2}">
                                            <option value="${i.nomClasse}///${i.regime}">${i.nomClasse} (${i.regime})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Matière</td>
                            <td>
                                <div class="form-group">
                                    <select name="matiere" class="form-control">
                                        <c:forEach var="i" items="${listMatiere2}">
                                            <option>${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Année Scolaire</td>
                            <td>
                                <div class="form-group">
                                    <select name="annee" class="form-control">
                                        <c:forEach var="i" items="${listAnnee2}">
                                            <option>${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><button class="btn btn-success">Valider</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
