<%-- 
    Document   : listeAbsence
    Created on : 8 sept. 2019, 15:35:30
    Author     : Moussa Joseph D Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Absence</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Liste des absences</p>
                    <div class="col-lg-1"></div>
                    <div class="col-lg-10">
                         <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th id="entete" class="col">Nom</th>
                                    <th id="entete" class="col">Prénom</th>
                                    <th id="entete" class="col">Total absences</th>
                                    <th id="entete" class="col">Total retards</th>
                                    <th id="entete" class="col">Détails</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="abs" items="${absences}">
                                    <tr style="text-align: center;">
                                        <td>${abs.nom}</td>
                                        <td>${abs.prenom}</td>
                                        <td>${abs.totalAbsence}h</td>
                                        <td>${abs.totalRetard}</td>
                                        <td><a href="Surveillant?action=detailsAbsence&&login=${abs.login}" class="btn btn-primary">Détails</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
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
