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
        <h1>Enregistrement Matière!</h1>
        <script>
            <c:if test="${!empty message}">
            alert("échec de l'ajout de la matière!");
            </c:if>
            <c:if test="${!empty mes}">
            alert("Matière ajoutée avec succée!!");
            </c:if>
        </script>
        <form action="Directeur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="validerMatiere" />
                <tr>
                    <th>Nom Matière :</th>
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

       <br>
        <br>
        <table class="table-bordered table-hover" id="tabc" style="margin-left: 47vw; border: #ffffff">

            <tbody>
                <tr>
                    <td colspan="3" id="entete">Matieres :</td>

                </tr>
                <c:forEach var="cm" items="${matieres}">
                    <tr>
                        <td>${cm}</td> 
                        <td><a href="Directeur?matiere=${cm}&&action=supprimerMatiere" onclick="javascript: return confirmation();"><span class="glyphicon glyphicon-remove"></span></a></td>
                        <td><a href="Directeur?matiere=${cm}&&action=modifierMatiere" ><img src="modifier.png" style="height: 25px"/></a></td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
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