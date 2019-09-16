<%-- 
    Document   : updateMatiere
    Created on : 20 août 2019, 00:14:39
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
           <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
        <h1>Modification Matière!</h1>
        
        <form action="Directeur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="validerMatiereMod" />
                 <input type="hidden" name="oldmatiere" value="${nomMatiere}" />
                <tr>
                    <th>Nom Matière :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nomMatiere" value="${nomMatiere}" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-success">Valider</button>
                    </td>
                </tr>
            </table>
        </form>
        
         </table>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
