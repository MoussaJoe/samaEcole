<%-- 
    Document   : form-listeAbsence
    Created on : 8 sept. 2019, 15:28:13
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
                // String profils = (String) session.getAttribute("profils");
        %>

        <%@include file="surveillant.jsp" %>
        <h1>Formulaire pour renseigner les absences :</h1>


        <form action="Surveillant" method="Post">
            <table id="tab">


                <input type="hidden" name="action" value="absents">
                <input type="hidden" name="annee" value="${anInscr}">
                <tr>
                    <th>NomClasse :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control" required="">
                                <c:forEach var="p" items="${classes}">
                                    <option>${p.nomClasse}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>

                <tr>
                    <th>Régime :</th>
                    <td>
                        <div class="form-group">
                            <select name="regime" class="form-control" required="">
                                <c:forEach var="r" items="${regimes}">
                                    <option>${r}</option>       
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>

                <tr>
                    <th>Semestre :</th>
                    <td>
                        <div class="form-group">
                            <select name="semestre" class="form-control" required="">                         
                                <option>1er_semestre</option> 
                                <option>2eme_semestre</option>
                            </select>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <th>
                        <button class="btn btn-success">Valider</button>

                    </th>
                </tr>

            </table>
        </form>

        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 




    </body>
</html>
