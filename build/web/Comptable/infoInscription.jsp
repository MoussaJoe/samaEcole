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
