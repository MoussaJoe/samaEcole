<%-- 
    Document   : modificationProf
    Created on : 8 août 2018, 14:05:37
    Author     : Moussa Joseph Sarr
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
      
                <%@include file="surveillant.jsp" %>
          
        <h1>Modification Professeur :</h1>

        <form action="Surveillant" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="valideModProf" />
                <input type="hidden" name="login" value="${login}" />          
                <tr>
                    <th>Nom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nom" value="${professeur.getPersonne().getNom()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Prenom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenom" value="${professeur.getPersonne().getPrenom()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Adresse :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="adresse" value="${professeur.getPersonne().getAdresse()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Telephone :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="tel" value="${professeur.getPersonne().getTel()}" class="form-control"/>
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

            <% } else {
            %>
            <jsp:forward page="../vue/SeConnecter.jsp"/>
            <% }%> 
    </body>
</html>
