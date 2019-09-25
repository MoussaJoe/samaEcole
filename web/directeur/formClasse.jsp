<%-- 
    Document   : formClasse
    Created on : 9 sept. 2018, 16:38:57
    Author     : Moussa Joseph Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Enregistrer Classe</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <script>
            <c:if test="${!empty messageClasse}">
                alert("La claase à été créer avec succès");
            </c:if>
            <c:if test="${!empty messageErreur}">
                alert("Une erreur s'est produite lors de l'enregistrement de la classe");
            </c:if>                    
            </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Enregistrer Classe</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="POST">
                            <input type="hidden" name="action" value="validerClasse" />

                            <div class="form-group">
                                <label>Nom Classe</label>
                                <input type="text" name="nomClasse" class="form-control" required=""/>
                            </div>

                            <div class="form-group">
                                <label>Régime</label>
                                <select name="regime" class="form-control" required/>
                                <option>Privée</option>
                                <option>Public</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Matières</label>
                                <select name="nomMatiere" multiple="multiple" class="form-control" required="">
                                    <c:forEach var="m" items="${matieres}">
                                        <option>${m}</option> 
                                    </c:forEach>
                                </select>
                            </div>

                            <div>
                                <button class="btn btn-success btn-block" type="submit">Valider</button>
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
    </body>
</html>
