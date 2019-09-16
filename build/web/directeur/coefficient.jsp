<%-- 
    Document   : coefficient
    Created on : 9 sept. 2018, 19:12:34
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coefficients</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
        <h1>Définir les Coefficients des Matières de la : ${nomClasse} </h1>
        <form action="Directeur" method="POST">
            <input type="hidden" name="action" value="insertcoef" />
            <input type="hidden" name="nomcl" value="${nomClasse}"/>
            <table id="tab">

                <thead>
                    <tr>
                        <th>Matière :</th>
                        <th>Coefficient :</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="m" items="${nomMatiere}">
                        <tr>
                            <td>${m}</td>
                    <input type="hidden" name="nomMatiere" value=${m}/>
                    <td>
                        <div class="form-group">
                            <input type="number" name="coef" value="" class="form-control" required="" />
                        </div>
                    </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success">Enregistrer</button>
                    </td>
                </tr>
                </tbody>
            </table>

        </form>


        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
