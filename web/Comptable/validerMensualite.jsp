<%-- 
    Document   : validerMensualite
    Created on : 21 mai 2019, 04:59:42
    Author     : Ouzy NDIAYE
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta  charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="dist/css/mystyle.css" />
        <link rel="stylesheet" type="text/css" href="Style/style1.css">
        <title>Acceuil</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavCompta.jsp" %> 
        
        <div class="col-lg-4"></div>
            <div class="col-lg-4">
                <form method="Post" action="Comptable">
                    <input type="hidden" name="connect" value="validerMensuel"/>
                    <input type="hidden" name="login" value="${reglement}"/>
                    <input type="hidden" name="nomClasse" value="${nomClasse}"/>
                    <input type="hidden" name="montantApayer" value="${montantApayer}"/>
                    <input type="hidden" name="moisMensuel" value="${moisMensuel}"/>
                    <div class="form-group">
                        <label for="montant">Montant</label>
                        <input type="text" name="montant" class="form-control" value="${montant}"  maxlength="7"
                               onkeypress=" return event.charCode >= 48 && event.charCode <= 57" required>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-success btn-block " type="submit">Valider</button>
                    </div>
                </form>
            </div>
         <!--///////////Fin/////////////// -->
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>                                                    
        <% }%>
    </body>
</html>
