<%-- 
    Document   : listeMesProfs
    Created on : 27 août 2018, 14:14:07
    Author     : Moussa Joseph Sarr
--%>

<%@page import="modelTables.Personne"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Elève | Mes professeurs</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {
                ArrayList<Personne> profs = (ArrayList<Personne>) request.getAttribute("profs");
        %>
        <%@include file="barreNavEleve.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p class="titre">Liste de mes professeurs </p>

                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th class="col" id="entete">Prénom</th>
                                <th class="col" id="entete">Nom</th>                    
                                <!--<th class="col" id="entete">Adresse</th>
                                <th class="col" id="entete">Téléphone</th>-->
                                <th class="col" id="entete">Matière</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach var="p" items="${profs}">
                                <tr>
                                    <td>${p.prenom}</td> 
                                    <td>${p.nom}</td>                                               
                                    <td>
                                        <c:forEach begin="0" end="0" var="m" items="${p.matiere}">
                                            ${m}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tr>
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

