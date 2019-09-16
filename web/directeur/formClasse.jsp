<%-- 
    Document   : formClasse
    Created on : 9 sept. 2018, 16:38:57
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrer Classe</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
        <h1>Enregistrer Classe :</h1>
        <form action="Directeur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="validerClasse" />
                <tr>
                    <th>Nom Classe :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nomClasse" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Régime :</th>
                    <td>
                        <div class="form-group">
                            <select name="regime" class="form-control" required="" required=""/>
                            <option>Privée</option>
                            <option>Public</option>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th> Ses Matières :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomMatiere" multiple="multiple" class="form-control" required="">
                                <c:forEach var="m" items="${matieres}">
                                    <option>${m}</option> 
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>

                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success">Enregistrer</button>
                    </td>
                </tr>

            </table>

        </form>

        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
