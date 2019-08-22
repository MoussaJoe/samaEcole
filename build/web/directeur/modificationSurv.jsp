<%-- 
    Document   : modificationSurv
    Created on : 15 mars 2019, 15:47:45
    Author     : ibrah
--%>
<%@page import="model.Professeur"%>
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
        <%@include file="accueilDirecteur.jsp" %>

        <h1>Modification Surveillant :</h1>
            <form action="Directeur" method="POST">

                <table id="tab">
                    <input type="hidden" name="action" value="valideModSurv" />
                    <input type="hidden" name="loginSurv" value="${loginSurv}" />          
                    <tr>
                        <th>Nom :</th>
                        <td>
                            <div class="form-group">
                                <input type="text" name="nom" value="${nom}" class="form-control"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>Prenom :</th>
                        <td>
                            <div class="form-group">
                                <input type="text" name="prenom" value="${prenom}" class="form-control"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>Adresse :</th>
                        <td>
                            <div class="form-group">
                                <input type="text" name="adresse" value="${adresse}" class="form-control"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>Telephone :</th>
                        <td>
                            <div class="form-group">
                                <input type="text" name="tel" value="${telephone}" class="form-control"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <button class="btn btn-success" type="submit">Valider</button>
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

