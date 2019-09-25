<%-- 
    Document   : modificationProf
    Created on : 8 août 2018, 14:05:37
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Professeur"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Modifier prof</title>
    </head>
    <body>

        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Modifier professeur</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Surveillant" method="POST">
                            <input type="hidden" name="action" value="valideModProf" />
                            <input type="hidden" name="login" value="${login}" />

                            <div class="form-group">
                                <label>Nom</label>
                                <input type="text" name="nom" value="${professeur.getPersonne().getNom()}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>Prénom</label>
                                <input type="text" name="prenom" value="${professeur.getPersonne().getPrenom()}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>Adresse</label>
                                <input type="text" name="adresse" value="${professeur.getPersonne().getAdresse()}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <input type="text" name="tel" value="${professeur.getPersonne().getTel()}" class="form-control"
                                       maxlength="9" onkeypress=" return event.charCode >= 48 && event.charCode <= 57"/>
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
