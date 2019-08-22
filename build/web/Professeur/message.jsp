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
        <title>Messagerie</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %> 
        <%@include file="barreNavProf.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-lg-12"> 
                    <table class="table table-bordered table-hover">
                        <c:forEach var="i" items="${listMessage}">
                            <tbody>
                                <tr>
                                    <td>${i.dateMsg}</td>
                                    <td>${i.prenom}&nbsp;&nbsp;${i.nom}</td>
                                    <td><button class="btn btn-success bouton"><a href="ControleurProf?action=afficheMess&date=${i.dateMsg}&texte=${i.texteMsg}&login_pro=${i.pro_login}&login=${i.login}"><span class="glyphicon glyphicon-envelope"></span></a></button></td>
                                </tr>    
                            </tbody>
                        </c:forEach> 
                    </table>

                </div>
            </div>
        </div>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 

    </body>
</html>
