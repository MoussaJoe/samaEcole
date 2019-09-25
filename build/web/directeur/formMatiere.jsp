<<<<<<< HEAD
<%-- 
    Document   : formMatiere
    Created on : 9 sept. 2018, 15:25:17
    Author     : Moussa Joseph Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Ajouter Matière</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <script>
            <c:if test="${!empty message}">
            alert("échec de l'ajout de la matière!");
            </c:if>
            <c:if test="${!empty msgExiste}">
                alert("La matière que vous essayer d'ajouter existe déjà")
            </c:if>
            <c:if test="${!empty mes}">
            alert("Matière ajoutée avec succès!!");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Enregistrer une nouvelle matière</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="POST">
                            <input type="hidden" name="action" value="validerMatiere" />

                            <div class="form-group">
                                <label>Nom de la matière</label>
                                <input type="text" name="nomMatiere" value="" class="form-control" required=""/>
                            </div>
                            <div>
                                <button class="btn btn-success btn-block" type="submit">Valider</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div> 

        <br><br>
        <c:if test="${!empty matieres}">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">                    
                        <p class="titre">Liste des matières</p>
                        <table class="table table-bordered table-hover table-responsive">
                            <thead>
                                <tr>
                                    <th id="entete">Nom Matière</th>                                
                                    <th id="entete">Supprimer</th>
                                    <th id="entete">Modifier</th>

                                </tr>
                            </thead>

                            <tbody>                
                                <c:forEach var="cm" items="${matieres}">
                                    <tr>
                                        <td>${cm}</td> 
                                        <td><a href="Directeur?matiere=${cm}&&action=supprimerMatiere" onclick="javascript: return confirmation();"><span class="glyphicon glyphicon-remove"></span></a></td>
                                        <td><a href="Directeur?matiere=${cm}&&action=modifierMatiere" ><img src="modifier.png" style="height: 25px"/></a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
<script>
    function confirmation() {
        var code = "Voulez vous vraiment supprimer cette matière ?\
    ";
        var msg = confirm(code);
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
</script>
=======
<%-- 
    Document   : formMatiere
    Created on : 9 sept. 2018, 15:25:17
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
        <h1>Enregistrement MatiÃ¨re!</h1>
        <script>
            <c:if test="${!empty message}">
            alert("Ã©chec de l'ajout de la matiÃ¨re!");
            </c:if>
            <c:if test="${!empty mes}">
            alert("MatiÃ¨re ajoutÃ©e avec succÃ©e!!");
            </c:if>
        </script>
        <form action="Directeur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="validerMatiere" />
                <tr>
                    <th>Nom MatiÃ¨re :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nomMatiere" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-success">Enregistrer</button>
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
