<%-- 
    Document   : formAbsence
    Created on : 7 sept. 2019, 02:08:48
    Author     : Moussa Joseph D Sarr
--%>

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


                <input type="hidden" name="action" value="saveAbsence">
                <input type="hidden" name="annee" value="${anInscr}">
                <tr>
                    <th>Login :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="login" value="" required="" class="form-control">
                        </div>
                    </td>
                </tr>

                <tr>
                    <th>Matière :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomMatiere" class="form-control" required="">
                                <c:forEach var="m" items="${matieres}">
                                    <option>${m}</option> 
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
                    <th> Absence ou retard :</th>
                    <td>
                        <div class="form-group">
                            <select name="sujet" class="form-control" required="">                         
                                <option>Retard</option> 
                                <option id="modNom" onselect="Absencedd()">Absence</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th> durée d'Absence :</th>
                    <td>
                        <div class="form-group">
                            <input type="number" name="absence" value="0" class="form-control">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <th>
                        <button class="btn btn-success">Valider</button>
                        &nbsp;
                        <a href="Surveillant?action=listeAbsence" class="btn btn-primary">Lister les absences</a>
                    </th>
                </tr>
                <div id="demo3"> </div>

                <script>
                    function Absencedd() {
                        mod = document.getElementById("mod3").innerHTML;

                        document.getElementById("demo3").innerHTML = mod;
                    }
                </script>
            </table>
        </form>

        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 




    </body>
</html>
