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
        <h1 align="center">Veuillez renseigner la classe,la matière,le semestre et l'année concernée</h1>
        
        <script>
            <c:if test="${!empty message}">
            alert("Veuillez vérifier les informations saisies");
            </c:if>
        </script>
        <%-- <h2 align="center">${message}</h2> --%>
        <form action="Controleur" method="POST">
            <input type="hidden" name="action" value="demandeMatClasse" />
            <input type="hidden" name="login" value="${login}" />
            <div id="tab">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td>Classe</td>
                            <td> <select name="classe" class="form-control">
                                    <c:forEach var="i" items="${listClasse}">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Matière</td>

                            <td>
                                <div class="form-group">
                                    <select name="matiere" class="form-control">
                                        <c:forEach var="i" items="${listMatiere}">
                                            <option>${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td>Semestre</td>
                            <td>
                                <div class="form-group">
                                    <select name="semestre" class="form-control">
                                        <option>1er_semestre</option>
                                        <option>2eme_semestre</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Année Scolaire</td>
                            <td>
                                <div class="form-group"><select name="annee" class="form-control">
                                        <c:forEach var="i" items="${listAnnee}">
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
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
