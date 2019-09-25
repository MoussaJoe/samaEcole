<%-- 
    Document   : listeAnnee
    Created on : 19 nov. 2018, 14:58:13
    Author     : Moussa Joseph Sarr
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Elève | Année-Scolaire</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavEleve.jsp" %>
        <div class="container">
            <div class="row">
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <p class="titre">Choisir l'année Scolaire </p>
                        <table class="table table-hover">
                            <c:forEach var="a" items="${annees}">
                                <tr>
                                    <td><a class="btn btn-success btn-block" href="Eleve?annee=${a}&connect=anneeScolaire">${a}</a></td> 
                                </tr>

                            </c:forEach>
                        </table>
                    </div>
            </div>
        </div>


        <%} else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
