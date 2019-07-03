<%-- 
    Document   : listeBulletin
    Created on : 16 oct. 2018, 18:54:56
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="listeBulletin.css">
        <title>Bulletin</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="../../surveillant.jsp" %>
        <h1>Liste des Classes :</h1>
        <script>
            <c:if test="${!empty mess}">
            alert("Le bulletin a été créer avec succés");
            </c:if>
        </script>
        <table  id="tab1" class="table table-bordered table-hover">
            <tr>
                <th class="col" id="entete">Nom</th>
                <th class="col" id="entete">Prénom</th>
                <th class="col" id="entete">Bulletin</th>

            </tr>
            <!--<tr>-->
            <c:forEach var="e" items="${eleves}">
                <tr>
                    <td>${e.nom}</td>
                    <td>${e.prenom}</td>
                    <td>
                        <p id="formulaire">
                            <button class="btn btn-default" onclick="myFunction()" id="bulletin">
                                Bulletin
                            </button>
                        </p>   
                        <!--<a 
                            href="ControleurDirecteur?action=creerBulletin&&login=${e.login}&&annee=${annee}&&semestre=${semestre}"
                            ></a>-->          
                    </td>
                    <td>                        
                        <script>
                            var codeBulletin = "\
                        <form class='form-inline' method='POST'>\n\
                        <input type='hidden' name='action' value='creerBulletin'/>\n\
                        <input type='hidden' name='login' value='${e.login}'/>\n\
                        <input type='hidden' name='annee' value='${annee}'/>\n\
                        <input type='hidden' name='semestre' value='${semestre}'/>\n\
                        <div class='form-group'>\n\
                            <label>Absence</label>\n\
                            <input type='text' class='form-control' name='absence'\n\
                            onkeypress='return event.charCode >=48 && event.charCode <= 57' maxlength='3' required/>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                            <label>Retard</label>\n\
                            <input type='text' class='form-control' name='retard'\n\
                            onkeypress='return event.charCode >=48 && event.charCode <= 57' maxlength='3' required/>\n\
                        </div><br><br>\n\
                        <button type='submit' class='btn bnt-success'>Creer un bulletin</button> \n\
                        </form>";
                            function myFunction() {
                                //var x = document.getElementById("bulletin").innerText;
                                document.getElementById("formulaire").innerHTML = codeBulletin;
                            }
                        </script>
                    </td>
                    <c:if test="${(!empty mess) && (login eq e.login)}">
                        <td><a href="bulletin/${e.nom}${e.prenom}.pdf">voir fichier</a></td>    
                    </c:if> 
                </tr>
            </c:forEach>
            <!--</tr>-->
        </table>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
