<%-- 
    Document   : affichageNote
    Created on : 12 ao�t 2018, 01:47:41
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Afficher notes</title>
    </head>
    <body>

        <%            if (session.getAttribute("log") != null) {
        %>

        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">    
                    <c:if test="${empty eleveVide}">
                        <p class="titre">Aucun �l�ve n'est inscrit dans la classe ${nomClasse}</p>
                    </c:if>

                    <c:if test="${!empty eleveVide}">
                        <p class="titre">Liste des notes en ${nomMatiere} de la ${nomClasse}</p>

                        <table class="table table-hover table-bordered table-responsive" >
                            <thead>
                                <tr>
                                    <th class="col" id="entete">Pr�nom</th>
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
                    </c:if>
                </div>
            </div>
        </div>

        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  


    </body>
</html>
