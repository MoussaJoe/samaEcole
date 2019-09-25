<<<<<<< HEAD:web/surveillant/reinscription.jsp
<%-- 
    Document   : reinscription
    Created on : 2 nov. 2018, 18:26:19
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réinscription</title>
    </head>
     <body>
        <%
            if (session.getAttribute("log") != null) {
               
        %>
         <%@include file="surveillant.jsp" %>
        <script>
            <c:if test="${!empty message1}">
            alert("Réinscription réussie !!!");
            </c:if>
            <c:if test="${!empty message2}">
            alert("La Réinscription a échoué !!!");
            </c:if>
                <c:if test="${!empty message3}">
            alert("Le login ne correspond à aucun éléve!!");
            </c:if>
        </script>
        <%--  <h1>Formulaire d'Enregistrement d'élève :</h1> --%>
        <table id="tab" class="table table-bordered">
            <tr>
              
                <td class="test" style="background-color: grey;color: white">
                    <a href="Surveillant?action=reinscription" style="color: white">RéInscription</a>
                </td>
        </table>
        <form action="Surveillant" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="reinscription-form" />
                 <tr>
                    <th>Login :</th>
                    <td>
                        <div class="form-group">
                        <input type="text" name="login" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Classe :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control" required="">
                            <c:forEach var="p" items="${classes}">
                                <option>${p.nomClasse}</option>
                            </c:forEach>

                        </select>
                        </div></td>
                </tr>
                <tr>
                    <th>Année Scolaire :</th>
                    <td>
                        <div class="form-group">
                            <select name="annee" class="form-control" required="">
                                <c:forEach var="a" items="${annees}">
                                    <option>${a}</option> 
                                </c:forEach>
                            </select>
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
=======
<%-- 
    Document   : reinscription
    Created on : 2 nov. 2018, 18:26:19
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réinscription</title>
    </head>
     <body>
        <%
            if (session.getAttribute("log") != null) {
               
        %>
         <%@include file="../../surveillant.jsp" %>
        <script>
            <c:if test="${!empty message1}">
            alert("Réinscription réussie !!!");
            </c:if>
            <c:if test="${!empty message2}">
            alert("La Réinscription a échoué !!!");
            </c:if>
                <c:if test="${!empty message3}">
            alert("Le login ne correspond à aucun éléve!!");
            </c:if>
        </script>
        <%--  <h1>Formulaire d'Enregistrement d'élève :</h1> --%>
        <table id="tab" class="table table-bordered">
            <tr>
              
                <td class="test" style="background-color: grey;color: white">
                    <a href="ControleurDirecteur?action=reinscription" style="color: white">RéInscription</a>
                </td>
        </table>
        <form action="ControleurDirecteur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="reinscription-form" />
                 <tr>
                    <th>Login :</th>
                    <td>
                        <div class="form-group">
                        <input type="text" name="login" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Classe :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control" required="">
                            <c:forEach var="p" items="${classes}">
                                <option>${p}</option>
                            </c:forEach>

                        </select>
                        </div></td>
                </tr>
                <tr>
                    <th>Année Scolaire :</th>
                    <td>
                        <div class="form-group">
                            <select name="annee" class="form-control" required="">
                                <c:forEach var="a" items="${annees}">
                                    <option>${a}</option> 
                                </c:forEach>
                            </select>
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
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403:web/vue/surveillant/reinscription.jsp
