<%-- 
    Document   : detailsAbsence
    Created on : 8 sept. 2019, 21:59:06
    Author     : Moussa Joseph D Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="surveillant.jsp" %>
        <h1>Liste des absences :</h1>
        <table class="table table-bordered table-hover" id="tab1" >
            <thead>
                <tr>
                    <th id="entete" class="col">Matière</th>
                    <th id="entete" class="col">absences</th>
                    <th id="entete" class="col">retards</th>
                    <th id="entete" class="col">Modifier</th>
                    <th id="entete" class="col">Supprimer</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="ma" items="${matabs}">

                <td>${ma.matiere}</td>
                <td>${ma.heureAbsence}h</td>
                <td>${ma.heureRetard}</td>
                <td><a  href="Surveillant?action=modifierAbs&&login=${login}&&semestre=${semestre}&&annee=${anInscr}&&matiere=${ma.matiere}&&abs=${ma.heureAbsence}&&ret=${ma.heureRetard}"><img src="modifier.png" style="width: 2vw;" ></a></td>
                <td><a href="Surveillant?action=supprimerAbs&&login=${login}&&semestre=${semestre}&&annee=${anInscr}&&matiere=${ma.matiere}&&abs=0&&ret=0" onclick="confirmation()"><span class="glyphicon glyphicon-remove"></span></a></td>
            </tr>

        </c:forEach>

    </tbody>
</table>

<% } else {
%>
<jsp:forward page="../vue/SeConnecter.jsp"/>
<% }%>  

<script type="text/javascript">
    function confirmation() {
        var msg = confirm("es-tu sur de vouloir supprimer?");
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>
