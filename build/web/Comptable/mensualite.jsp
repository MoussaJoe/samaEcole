<%-- 
    Document   : mensualite
    Created on : 20 mai 2019, 21:43:10
    Author     : Ouzy NDIAYE
--%>

<%            if (session.getAttribute("log") != null) {

%>
<%@include file="barreNavCompta.jsp" %> 
<div class="container">
    <div class="row">
        <!--///////////Liste Classe Privee/////////////// -->
        <c:if test="${(! empty classePrivee) && (empty eleve)}">
            <script>
                <c:if test="${! empty payementReussit}">
                alert("Payement effectué avec success");
                </c:if>
            </script>
            <div class="col-lg-6">
                <table id="tab2" class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th scope="col" style="text-align: center">Nom classe</th>
                            <th scope="col" style="text-align: center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach var="cl" items="${classePrivee}">
                                <td>
                                    ${cl} 
                                </td>

                                <td>
                                    <a href="Comptable?connect=choixClasse&nomClasse=${cl}">
                                        <button class="btn btn-success btn-block">Voir classe</button>
                                    </a>
                                </td>
                            </c:forEach>                            
                        </tr>
                    </tbody>

                </table>
            </div>
        </c:if>
    </div>
</div>
<!--///////////Fin Liste Classe Privee/////////////// -->
<!--///////////Liste des eleves de la classe X/////////////// -->
<div class="container">
    <div class="row">
        <c:if test="${! empty eleve}">
            <form method="POST" action="Comptable">
                <input type="hidden" name="connect" value="payerMensuel"/>
                <div class="col-lg-12">
                    <h2 style="text-align: center">Liste des élèves de la classe de ${nomClasse}</h2>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th scope="col" style="text-align: center">Prénom</th>
                                <th scope="col" style="text-align: center">Nom</th>
                                <th scope="col" style="text-align: center">Date de Naissance</th>
                                <th scope="col" style="text-align: center">Lieu de Naissance</th>
                                <th scope="col" style="text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="cl" items="${eleve}">
                                <tr>
                                    <td>${cl.prenom}</td>                                    
                                    <td>${cl.nom}</td>
                                    <td>${cl.dateNaissance}</td>
                                    <td>${cl.lieuNaissance}</td>
                            <input type="hidden" name="login" value="${cl.login}"/>
                            <input type="hidden" name="nomClasse" value="${nomClasse}"/>                            
                            <td><button class="btn btn-success btn-block" type="submit">Voir élève</button></td>
                            </tr>
                        </c:forEach>                            

                        </tbody>

                    </table>
                </div>
            </form>
        </c:if>

    </div>
</div>
<!--///////////Fin Liste des eleves de la classe X/////////////// -->
<% } else {
%>
<jsp:forward page="vue/SeConnecter.jsp"/>                                                    
<% }%>
</body>
</html>
