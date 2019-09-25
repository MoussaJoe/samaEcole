<%-- 
    Document   : formMatiere
    Created on : 9 sept. 2018, 15:25:17
    Author     : Moussa Joseph Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Ajouter Mati�re</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <script>
            <c:if test="${!empty message}">
            alert("�chec de l'ajout de la mati�re!");
            </c:if>
            <c:if test="${!empty msgExiste}">
                alert("La mati�re que vous essayer d'ajouter existe d�j�")
            </c:if>
            <c:if test="${!empty mes}">
            alert("Mati�re ajout�e avec succ�s!!");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Enregistrer une nouvelle mati�re</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="POST">
                            <input type="hidden" name="action" value="validerMatiere" />

                            <div class="form-group">
                                <label>Nom de la mati�re</label>
                                <input type="text" name="nomMatiere" value="" class="form-control" required=""/>
                            </div>
                            <div>
                                <button class="btn btn-success btn-block" type="submit">Valider</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div> 

        <br><br>
        <c:if test="${!empty matieres}">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">                    
                        <p class="titre">Liste des mati�res</p>
                        <table class="table table-bordered table-hover table-responsive">
                            <thead>
                                <tr>
                                    <th id="entete">Nom Mati�re</th>                                
                                    <th id="entete">Supprimer</th>
                                    <th id="entete">Modifier</th>

                                </tr>
                            </thead>

                            <tbody>                
                                <c:forEach var="cm" items="${matieres}">
                                    <tr>
                                        <td>${cm}</td> 
                                        <td><a href="Directeur?matiere=${cm}&&action=supprimerMatiere" onclick="javascript: return confirmation();"><span class="glyphicon glyphicon-remove"></span></a></td>
                                        <td><a href="Directeur?matiere=${cm}&&action=modifierMatiere" ><img src="modifier.png" style="height: 25px"/></a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
<script>
    function confirmation() {
        var code = "Voulez vous vraiment supprimer cette mati�re ?\
    ";
        var msg = confirm(code);
        if (msg) {
            return true;
        } else {
            return false;
        }
    }
</script>