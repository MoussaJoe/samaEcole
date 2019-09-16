<%-- 
    Document   : modificationAbsence
    Created on : 9 sept. 2019, 03:51:35
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
        <%            if (session.getAttribute("log") != null) {
                // String profils = (String) session.getAttribute("profils");
        %>

        <%@include file="surveillant.jsp" %>
        <h1>Formulaire pour renseigner les absences :</h1>


        <form action="Surveillant" method="Post">
            <table id="tab">


                <input type="hidden" name="action" value="validModAbs">
                <input type="hidden" name="annee" value="${anInscr}">
                <input type="hidden" name="login" value="${login}">
                
                <tr>
                    <th></th>
                    <td>
                        <div class="form-group">
                            <input type="hidden" name="matiere" value="${matiere}" class="form-control">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <div class="form-group">
                            <input type="hidden" name="semestre" value="${semestre}" class="form-control">
                        </div>
                    </td>
                </tr>

                <tr>
                    <th>Absence :</th>
                    <td>
                        <div class="form-group">
                            <input type="number" name="abs" value="${abs}" class="form-control" class="form-control">
                        </div>
                    </td>
                </tr>

                <tr>
                    <th>retard :</th>
                    <td>
                        <div class="form-group">
                            <input type="number" name="ret" value="${ret}" class="form-control">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <th>
                        <button class="btn btn-success" onclick="confirmation()">Valider</button>

                    </th>
                </tr>

            </table>
        </form>

        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 


<script type="text/javascript">
    function confirmation() {
        var msg = confirm("es-tu sur de vouloir modifer?");
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
</script>

    </body>
</html>
