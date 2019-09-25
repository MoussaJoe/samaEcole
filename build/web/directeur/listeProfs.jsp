<%-- 
    Document   : listeProfs
    Created on : 20 juil. 2018, 03:10:38
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Professeur"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${profils} | Nos Professeurs</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Liste des professeurs de l'établissement </p>

                    <table class="table table-bordered table-hover table-responsive" >
                        <thead>
                            <tr>
                                <th id="entete" class="col">Nom</th>
                                <th id="entete" class="col">Prénom</th>
                                <th id="entete" class="col">Adresse</th>
                                <th id="entete" class="col">Téléphone</th>
                                    <%--  <th id="entete" class="col">Désactiver</th> --%>
                                    <c:if test="${profils eq 'surveillant'}">
                                    <th id="entete" class="col">Modifier</th>
                                    </c:if>
                                <th id="entete" class="col">Détails</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${profs}">
                                <tr>
                                    <td>${p.personne.nom}</td>
                                    <td>${p.personne.prenom}</td>
                                    <td>${p.personne.adresse}</td>
                                    <td>${p.personne.tel}</td>
                                    <%-- <td><button><a href="ControleurDirecteur?action=desactiverProf&&idProf=${p.personne.idPersonne}">Désactiver</a></button></td> --%>
                                    <c:if test="${profils eq 'surveillant'}">
                                        <td>
                                            <a href="Directeur?action=modifierProf&&idProf=${p.personne.idPersonne}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                        </td>
                                    </c:if>
                                    <td>
                                        <a href="Directeur?action=detailProf&&loginProf=${p.personne.login}" class="btn btn-primary">Détails</a>
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