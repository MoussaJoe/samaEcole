<%-- 
    Document   : listerNote
    Created on : 23 juil. 2018, 14:50:45
    Author     : ibrah
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Professeur | Voir notes devoir</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavProf.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <form method="post" action="ControleurProf">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th scope="col" id="entete">Prénom</th>
                                    <th scope="col" id="entete">Nom</th>
                                    <th scope="col" id="entete">Matière</th>
                                    <th scope="col" id="entete">Semestre</th>
                                    <th scope="col" id="entete">Note devoir</th>
                                    <th scope="col" id="entete">Modifier</th>
                                </tr>
                            </thead>
                            <c:forEach var="i" items="${eleve}">
                                <tbody>
                                    <tr>
                                        <td>${i.prenom}</td>
                                        <td>${i.nom}</td>
                                        <td>${i.matiere}</td>
                                        <td>${i.semestre}</td>
                                        <td>${i.devoir1}</td>
                                        <td><a href="ControleurProf?action=modifierDevoir&prenom=${i.prenom}&nom=${i.nom}&devoir=${i.devoir1}&loginEleve=${i.matriculeEleve}&matiere=${i.matiere}&semestre=${i.semestre}&annee=${i.annee}&classe=${i.classe}&idDevoir=${i.idDevoir}"><img src="modifier.png" alt="Modifier" id="modifier"/></a></td>
                                    </tr>    
                                </tbody>
                            </c:forEach> 
                        </table>
                    </form>  
                </div>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
