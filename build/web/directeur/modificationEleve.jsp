<%-- 
    Document   : modificationEleve
    Created on : 2 août 2018, 23:35:53
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification Eleve</title>
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
        <h1>Modification Eleve :</h1>

        <form action="ControleurDirecteur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="valideModEleve" />
               <!-- <input type="hidden" name="year" value="${year}" />-->
                <tr>
                    <td></td>
                    <td>
                        <div class="form-group">
                            <input type="hidden" name="idLog" value="${eleve.getLogin()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Classe :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control">
                                <c:forEach var="p" items="${classes}">
                                    <c:choose>
                                        <c:when test="${p eq eleve.getNomClasse()}">
                                            <option selected >${p}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>${p}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Nom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nom" value="${eleve.getNom()}" class="form-control" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Prénom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenom" value="${eleve.getPrenom()}" class="form-control" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Adresse :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="adresse" value="${eleve.getAdresse()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Téléphone :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="tel" value="${eleve.getTel()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>date de Naissance :</th>
                    <td>
                        <div class="form-group">
                            <input type="date" name="dateNaissance" value="${eleve.getDateNaissance()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Lieu de Naissance :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="lieuNaissance" value="${eleve.getLieuNaissance()}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <input type="hidden" name="year" value="${year}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success" type="submit">Valider</button>
                    </td>
                </tr>

            </table>

            <% } else {
            %>
            <jsp:forward page="../SeConnecter.jsp"/>
            <% }%> 
    </body>
</html>
