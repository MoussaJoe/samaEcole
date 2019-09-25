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
        <title>${profils} | Liste classe ${nomCl}</title>
    </head>
    <body>

        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <form action="Surveillant" method="POST">
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
                                        <div class="form-group"></div>
                                    </td>
                                    &nbsp;&nbsp;
                                    <td>
                                        <div class="form-group"> 
                                            <button class="btn btn-success btn-block" type="submit">Valider</button>
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
                    <c:if test="${empty eleves}">
                        <p class="titre">Désolé aucun élève n'est incrit dans la classe ${nomCl}</p>
                    </c:if>
                        
                    <c:if test="${!empty eleves}">
                    <p class="titre">Liste de la Classe : ${nomCl} en ${an}</p>                    
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th id="entete" class="col">Nom</th>
                                <th id="entete" class="col">Prenom</th>
                                <th id="entete" class="col">Adresse</th>
                                <th id="entete" class="col">Telephone</th>
                                <th id="entete" class="col">Date de Naissance</th>
                                <th id="entete" class="col">Lieu de Naissance</th>
                                <th id="entete" class="col">Etat Paiement</th>
                                <th id="entete" class="col">Modifier</th>
                                <th id="entete" class="col">Supprimer</th>

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


                                    <td>
                                        <a href="Surveillant?action=modifierElv&&nom=${e.login}&&year=${an}&&regime=${regime}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                    </td>
                                    <td>
                                        <a href="Surveillant?action=supprimerElv&&nom=${e.login}&&year=${an}&&regime=${regime}&&nomClasse=${nomCl}" onclick="confirmation()">
                                            <img src="delete.png" alt="Supprimer" id="modifier"/></a>
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

<script type="text/javascript">
    function confirmation() {
        var msg = confirm("Voulez vous vraiment supprimer cette élève");
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
</script>
