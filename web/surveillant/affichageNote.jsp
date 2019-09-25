<%-- 
    Document   : affichageNote
    Created on : 12 août 2018, 01:47:41
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Voir notes</title>
    </head>
    <body>

        <%            if (session.getAttribute("log") != null) {
        %>

        <%@include file="barreNavSurveillant.jsp" %>        
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Liste des Notes en ${nomMatiere} de la ${nomClasse}</p>

                    <table class="table table-hover table-bordered" >
                        <thead>
                            <tr>
                                <th class="col" id="entete">Prénom</th>
                                <th class="col" id="entete">Nom</th>
                                <th class="col" id="entete">Date de Naissance</th>
                                <th class="col" id="entete">Lieu de Naissance</th>
                                <th class="col" id="entete">Devoir</th>
                                <th class="col" id="entete">Composition</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="e" items="${eleves}">
                                <tr>
                                    <td>${e.prenom}</td>
                                    <td>${e.nom}</td>                       
                                    <td>${e.dateNaissance}</td>
                                    <td>${e.lieuNaissance}</td>
                                    <td><c:choose>
                                            <c:when test ="${e.devoir == 0.0}">

                                            </c:when>
                                            <c:otherwise>
                                                ${e.devoir}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>
                                        <c:choose><c:when test ="${e.composition == 0.0}">

                                            </c:when>
                                            <c:otherwise>
                                                ${e.composition}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  


    </body>
</html>
