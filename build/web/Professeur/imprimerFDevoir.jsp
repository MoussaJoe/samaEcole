<%-- 
    Document   : demandeMatClasse
    Created on : 17 juil. 2018, 09:37:24
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Professeur | Fiche de devoir</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavProf.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p class="titre">Veuillez renseigner la classe concernées</p>

                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="ControleurProf" method="POST">
                            <input type="hidden" name="action" value="impressionDevoir" />

                            <div class="form-group">
                                <label>Classe</label>
                                <select name="nomClasse" class="form-control">
                                    <c:forEach var="i" items="${listClasse2}">
                                        <option value="${i.nomClasse}///${i.regime}">${i.nomClasse} (${i.regime})</option>
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
                            </div><br>                        
                            <c:if test="${!empty message}">
                                <div>
                                    <button class="btn btn-success btn-block" onclick="popup()">Cliquer pour imprimer le fichier</button>
                                </div>
                            </c:if>
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

    <SCRIPT LANGUAGE="JavaScript">
        function popup() {
            w = open("Fiche/fiche_devoir.pdf", 'popup', 'width=600,height=900,toolbar=no,scrollbars=no,resizable=yes');
        }
    </SCRIPT>
</html>
