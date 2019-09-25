<%-- 
    Document   : listeClasse
    Created on : 25 juil. 2018, 17:02:26
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Liste Classes</title>
    </head>
    <body>

        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Liste de la classe : ${nomCl} en ${an}</p>
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <form action="Directeur" method="POST">
                            <table>
                                <input type="hidden" name="action" value="listerClasse" />
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <select name="nomClasse" class="form-control" required="">
                                                <c:forEach var="p" items="${classes}">
                                                    <c:choose>
                                                        <c:when test="${p.nomClasse eq nomCl}">
                                                            <option selected >${p.nomClasse}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option>${p.nomClasse}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    &nbsp;&nbsp;
                                    <td>
                                        <div class="form-group">
                                            <select name="regime" class="form-control" required="">
                                                <c:forEach var="r" items="${regimes}">
                                                    <c:choose>
                                                        <c:when test="${r eq regime}">
                                                            <option selected >${r}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option>${r}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>

                                            </select>
                                        </div>
                                    </td>
                                    &nbsp;&nbsp;
                                    <td>
                                        <div class="form-group">
                                            <select name="year" class="form-control" required="">
                                                <c:forEach var="a" items="${annees}">
                                                    <c:choose>
                                                        <c:when test="${a eq an}">
                                                            <option selected >${a}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option>${a}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    &nbsp;&nbsp;
                                    <td>
                                        <div class="form-group">
                                            <button class="btn btn-success">Valider</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12"> 
                    <table class="table table-bordered table-hover table-responsive">
                        <thead>
                            <tr>
                                <th id="entete" class="col">Nom</th>
                                <th id="entete" class="col">Prenom</th>
                                <th id="entete" class="col">Adresse</th>
                                <th id="entete" class="col">Telephone</th>
                                <th id="entete" class="col">Date de Naissance</th>
                                <th id="entete" class="col">Lieu de Naissance</th>
                                <th id="entete" class="col">Etat Paiement</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="e" items="${eleves}">
                                <tr>
                                    <td>${e.nom}</td>
                                    <td>${e.prenom}</td>
                                    <td>${e.adresse}</td>
                                    <td>
                                        <c:if test="${empty e.tel}">
                                            Néant
                                        </c:if>
                                        <c:if test="${!empty e.tel}">
                                            ${e.tel}
                                        </c:if>                                        
                                    </td>
                                    <td>${e.dateNaissance}</td>
                                    <td>${e.lieuNaissance}</td>
                                    <c:choose>
                                        <c:when test="${e.etatPaiement eq '1'}">
                                            <td style="background-color: greenyellow">A Jour</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="background-color: red">Pas A Jour</td>
                                        </c:otherwise>
                                    </c:choose>


                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <% } else {
                    %>
                    <jsp:forward page="../SeConnecter.jsp"/>
                    <% }%>  

                    </body>
                    </html>

                    <script type="text/javascript">
                        function confirmation() {
                            var msg = confirm("tu veux supprimer cette ligne");
                            if (msg) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    </script>
