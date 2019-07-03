<%-- 
    Document   : infoInscription
    Created on : 13 mai 2019, 03:56:08
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
        <div class="col-lg-3"></div>
        <div class="col-lg-9">
            <p style="text-align: center;font-size: 20px">Votre enfant à été bien inscrit dans notre école</p>
            <p style="text-align: center;font-size: 20px">Information de l'élève</p>
            <p style="text-align: center;font-size: 25px">
                Login : ${loginElv} <br>
                Mot de passe :${passwordElv}<br>
            </p>
            <c:if test="${!empty loginPar}">
                <p style="text-align: center;font-size: 20px">Information du parent</p>
                <p style="text-align: center;font-size: 25px">
                    Login : ${loginPar} <br>
                    Mot de passe :${passwordPar}<br>
                </p>
            </c:if>
            <h2>Rapprochez vous du directeur des études pour valider votre inscription et 
                Songez à changer vos paramètres de compte pour plus de sécurité</h2>

        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
