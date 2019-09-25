<%-- 
    Document   : demandeMatClasse
    Created on : 17 juil. 2018, 09:37:24
    Author     : ibrah
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Professeur</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavProf.jsp" %>
        <script>
            <c:if test="${!empty message}">
            alert("Veuillez vérifier les informations saisies");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12"> 
                    <p class="titre">Veuillez renseigner la classe et la matière concernées</p>        
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="ControleurProf" method="POST">
                            <input type="hidden" name="action" value="demandeNoteDevoir" />
                            <input type="hidden" name="login" value="${login}" />

                            <div class="form-group">
                                <label>Nom classe</label>
                                <select name="nomClasse" class="form-control">
                                    <c:forEach var="i" items="${listClasse2}">
                                        <option value="${i.nomClasse}///${i.regime}">${i.nomClasse} (${i.regime})</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Matière</label>
                                <select name="matiere" class="form-control">
                                    <c:forEach var="i" items="${listMatiere2}">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Année Scolaire</label>
                                <select name="annee" class="form-control">
                                    <c:forEach var="i" items="${listAnnee2}">
                                        <option>${i}</option>
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
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
