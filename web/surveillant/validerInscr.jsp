<%-- 
    Document   : validerInscr
    Created on : 4 sept. 2019, 03:09:54
    Author     : Moussa Joseph D Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Valider l'inscription de l'élève</title>
    </head>
    <body>
        <%        
            if (session.getAttribute("log") != null) {
        %>
        <%@include file="surveillant.jsp" %>
        <h1>Valider l'inscription de l'élève</h1>
        <form action="Surveillant" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="inscrit" />
                <tr>
                    <th>Login ou matricule :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="login" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success">Valider</button>
                    </td>
                </tr>
            </table>
        </form>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>

