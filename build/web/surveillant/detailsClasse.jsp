<%-- 
    Document   : detailsClasse
    Created on : 17 juin 2019, 15:37:43
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
        <title>Détails Classe </title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %> 
        <h1>Les Classes de l'établissement</h1>

        <table class="table-bordered table-hover" id="tabc" style="margin-left: 47vw; border: #ffffff">

            <tbody>
                <tr>
                    <td id="mod1" hidden="hidden">
                        <form action="Directeur" method="POST">
                            <input type="hidden" name="action" value="modifierNomClasse">
                            <input type="hidden" name="regime" value="${classe.regime}">
                            <input type="hidden" name="oldNomClasse" value="${classe.nomClasse}">
                            <input type="text" name="nomClasseUpdate" value="${classe.nomClasse}" placeholder="modifier nom Classe" class="form-control" required="">
                            <input type="submit" name="Valider" value="Valider"  class="btn btn-success">
                        </form>
                    </td>

                    <td>Nom Classe :</td>
                    <td>${classe.nomClasse}</td> 
                    <td>
                        <button id="modNom" onclick="UpdateNomClasse()" class="form-control" name="modNom" onmouseup="">
                            <img src="modifier.png" style="width: 2vw;" >
                        </button>
                    </td>
                    <td id="demo1" ></td>
                </tr>
                <tr>
                    <td id="mod2" hidden="hidden">
                        <form action="Directeur" method="POST">
                            <input type="hidden" name="action" value="modifierRegime">
                            <input type="hidden" name="oldregime" value="${classe.regime}">
                            <input type="hidden" name="nomClasse" value="${classe.nomClasse}">
                            <select name="regimeUpdate" class="form-control" required="">
                                <c:choose>
                                    <c:when test="${classe.regime == 'Privée'}">
                                        <option selected="">Privée</option>
                                        <option>Public</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>Privée</option>
                                        <option selected="">Public</option>
                                    </c:otherwise>

                                </c:choose>

                            </select>
                            <input type="submit" name="Valider" value="Valider"  class="btn btn-success">
                        </form>
                    </td>
                    <td>Régime :</td>
                    <td>${classe.regime}</td> 
                    <td><button id="modNom" onclick="UpdateRegime()" class="form-control" name="modNom"><img src="modifier.png" style="width: 2vw;" ></button></td>
                    <td id="demo2" ></td>
                </tr>

            </tbody>
        </table>
        <br>
        <br>
        <table class="table-bordered table-hover" id="tabc" style="margin-left: 47vw; border: #ffffff">

            <tbody>
                <tr>
                    <td colspan="2" id="entete">Matieres :</td>

                </tr>
                <c:forEach var="cm" items="${classe.matieres}">
                    <tr>
                        <td>${cm}</td> 
                        <td><a href="Directeur?action=supprimerMatCl&&nomcl=${classe.nomClasse}&&reg=${classe.regime}&&mat=${cm}"><span class="glyphicon glyphicon-remove"></span></a></td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  

        <script>
            function UpdateNomClasse() {

                mod1 = document.getElementById("mod1").innerHTML;

                document.getElementById("demo1").innerHTML = mod1;
            }


            function UpdateRegime() {

                mod2 = document.getElementById("mod2").innerHTML;

                document.getElementById("demo2").innerHTML = mod2;
            }

        </script>
    </body>
</html>

