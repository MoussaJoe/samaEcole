<%-- 
    Document   : reinscrireEleve
    Created on : 15 mai 2019, 22:46:30
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
        <script>        
        <!-- Message réinscription réussit date-->
            <c:if test="${!empty msgReussiReinscrip}">
                alert("L'élève a été réinscrit dans la classe et pourra se connecter dès que son compte sera validé par le Directeur des Etudes");
            </c:if>
            <c:if test="${!empty erreurReinscrip}">
                alert("l'inscription a échoué!!!");
            </c:if>
            <c:if test="${!empty msgErreurReinscrip}">
                alert("L'élève s'est déjà inscrit dans cette année");
            </c:if>
            <c:if test="${!empty msgErreurLoginElv}">
                alert("Le login de l'élève est invalide");
            </c:if>
            <c:if test="${!empty msgErreurMontant}">
            alert("Veuillez vérifier le montant saisi");
            </c:if>
                <!--Message erreur Régime -->
            <c:if test="${!empty msgErreurRegime}">
            alert("Veuillez choisir un régime");
            </c:if>
        </script>
         <!--///////////////////////////////////// -->
        <h1>Inscription élève</h1>
        <form action="Comptable" method="Post">
            <input type="hidden" name="connect" value="valider-reinscription" />
            <div class="col-lg-3"></div>
            <div class="col-lg-12">
                <p style="text-align: center; font-size: 20px;">Informations de l'élève</p>
            </div>
            <div class="col-lg-4"></div>
            <div class="col-lg-4">                
                <div class="form-group">
                    <label for="login">Login de l'élève</label>
                    <input id="login" type="text" name="loginElv" class="form-control" placeholder="Saisir le login de l'élève" required>
                </div>
                <!--////////////////////////////////////// -->
                  <div class="form-group">
                    <label>Sélectionner un régime</label>
                    <select id="regime" onchange="myFunction()" class="form-control" name="regime">
                        <option value="">Sélectionnez un régime</option>
                        <option value="Privee">Privée</option>
                        <option value="Public">Public</option>
                    </select>
                    <p id="demo" ></p>
                    <script>                        
                        var codePrivee = "\n\
                    <div class='form-group'>\n\
                        <label>Nom Clasee</label>\n\
                        <select class='form-control' name='nomClassePriv'>\n\
                            <c:forEach var='e2' items='${classePrivee}'>\n\n\
                                <option value='${e2}'>${e2}</option>\n\
                            </c:forEach>\n\n\
                        </select>\n\
                    </div>";
                    </script>
                    <script>
                        var codePublic = "\n\
                    <div class='form-group'>\n\
                        <label>Nom Clasee</label>\n\
                        <select class='form-control' name='nomClassePub'>\n\
                            <c:forEach var='e2' items='${classePublic}'>\n\n\
                                <option value='${e2}'>${e2}</option>\n\
                            </c:forEach>\n\n\
                        </select>\n\
                    </div>";
                    </script>
                    <script>
                        function myFunction() {
                            var choix = document.getElementById("regime").value;
                            if (choix == "Privee") 
                                document.getElementById("demo").innerHTML = codePrivee;
                            
                            if (choix == "Public") 
                                document.getElementById("demo").innerHTML = codePublic;
                            
                        }                                            
                    </script>
                </div>
                <!--////////////////////////////////////// -->
                <div class="form-group">
                    <label for="montantInsc">Montant Inscription</label>
                    <input id="prenom" type="text" name="montantInsc" class="form-control" placeholder="Saisir le montant"
                           maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" required>
                </div>
                <button class="btn btn-success btn-block" type="submit">Réinscrire l'élève</button>
            </div>
            
        </form>
        
        
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>