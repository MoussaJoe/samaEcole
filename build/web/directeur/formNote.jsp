<%-- 
    Document   : formNote
    Created on : 12 août 2018, 00:51:11
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Notes</title>
    </head>

    <%        if (session.getAttribute("log") != null) {
            // String profils = (String) session.getAttribute("profils");
%>

    <%@include file="barreNavDirecteur.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">                    
                <p class="titre">Formulaire à renseigner pour afficher les notes</p>
                <div class="col-lg-4"></div>
                <div class="col-lg-4">

                    <form action="Directeur" method="Post">
                        <input type="hidden" name="action" value="voirNote">

                        <div class="form-group">
                            <label>Nom classe</label>
                            <select name="nomClasse" class="form-control" required="">
                                <c:forEach var="p" items="${classes}">
                                    <option>${p.nomClasse}</option>       
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Régime</label>
                            <select name="regime" class="form-control" required="">
                                <c:forEach var="r" items="${regimes}">
                                    <option>${r}</option>       
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Nom matière</label>
                            <select name="nomMatiere" class="form-control" required="">
                                <c:forEach var="m" items="${matieres}">
                                    <option>${m}</option> 
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Semestre</label>
                            <select name="semestre" class="form-control" required="">                         
                                <option value="1er_semestre">1er semestre</option> 
                                <option value="2eme_semestre">2nd semestre</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Année-Scolaire</label>
                            <select name="annee" class="form-control" required="">
                                <c:forEach var="a" items="${annees}">
                                    <option>${a}</option> 
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <button class="btn btn-success btn-block" type="submit">Valider</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>

    <% } else {
    %>
    <jsp:forward page="../vue/SeConnecter.jsp"/>
    <% }%> 



</html>
