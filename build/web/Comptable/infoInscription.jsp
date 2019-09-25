<<<<<<< HEAD
<%-- 
    Document   : infoInscription
    Created on : 13 mai 2019, 03:56:08
    Author     : Ouzy NDIAYE
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Comptable | Infos inscription</title> 
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavCompta.jsp" %> 
        <div class="col-lg-12">
            <p class="titre">Votre enfant à été bien inscrit dans notre école</p>
            <p class="titre">Information de l'élève</p>
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
                <p class="titre btn-success" >
                    Login : ${loginElv} <br>
                    Mot de passe :${passwordElv}<br>
                </p>
            </div>
            <div class="col-lg-4"></div>
        </div>
        <div class="col-lg-12">
            <c:if test="${!empty loginPar}">
                <p class="titre">Information du parent</p>
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <p class="titre btn-success">
                        Login : ${loginPar} <br>
                        Mot de passe :${passwordPar}<br>
                    </p>
                </div>
            </c:if>
        </div>
        <div class="col-lg-12">
            <p class="titre" style="color: red;">
                Rapprochez vous du directeur des études pour valider votre inscription et 
                songez à changer vos paramètres de compte pour plus de sécurité</p>

        </div>
        <div class="col-lg-12">
            <style media="print">
                #pasaffiche{
                    visibility: hidden;
                }
            </style>
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
                <a style="color: blue;font-size: 25px; font-weight: bold" id="pasaffiche" href="javascript:print()">
                    Cliquez ici pour imprimer
                </a>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
=======
<%-- 
    Document   : infoInscription
    Created on : 13 mai 2019, 03:56:08
    Author     : Ouzy NDIAYE
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Acceuil</title> 
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavCompta.jsp" %> 
        <div class="col-lg-3"></div>
        <div class="col-lg-9">
            <p style="text-align: center;font-size: 20px">Votre enfant à été bien inscrit dans notre école</p>
            <p style="text-align: center;font-size: 20px">Information de l'élève</p>
            <p style="text-align: center;font-size: 25px">
                Login : ${loginElv} <br>
                Mot de passe :${passwordElv}<br>
            </p>
            <c:if test="${!empty loginPar}">
                <p style="text-align: center;font-size: 20px">Information du parent</p>
                <p style="text-align: center;font-size: 25px">
                    Login : ${loginPar} <br>
                    Mot de passe :${passwordPar}<br>
                </p>
            </c:if>
            <h2>Rapprochez vous du directeur des études pour valider votre inscription et 
                Songez à changer vos paramètres de compte pour plus de sécurité</h2>

        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
