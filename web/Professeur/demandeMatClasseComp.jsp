<%-- 
    Document   : demandeMatClasse
    Created on : 17 juil. 2018, 09:37:24
    Author     : ibrah
--%>
<!DOCTYPE html>
<html>
    <head>
        <title>Professeur | Infos classe</title>
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
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <p class="titre">Informations de la classe</p>
                    </div>
                    <div class="col-lg-3"></div>
                </div>
                <div class="col-lg-12">
                    <div class="col-lg-4"></div>
                    <%-- <h2 align="center">${message}</h2> --%>
                    <div class="col-lg-4">
                        <form action="ControleurProf" method="POST">
                            <input type="hidden" name="action" value="demandeMatClasseComp" />
                            <input type="hidden" name="login" value="${login}" />                            
                            <div class="form-group">
                                <label for="sexe">Classe</label>
                                <select class="form-control" name="nomClasse">
                                    <c:forEach var="i" items="${listClasse}">
                                        <option value="${i.nomClasse}///${i.regime}">${i.nomClasse} (${i.regime})</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="sexe">Matière</label>
                                <select class="form-control" name="matiere">
                                    <c:forEach var="i" items="${listMatiere}">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="sexe">Semestre</label>
                                <select id="sexe" class="form-control" name="semestre">
                                    <option value="1er_semestre">1ère Semestre</option>
                                    <option value="2eme_semestre">2nde Semestre</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="sexe">Année Scolaire</label>
                                <select class="form-control" name="annee">
                                    <c:forEach var="i" items="${listAnnee}">
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
