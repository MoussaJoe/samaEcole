<%-- 
    Document   : modificationAbsence
    Created on : 9 sept. 2019, 03:51:35
    Author     : Moussa Joseph D Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Modifier Absence</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {
                // String profils = (String) session.getAttribute("profils");
        %>

        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Modifier absence</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Surveillant" method="Post">
                            <input type="hidden" name="action" value="validModAbs">
                            <input type="hidden" name="annee" value="${anInscr}">
                            <input type="hidden" name="login" value="${login}">
                            <input type="hidden" name="matiere" value="${matiere}">
                            <input type="hidden" name="semestre" value="${semestre}">


                            <div class="form-group">
                                <label>Absence</label>
                                <input type="number" name="abs" value="${abs}" class="form-control" class="form-control">
                            </div>                                

                            <div class="form-group">
                                <label>Retard</label>
                                <input type="number" name="ret" value="${ret}" class="form-control">
                            </div>                                

                            <div>
                                <button class="btn btn-success btn-block" onclick="confirmation()" type="submit">Valider</button>
                            </div>                                
                        </form>
                    </div>
                </div>
            </div>
        </div>

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
