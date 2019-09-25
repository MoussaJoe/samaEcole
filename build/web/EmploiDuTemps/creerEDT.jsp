<<<<<<< HEAD
<%-- 
    Document   : creerEDT
    Created on : 30 mai 2019, 15:51:38
    Author     : Ouzy NDIAYE
--%>

<!DOCTYPE html>
<html>
    <head>        
        <title>${profils} | Cr�er EDT</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="../directeur/barreNavDirecteur.jsp" %>
        <script>
            <c:if test="${! empty msgInsertReussit}">
            alert("Le cours a �t� cr�er avec succ�s");
            </c:if>
        </script>
        <!--////////////////////////////////////////////////////// -->
        <div class="container">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <p class="edp_Classe">Emploi du temps ${classe} Ann�e-Scolaire ${annee}</p>
                    <c:if test="${!empty noDispo}">
                        <div class="alert alert-danger">
                            <p class="erreur_saisi">
                                Le jour et la date choisi est d�j� occup�!!!
                            </p>
                        </div>
                    </c:if>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col" style="text-align: center">Lundi</th>
                                <th scope="col" style="text-align: center">Mardi</th>
                                <th scope="col" style="text-align: center">Mercredi</th>
                                <th scope="col" style="text-align: center">Jeudi</th>
                                <th scope="col" style="text-align: center">Vendredi</th>
                                <th scope="col" style="text-align: center">Samedi</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="hr" begin="0" end="3">
                                <c:if test="${hr eq '2'}">
                                    <tr>
                                        <th scope="col" style="text-align: center">12h-13h</th>
                                        <td colspan='6' align='center'>Pause</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th scope="col" style="text-align: center">${heure[hr]}</th>
                                        <c:forEach begin="0" end="5" var="j">                                        
                                                <td style="text-align: center">
                                                <a href="EDT?connect=ajouterCours&nomClasse=${classe}&heure=${heure[hr]}&jour=${j}&regime=${regime}">
                                                    <img src="modifier.png" width="25px" alt="Ajouter"/>
                                                </a>
                                                </td>

                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>                                                        
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <!--////////////////////////////////////////////////////// -->
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
=======
<%-- 
    Document   : creerEDT
    Created on : 30 mai 2019, 15:51:38
    Author     : Ouzy NDIAYE
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta  charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="dist/css/mystyle.css" />
        <link rel="stylesheet" type="text/css" href="Style/style1.css">
        <title>Acceuil</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavEDT.jsp" %>
        <script>
            <c:if test="${! empty msgInsertReussit}">
            alert("Le cours a été créer avec succès");
            </c:if>
        </script>
        <!--////////////////////////////////////////////////////// -->
        <div class="container">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <p class="edp_Classe">Emploi du temps ${classe}</p>
                    <c:if test="${!empty noDispo}">
                        <div class="alert alert-danger">
                            <p class="erreur_saisi">
                                Le jour et la date choisi est déjà occupé!!!
                            </p>
                        </div>
                    </c:if>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col" style="text-align: center">Lundi</th>
                                <th scope="col" style="text-align: center">Mardi</th>
                                <th scope="col" style="text-align: center">Mercredi</th>
                                <th scope="col" style="text-align: center">Jeudi</th>
                                <th scope="col" style="text-align: center">Vendredi</th>
                                <th scope="col" style="text-align: center">Samedi</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="hr" begin="0" end="3">
                                <c:if test="${hr eq '2'}">
                                    <tr>
                                        <th scope="col" style="text-align: center">12h-13h</th>
                                        <td colspan='6' align='center'>Pause</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th scope="col" style="text-align: center">${heure[hr]}</th>
                                        <c:forEach begin="0" end="5" var="j">                                        
                                                <td style="text-align: center">
                                                <a href="EDT?connect=ajouterCours&nomClasse=${classe}&heure=${heure[hr]}&jour=${j}&regime=${regime}">
                                                    <img src="modifier.png" width="25px" alt="Ajouter"/>
                                                </a>
                                                </td>

                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>                                                        
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <!--////////////////////////////////////////////////////// -->
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
