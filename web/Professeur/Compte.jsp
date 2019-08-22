<%-- 
    Document   : demandeMatClasse
    Created on : 17 juil. 2018, 09:37:24
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Professeur | Mon compte</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavProf.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12"> 
                    <h1 align='center'>${message}</h1>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="ControleurProf" method="POST">
                            <input type="hidden" name="action" value="modifCompte" />
                            <c:forEach var="i" items="${compte}">
                                <div class="form-group">
                                    <label>Login</label>
                                    <input type="text" name="login" value="${log}"  disabled="disabled" class="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label>Ancien mot de passe</label>
                                    <input type="password" name="ancienMdp" value="" placeholder="Ancien mot de passe" required="" class="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label>Nouveau mot de passe</label>
                                    <input type="password" name="nouveauMdp" value="" placeholder="Nouveau mot de passe" required="" class="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label>Confirmer mot de passe</label>
                                    <input type="password" name="confirmerMdp" value="" placeholder="confirmer mot de passe" required="" class="form-control"/>
                                </div>
                                <div>
                                    <button class="btn btn-success btn-block" type="submit">Valider</button>
                                </div><br>
                                <div>
                                    <button class="btn btn-danger btn-block" type="reset">Annuler</button>
                                </div>
                            </c:forEach>                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
