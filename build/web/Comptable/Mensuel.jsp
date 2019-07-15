<%-- 
    Document   : Mensuel
    Created on : 20 mai 2019, 22:55:42
    Author     : Ouzy NDIAYE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%@include file="barreNavCompta.jsp" %>         
        <!--///////////////////////////////////// -->
            <div class="col-lg-3"></div>
                <div class="col-lg-9">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th scope="col" style="text-align: center">Mois</th>
                                <th scope="col" style="text-align: center">Mensualité</th>
                                <th scope="col" style="text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="m" items="${listerMois}">
                                <tr>                                                                   
                                    <td>
                                        ${m.mois}
                                        <input type="hidden" name="mois" value="${m.mois}"/>
                                    </td> 
                                    <td>${montant}</td>                             
                            <c:if test="${m.statutMensuel eq '0'}">
                                <td>
                                    <a class="btn btn-success btn-block" href="Comptable?connect=payer&login=${login}&nomClasse=${nomClasse}&mois=${m.mois}&montant=${montant}">
                                        Payer
                                    </a>
                                   </td>
                            </c:if>

                            <c:if test="${(m.statutMensuel eq '1') and (m.reliquat eq '0')}">
                                <td>                                    
                                        <a class="btn btn-success btn-block">En règle</a>
                                </td>                            
                            </c:if>
                                
                            <c:if test="${(m.statutMensuel eq '1') and (m.reliquat ne '0')}">
                                <td>                                    
                                    <a class="btn btn-success btn-block" href="Comptable?connect=resteApayer&login=${login}&nomClasse=${nomClasse}&mois=${m.mois}&reliquat=${m.reliquat}">
                                        Reste à payer</a>
                                </td>                            
                            </c:if>

                            </tr>
                        </c:forEach>                            

                        </tbody>

                    </table>
                </div>
        <!--///////////Fin/////////////// -->
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>                                                    
        <% }%>
    </body>
</html>
