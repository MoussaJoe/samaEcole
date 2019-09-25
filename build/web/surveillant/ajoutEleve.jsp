<<<<<<< HEAD:build/web/surveillant/ajoutEleve.jsp
<%-- 
    Document   : ajoutEleve
    Created on : 12 juil. 2018, 14:17:27
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout d'Elèves</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="surveillant.jsp" %>
        <h1>Formulaire d'Enregistrement d'élève :</h1>
        <table id="tab" class="table table-bordered">
            <tr>
                <td style="background-color: #2ecc71" class="test">
                    <a href="Surveillant?action=inscription">Inscription</a>
                </td>
                <td style="background-color: #2ecc71" class="test">
                    <a href="Surveillant?action=reinscription">RéInscription</a>
                </td>
        </table>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
=======
<%-- 
    Document   : ajoutEleve
    Created on : 12 juil. 2018, 14:17:27
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout d'Elèves</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="../../surveillant.jsp" %>
        <h1>Formulaire d'Enregistrement d'élève :</h1>
        <table id="tab" class="table table-bordered">
            <tr>
                <td style="background-color: #2ecc71" class="test">
                    <a href="ControleurDirecteur?action=inscription">Inscription</a>
                </td>
                <td style="background-color: #2ecc71" class="test">
                    <a href="ControleurDirecteur?action=reinscription">RéInscription</a>
                </td>
        </table>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403:web/vue/surveillant/ajoutEleve.jsp
