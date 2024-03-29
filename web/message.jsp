<%-- 
    Document   : reclamation
    Created on : 18 oct. 2018, 12:53:29
    Author     : ibrah
--%>

<%@page import="modelTables.Personne"%>
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
                ArrayList<Personne> profs = (ArrayList<Personne>) request.getAttribute("profs");
        %> 
        <%@include file="acceuil.jsp" %>
        <h1>Réponse: veuillez saisir le message que vous voulez envoyé </h1>
        <h2>${messAcu}</h2>

        <form method="post" action="Controleur">
            <input type="hidden" name="action" value="reponse"/>
            <input type="hidden" name="loginProf" value="${loginProf}"/>
            <input type="hidden" name="loginEleve" value="${loginEleve}"/>
            <input type="hidden" name="nom" value="${nom}"/>
            <input type="hidden" name="prenom" value="${prenom}"/>
            <input type="hidden" name="idReclamation" value="${idReclamation}"/>
            <table class="table table-bordered" id="tab">
                <thead>
                    <tr>
                        <th class="col" id="entete">Message</th>  
                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <td>
                            <textarea name="message" class="form-control"></textarea>
                        </td>
                    </tr>
                    <tr><td align="center"><button name="envoyer" class="btn btn-success">Envoyer</button></td></tr>
                </tbody>
            </table>
        </form>

        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%> 

    </body>
</html>
