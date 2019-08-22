<%-- 
    Document   : affichageNoteEleve
    Created on : 20 ao�t 2018, 13:29:53
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eleve | mes notes</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavEleve.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-6">
                    <p class="titre">Veuillez choisir le semestre concern� :</p>
                    <table class="table table-bordered">
                        <tr>

                            <td class="test">
                                <a href="Eleve?connect=1er_semestre&&annee=${annee}">1er Semestre</a>
                            </td>
                            <td class="test">
                                <a href="Eleve?connect=2eme_semestre&&annee=${annee}">2nd Semestre</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <%             if (request.getAttribute("action") != null) {
                ArrayList<Eleve> eleves = (ArrayList<Eleve>) request.getAttribute("eleves");
        %>
        <
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    
                    <p class="titre">Ann�e Scolaire : <a style="color: #00a1dc;text-decoration: none"
                                                         href="ControleurEleve?action=afficherNote">${annee}</a>
                    </p>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th class="col" id="entete">Matieres</th>
                                <th class="col" id="entete">Devoir1</th>
                                <th class="col" id="entete">Devoir2</th>
                                <th class="col" id="entete">Composition</th>
                                <th class="col" id="entete">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="e" items="${eleves}">
                                <tr>
                                    <td>${e.matiere}</td>
                                    <td>${e.devoir1}</td>
                                    <td>${e.devoir2}</td>
                                    <td>${e.composition}</td>
                                    <c:choose>
                                        <c:when test="${!empty varAn}">
                                            <td><a class="btn btn-success" href="ControleurEleve?action=reclamer&nomClasse=${e.classe}&nomMatiere=${e.matiere}">R�clamer</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><button class="btn btn-success" href="ControleurEleve?annee=${a}&&action=anneeScolaire" disabled="disabled">R�clamer</button></td>


                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%}
        } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
