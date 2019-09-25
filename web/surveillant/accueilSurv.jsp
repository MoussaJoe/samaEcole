<%-- 
    Document   : accueilDirecteur
    Created on : 10 oct. 2018, 18:17:05
    Author     : ibrah
--%>

<!DOCTYPE html>

<head>
    <title>${profils} | Accueil</title>
</head>
<body>
    <%            if (session.getAttribute("log") != null) {

    %> 
    <%@include file="barreNavSurveillant.jsp" %>
    <script>
        <c:if test="${!empty msg1}">
        alert("Photo de profil modifier avec success");
        </c:if>

        <c:if test="${!empty msg2}">
        alert("Photo de profil supprimer avec success");
        </c:if>
    </script>

    <% } else {
    %>
    <jsp:forward page="../SeConnecter.jsp"/>
    <% }%>  
</body>
</html>