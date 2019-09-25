<%-- 
    Document   : form-recap
    Created on : 15 sept. 2019, 02:40:46
    Author     : Moussa Joseph D Sarr
--%>

<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Récapitulatif des moyennes</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {
        %>
        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Récapitulation des moyennes</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form method="POST" action="Surveillant">
                            <input type="hidden" name="action" value="recapMoy"/>
                            <div class="form-group">
                                <label>Nom classe</label>
                                <select name="nomClasse" class="form-control">
                                    <c:forEach var="p" items="${classes}">
                                        <option>${p.nomClasse}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Régime</label>
                                <select name="regime" class="form-control">
                                    <c:forEach var="r" items="${regimes}">
                                        <option>${r}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Semestre</label>
                                <select name="semestre" class="form-control">
                                    <option value="1er_semestre">1er semestre</option>
                                    <option value="2eme_semestre">2nd semestre</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Année-Scolaire</label>
                                <select name="annee" class="form-control">
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
    </body>    
</html>

