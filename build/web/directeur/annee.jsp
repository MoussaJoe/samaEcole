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
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>

        <script>
            <c:if test="${!empty msg}">
            alert("Veuillez revoir l'année Scolaire saisi!");
            </c:if>
        </script>
        <script>
            <c:if test="${!empty er}">
             alert("Veuillez Changer d'année; celle-ci a été déjà ajoutée!");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <h1 style="margin-left: 80px;width: 100%">Année Scolaire</h1>
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th id="entete">Année Scolaire</th>
                            </tr>
                            <c:forEach var="p" items="${annees}">
                                <tr>
                                    <td>${p}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="Post">
                            <input type="hidden" name="action" value="ajoutAnnee">

                            <div class="form-group">
                                <label>Ajouter une nouvelle Année Scolaire</label>
                                <input type="text" name="nv-annee" placeholder="format 2018-2019" maxlength="9" onkeypress=" return event.charCode >= 48 && event.charCode <= 57 || event.charCode === 45" class="form-control" required="">
                            </div>

                            <div class="form-group">
                                <button class="btn btn-success btn-block " type="submit">Ajouter</button>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
