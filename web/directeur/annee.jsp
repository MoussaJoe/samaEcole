<<<<<<< HEAD
<%-- 
    Document   : annee
    Created on : 15 nov. 2018, 22:51:43
    Author     : Moussa Joseph Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Année-Scolaire</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>

        <script>
            <c:if test="${!empty msg}">
            alert("Veuillez revoir l'année Scolaire saisi!");
            </c:if>
        </script>
        <script>
            <c:if test="${!empty er}">
             alert("Veuillez Changer d'année; celle-ci a été déjà ajoutée!");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p class="titre">Liste des années-scolaire</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">                                                                        
                        <table class="table table-bordered table-hover table-responsive">
                            <tr>
                                <th id="entete">Année Scolaire</th>
                            </tr>
                            <c:forEach var="p" items="${annees}">
                                <tr>
                                    <td>${p}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                
                
                <div class="col-lg-12">
                    <p class="titre">Ajouter une nouvelle année-scolaire</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="Post">
                            <input type="hidden" name="action" value="ajoutAnnee">

                            <div class="form-group">
                                <label>Année Scolaire</label>
                                <input type="text" name="nv-annee" placeholder="format 2018-2019" maxlength="9" onkeypress=" return event.charCode >= 48 && event.charCode <= 57 || event.charCode === 45" class="form-control" required="">
                            </div>

                            <div class="form-group">
                                <button class="btn btn-success btn-block " type="submit">Ajouter</button>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
=======
<%-- 
    Document   : annee
    Created on : 15 nov. 2018, 22:51:43
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AnnÃ©e-Scolaire</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
        <h1>AnnÃ©e Scolaire :</h1>
        <table class="table table-bordered table-hover" id="tab1" >
            <tr><th id="entete" class="col">AnnÃ©e Scolaire</th></tr>
                    <c:forEach var="p" items="${annees}">
                <tr><td>${p}</td></tr>
            </c:forEach>
        </table>

        <form action="Directeur" method="Post">
            <input type="hidden" name="action" value="ajoutAnnee">
            <table id="tab">
                <tr><th>
                        Ajouter une nouvelle AnnÃ©e Scolaire : </th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nv-annee" value="" placeholder="format 2018-2019" maxlength="9" onkeypress=" return event.charCode >= 48 && event.charCode <=57 || event.charCode === 45" class="form-control" required="">
                        </div></td>
                </tr>
                <tr><th>
                    </th>
                    <td><button class="btn btn-success">Valider</button></td>
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
