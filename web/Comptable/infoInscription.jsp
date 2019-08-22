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
            <p style="text-align: center;font-size: 20px">Votre enfant � �t� bien inscrit dans notre �cole</p>
            <p style="text-align: center;font-size: 20px">Information de l'�l�ve</p>
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
            <h2>Rapprochez vous du directeur des �tudes pour valider votre inscription et 
                Songez � changer vos param�tres de compte pour plus de s�curit�</h2>

        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
