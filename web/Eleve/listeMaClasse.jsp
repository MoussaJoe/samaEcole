<%-- 
    Document   : listeMaClasse
    Created on : 21 août 2018, 01:25:16
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Eleve | ma classe</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {
                ArrayList<Eleve> eleves = (ArrayList<Eleve>) request.getAttribute("eleves");
        %>
        <%@include file="barreNavEleve.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p class="titre">Liste de Ma Classe :</p>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th class="col" id="entete">Nom</th>
                                <th class="col" id="entete">Prénom</th>
                                <th class="col" id="entete">Adresse</th>
                                <th class="col" id="entete">Téléphone</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach var="e" items="${eleves}">
                                <tr>
                                    <c:if test="${e.login == login}">
                                        <td>(Moi) ${e.prenom}</td>
                                        <td>${e.nom}</td>
                                        <td>${e.adresse}</td>
                                        <c:if test="${!empty e.tel}">
                                            <td>${e.tel}</td>
                                        </c:if>
                                        <c:if test="${empty e.tel}">
                                            <td>Néant</td>
                                        </c:if>

                                    </c:if>
                                    <c:if test="${e.login != login}">
                                        <td>${e.prenom}</td>
                                        <td>${e.nom}</td>
                                        <td>${e.adresse}</td>
                                        <c:if test="${!empty e.tel}">
                                            <td>${e.tel}</td>
                                        </c:if>
                                        <c:if test="${empty e.tel}">
                                            <td>Néant</td>
                                        </c:if>
                                    </c:if>

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
