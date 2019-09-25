<%-- 
    Document   : annee
    Created on : 15 nov. 2018, 22:51:43
    Author     : Moussa Joseph Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Ann�e-Scolaire</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>

        <script>
            <c:if test="${!empty msg}">
            alert("Veuillez revoir l'ann�e Scolaire saisi!");
            </c:if>
        </script>
        <script>
            <c:if test="${!empty er}">
             alert("Veuillez Changer d'ann�e; celle-ci a �t� d�j� ajout�e!");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p class="titre">Liste des ann�es-scolaire</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">                                                                        
                        <table class="table table-bordered table-hover table-responsive">
                            <tr>
                                <th id="entete">Ann�e Scolaire</th>
                            </tr>
                            <c:forEach var="p" items="${annees}">
                                <tr>
                                    <td>${p}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                
                
                <div class="col-lg-12">
                    <p class="titre">Ajouter une nouvelle ann�e-scolaire</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="Post">
                            <input type="hidden" name="action" value="ajoutAnnee">

                            <div class="form-group">
                                <label>Ann�e Scolaire</label>
                                <input type="text" name="nv-annee" placeholder="format 2018-2019" maxlength="9" onkeypress=" return event.charCode >= 48 && event.charCode <= 57 || event.charCode === 45" class="form-control" required="">
                            </div>

                            <div class="form-group">
                                <button class="btn btn-success btn-block " type="submit">Ajouter</button>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
