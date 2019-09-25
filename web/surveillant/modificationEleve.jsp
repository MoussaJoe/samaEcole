<%-- 
    Document   : modificationEleve
    Created on : 2 août 2018, 23:35:53
    Author     : Moussa Joseph Sarr
--%>

<%@page import="model.Eleve"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Modifier Elève</title>
    </head>
    <body>

        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <p class="titre">Modifier informations de l'élève</p>
                <div class="col-lg-2"></div>
                <form action="Surveillant" method="POST">
                    <table>
                        <input type="hidden" name="action" value="valideModEleve" />
                        <input type="hidden" name="idLog" value="${eleve.getLogin()}"/> 
                        <input type="hidden" name="year" value="${eleve.annee}"/>

                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Nom Classe</label>
                                <select name="nomClasse" class="form-control">
                                    <c:forEach var="p" items="${classes}">
                                        <c:choose>
                                            <c:when test="${p.nomClasse eq eleve.nomClasse}">
                                                <option selected >${eleve.nomClasse}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>${p.nomClasse}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                </select>
                            </div>

                            <div class="form-group">
                                <label>Régime</label>
                                <select name="regime" class="form-control">
                                    <c:forEach var="r" items="${regimes}">
                                        <c:choose>
                                            <c:when test="${r eq eleve.regime}">
                                                <option selected >${r}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>${r}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Nom</label>
                                <input type="text" name="nom" value="${eleve.getNom()}" class="form-control" />
                            </div>  

                            <div class="form-group">
                                <label>Prénom</label>
                                <input type="text" name="prenom" value="${eleve.getPrenom()}" class="form-control" />
                            </div>    

                        </div>
                        <!--/////////////////////////////////// -->
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Adresse</label>
                                <input type="text" name="adresse" value="${eleve.getAdresse()}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>Téléphone</label>
                                <input type="text" name="tel" value="${eleve.getTel()}" class="form-control"  maxlength="9" 
                                       onkeypress=" return event.charCode >= 48 && event.charCode <= 57"/>
                            </div>  

                            <div class="form-group">
                                <label>Date de naissance</label>
                                <input type="date" name="dateNaissance" value="${eleve.getDateNaissance()}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>Lieu de naissance</label>
                                <input type="text" name="lieuNaissance" value="${eleve.getLieuNaissance()}" class="form-control"/>
                            </div>                                
                        </div>
                        <div class="col-lg-12">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-8">
                                <button class="btn btn-success btn-block" type="submit">Modifier informations</button>
                            </div>

                        </div>
                </form>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 


    </body>
</html>
