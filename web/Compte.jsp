<%-- 
    Document   : demandeMatClasse
    Created on : 17 juil. 2018, 09:37:24
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1 align='center'>${message}</h1>
        <form action="Controleur" method="POST">
            <input type="hidden" name="action" value="modifCompte" />
            <input type="hidden" name="idPersonne" value="${idPersonne}" />
            <div id="tab">
                <table class="table table-bordered">
                    <c:forEach var="i" items="${compte}">
                        <tbody>
                            <tr>
                                <td>Login</td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" name="login" value="${log}"  disabled="disabled" class="form-control"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Ancien Password</td>
                                <td>
                                    <div class="form-group">
                                        <input type="password" name="ancienMdp" value="" placeholder="Ancien mot de passe" required="" class="form-control"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Nouveau Password</td>
                                <td>
                                    <div class="form-group">
                                        <input type="password" name="nouveauMdp" value="" placeholder="Nouveau mot de passe" required="" class="form-control"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Confirmer Password</td>
                                <td>
                                    <div class="form-group">
                                        <input type="password" name="confirmerMdp" value="" placeholder="confirmer mot de passe" required="" class="form-control"/>
                                    </div>
                                </td>
                            </tr>
                    </c:forEach>
                    <tr>
                        <td><button class="btn btn-success">Valider</button></td>
                        <td><button type="reset" class="btn btn-success">Annuler</button></td>
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
