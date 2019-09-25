<%-- 
    Document   : detailProf
    Created on : 11 oct. 2018, 18:58:25
    Author     : Moussa Joseph Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Infos Professeurs</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Informations Professeur</p>
                    <table class="table table-bordered table-hover table-responsive">
                        <thead>
                            <tr>
                                <th id="entete">Classe</th>
                                <th id="entete">Régime</th>
                                <th id="entete">Matière</th>
                                <th id="entete">Année Scolaire</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="d" items="${detailProf}">
                                <tr>

                                    <td>${d.nomClasse}</td>
                                    <td>${d.regime}</td>
                                    <td>${d.nomMatiere}</td>
                                    <td>${d.annee}</td>
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
