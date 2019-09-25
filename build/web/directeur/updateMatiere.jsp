<%-- 
    Document   : updateMatiere
    Created on : 20 août 2019, 00:14:39
    Author     : Moussa Joseph D Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Modifier matière</title>
    </head>
    <body>
        <%               if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Modifier la matière ${nomMatiere}</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="POST">
                            <input type="hidden" name="action" value="validerMatiereMod" />
                            <input type="hidden" name="oldmatiere" value="${nomMatiere}" />

                            <div class="form-group">
                                <label>Nom de la matière</label>
                                <input type="text" name="nomMatiere" value="${nomMatiere}" class="form-control" required=""/>
                            </div>
                            <div>
                                <button class="btn btn-success btn-block" type="submit">Modifier</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
