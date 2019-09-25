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
        <title>${profils} | Liste des Professeurs</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Liste des Professeurs de l'etablissement</p>

                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th id="entete" class="col">Nom</th>
                                <th id="entete" class="col">Prénom</th>
                                <th id="entete" class="col">Adresse</th>
                                <th id="entete" class="col">Téléphone</th>
                                    <%--  <th id="entete" class="col">Désactiver</th> --%>
                                <th id="entete" class="col">Modifier</th>
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
                                    <td>
                                        <a href="Surveillant?action=modifierProf&&loginProf=${p.personne.login}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                    </td>
                                    <td>
                                        <a href="Surveillant?action=detailProf&&loginProf=${p.personne.login}" class="btn btn-primary">Détails</a>
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