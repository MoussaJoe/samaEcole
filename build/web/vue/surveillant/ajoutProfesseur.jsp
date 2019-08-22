<%-- 
    Document   : ajoutProfesseur
    Created on : 14 juil. 2018, 14:03:05
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout Professeur</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="../../surveillant.jsp" %>
        <script>
            <c:if test="${!empty msg}">
            alert("Professeur enregistré avec succés");
            </c:if>
        </script>
        <h1>Formulaire d'ajout de Professeur :</h1>
        <c:if test="${!empty msg}">
            <h2>Login:&nbsp;&nbsp;${loginProfesseur}</h2>
            <h2>Mot de passe:&nbsp;&nbsp;${mdpProfesseur}</h2>
        </c:if>
        <form action="ControleurDirecteur" method="Post">
            <table id="tab">
                <input type="hidden" name="action" value="formProf"/>
                <tr>
                    <th>Nom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nom" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Prénom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenom" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Adresse :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="adresse" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Téléphone :</th>
                    <td>
                        <div class="form-group">
                            <div class="form-inline">
                                <select name="tel1" class="form-control" required="">                                
                                    <option>30</option>
                                    <option>33</option>
                                    <option>70</option>
                                    <option>76</option>
                                    <option>77</option>
                                    <option>78</option>
                                </select> 
                                <input type="text" name="tel2" value="" maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control" required=""/>
                            </div>
                        </div>
                    </td>
                </tr>

                <tr>
                    <th>Classes :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" multiple="multiple" class="form-control" required="">
                                <c:forEach var="p" items="${classes}">
                                    <option>${p}</option>       
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Matières :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomMatiere" multiple="multiple" class="form-control" required="">
                                <c:forEach var="m" items="${matieres}">
                                    <option>${m}</option> 
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    
                    <td>
                        <div class="form-group">
                            <input type="hidden" name="annee" value="${anInscr}" class="form-control"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><button class="btn btn-success" type="submit">Valider</button></td>
                </tr>
            </table>
        </form>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 

    </body>
</html>
