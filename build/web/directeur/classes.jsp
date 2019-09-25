<%-- 
    Document   : classes
    Created on : 17 juin 2019, 12:37:21
    Author     : Moussa Joseph D Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Classes</title>
    </head>
    <body>
        <%             if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Les classes de l'établissement</p>

                    <table class="table table-bordered table-hover table-responsive">
                        <thead>
                            <tr>
                                <th id="entete" class="col">Nom Classe</th>
                                <th id="entete" class="col">Régime</th>
                                <th id="entete" class="col">Détails</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${classes}">
                                <tr>
                                    <td>${c.nomClasse}</td>
                                    <td>${c.regime}</td>
                                    <td><a href="Directeur?action=detailsClasse&&nomClasse=${c.nomClasse}&&regime=${c.regime}" class="btn btn-primary">Détails</a></td>

                                </tr>
                            </c:forEach>  
                        </tbody>
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
