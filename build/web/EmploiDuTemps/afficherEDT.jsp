<<<<<<< HEAD
<%-- 
    Document   : afficherEDT
    Created on : 30 mai 2019, 14:49:47
    Author     : Ouzy NDIAYE
--%>

<!DOCTYPE html>
<html>
    <head>        
        <title>${profils} | Afficher EDT</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="../directeur/barreNavDirecteur.jsp" %>
        <!--////////////////////////////////////////////////////// -->
        <div class="container">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-10">
                    <p class="edp_Classe">Emploi du temps ${classe}</p>
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
                            <c:forEach begin="0" end="3" var="l"> 
                                <c:if test="${l eq '2'}">
                                    <tr>
                                        <th scope="col" style="text-align: center">12h-13h</th>
                                        <td colspan='6' align='center'>Pause</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th scope="col" style="text-align: center">${heure[l]}</th>
                                        <c:forEach begin="0" end="5" var="c">                                        
                                        <td style="text-align: center">
                                            <c:forEach var="e" items="${fn:split(afficherEDT[l][c], '//---//')}">
                                                <span class="edp_Classe1">${e}</span><br>
                                            </c:forEach>                                            
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
    Document   : afficherEDT
    Created on : 30 mai 2019, 14:49:47
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
        <!--////////////////////////////////////////////////////// -->
        <div class="container">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-10">
                    <p class="edp_Classe">Emploi du temps ${classe}</p>
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
                            <c:forEach begin="0" end="3" var="l"> 
                                <c:if test="${l eq '2'}">
                                    <tr>
                                        <th scope="col" style="text-align: center">12h-13h</th>
                                        <td colspan='6' align='center'>Pause</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th scope="col" style="text-align: center">${heure[l]}</th>
                                        <c:forEach begin="0" end="5" var="c">                                        
                                        <td style="text-align: center">
                                            <c:forEach var="e" items="${fn:split(afficherEDT[l][c], '//---//')}">
                                                <span class="edp_Classe1">${e}</span><br>
                                            </c:forEach>                                            
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
