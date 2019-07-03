

<%-- 
    Document   : AloutNote
    Created on : 12 juil. 2018, 12:49:56
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link href="dist/css/mystyle.css" rel="stylesheet"/>
        <title>Ajouter Notes</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="acceuil.jsp" %>
        <script>
            <c:if test="${!empty message}">
            alert("les notes ne doivent pas etre supérieur à 20");
            </c:if>
            <c:if test="${!empty mes}">
            alert("les notes ont été ajouter avec succée");
            </c:if>
            function verifTaille() {
                var val1 = document.getElementById("note1").value,
                         val2 = document.getElementById("note2").value,
                         val3 = document.getElementById("note3").value,
                        taille1 = document.getElementById("verifNote1"),
                        taille2 = document.getElementById("verifNote2"),
                        taille3 = document.getElementById("verifNote3");
                /*taille.innerHTML = val1;*/
                if (val1 > 20) {
                    /*onkeypress="verifTaille()"*/
                    taille1.innerHTML = "<span style='color: red';font-weight: bold>La note ne peut etre supérieur à 20</span>";
                }
                if (val2 > 20) {
                    /*onkeypress="verifTaille()"*/
                    taille2.innerHTML = "<span style='color: red';font-weight: bold>La note ne peut etre supérieur à 20</span>";
                }
                if (val3 > 20) {
                    /*onkeypress="verifTaille()"*/
                    taille3.innerHTML = "<span style='color: red';font-weight: bold>La note ne peut etre supérieur à 20</span>";
                }
            }
        </script>
        <%--  <h1>${message}</h1> --%>
        <div>
            <form method="post" action="Controleur">
                <input type="hidden" name="action" value="ajouterNote"/>
                <input type="hidden" name="annee" value="${annee}"/>
                <input type="hidden" name="semestre" value="${semestre}"/>
                <input type="hidden" name="matiere" value="${matiere}"/>
                <table class="table table-bordered" id="tab1">
                    <thead>
                        <tr>
                            <th scope="col" id="entete">Prénom</th>
                            <th scope="col" id="entete">Nom</th>
                            <th scope="col" id="entete">Devoir 1</th>
                            <th scope="col" id="entete">Devoir 2</th>
                            <th scope="col" id="entete">Composition</th>
                        </tr>
                    </thead>
                    <c:forEach var="i" items="${eleve}">
                        <tbody>
                            <tr>
                                <td>${i.prenom}</td>
                                <td>${i.nom}</td>
                                <td><input id="note1" type="float" onkeypress='return event.charCode >= 46 && event.charCode <= 57' name="devoir1" required="" maxlength="5" class="form-control" onkeyup="verifTaille()"/><span id="verifNote1"></span></td>
                                <td><input id="note2" type="float" onkeypress='return event.charCode >= 46 && event.charCode <= 57' name="devoir2" required="" maxlength="5" class="form-control" onkeyup="verifTaille()"/><span id="verifNote2"></span></td>
                                <td><input id="note3" type="float" onkeypress='return event.charCode >= 46 && event.charCode <= 57' name="composition" required="" maxlength="5" class="form-control" onkeyup="verifTaille()"/><span id="verifNote3"></span></td>
                            </tr>    
                        </tbody>
                    </c:forEach> 
                </table>
                <div id="btn">
                    <button class="btn-success">Valider</button>
                </div>
            </form>  
        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
