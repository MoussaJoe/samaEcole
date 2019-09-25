<%-- 
    Document   : formAffClasse
    Created on : 21 févr. 2019, 14:19:26
    Author     : Moussa Joseph Sarr
--%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Afficher Classe</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {
        %>

        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Afficher classe</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Surveillant" method="POST">
                            <table>
                                <input type="hidden" name="action" value="listerClasse" />

                                <div class="form-group">
                                    <label>Nom Classe</label>
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
                                <div class="form-group">
                                    <label>Régime</label>
                                    <select name="regime" class="form-control" required="">
                                        <option>Privée</option>
                                        <option>Public</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Année-Scolaire</label>
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
