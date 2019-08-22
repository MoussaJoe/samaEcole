<%-- 
    Document   : eleve
    Created on : 9 juil. 2018, 16:55:17
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            <%@include file="barreNavEleve.jsp" %>

            <% } else {
            %>
            <jsp:forward page="vue/SeConnecter.jsp"/>
            <% }%> 
        </body>
    </html>
