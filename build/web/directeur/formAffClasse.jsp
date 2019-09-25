<<<<<<< HEAD
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

        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p class="titre">Formulaire d'affichage des classes</p>
                    <div class="col-lg-4"></div>           
                    <div class="col-lg-4">
                        <form action="Directeur" method="POST">
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
=======
<%-- 
    Document   : formAffClasse
    Created on : 21 fÃ©vr. 2019, 14:19:26
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulaire Aff. Classe</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <c:choose>
            <c:when test="${profils eq 'Directeur'}">
                <%@include file="accueilDirecteur.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="../surveillant.jsp" %>
            </c:otherwise>
        </c:choose>

        <h1>Formulaire d'affichage de Classe :</h1>

        <form action="Directeur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="listerClasse" />
                <tr>
                    <td>Nom Classe :</td>
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
                </tr>
                &nbsp;&nbsp;
                <tr>
                    <td>RÃ©gime :</td> 
                    <td>
                        <div class="form-group">
                            <select name="regime" class="form-control" required="">
                                <option>Privee</option>
                                <option>Public</option>
                            </select>
                        </div>
                    </td>
                </tr>
                &nbsp;&nbsp;
                <tr>
                    <td>AnnÃ©e Scolaire :</td>
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
                </tr>
                &nbsp;&nbsp;
                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-success">Valider</button>
                    </td>
                </tr>

            </table>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
