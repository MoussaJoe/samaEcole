<%-- 
    Document   : modificationSurv
    Created on : 15 mars 2019, 15:47:45
    Author     : ibrah
--%>
<%@page import="model.Professeur"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | </title>
    </head>
    <body>

        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">
                        <c:if test="${!empty profils_DE}">${profils_DE}</c:if>
                        <c:if test="${!empty profils_Surv}">${profils_Surv}</c:if>
                        <c:if test="${!empty profils_Surv_Gene}">${profils_Surv_Gene}</c:if>
                        <c:if test="${!empty profils_Comp}">${profils_Comp}</c:if>
                        </p>
                        <div class="col-lg-4"></div>
                        <div class="col-lg-4">
                            <form action="Directeur" method="POST">
                                <input type="hidden" name="action" value="valideModPers" />
                                <input type="hidden" name="log" value="${log}" />

                            <div class="form-group">
                                <label>Profils</label>
                                <select name="profil" value="" class="form-control" required="">
                                    <option disabled="">--choisir un profil--</option>
                                    <c:choose>
                                        <c:when test="${profil == 'Directeur des études'}">
                                            <option selected="selected">Directeur des études</option>
                                            <option>Surveillant Général</option>
                                            <option>Surveillant</option>
                                            <option>Comptable</option>
                                        </c:when>
                                        <c:when test="${profil == 'Surveillant Général'}">
                                            <option>Directeur des études</option>
                                            <option selected="selected">Surveillant Général</option>
                                            <option>Surveillant</option>
                                            <option>Comptable</option>
                                        </c:when>
                                        <c:when test="${profil == 'Surveillant'}">
                                            <option>Directeur des études</option>
                                            <option>Surveillant Général</option>
                                            <option selected="selected">Surveillant</option>
                                            <option>Comptable</option>
                                        </c:when>
                                        <c:when test="${profil == 'Comptable'}">
                                            <option>Directeur des études</option>
                                            <option>Surveillant Général</option>
                                            <option>Surveillant</option>
                                            <option selected="selected">Comptable</option>
                                        </c:when>

                                    </c:choose>


                                </select>
                            </div>

                            <div class="form-group">
                                <label>Nom</label>
                                <input type="text" name="nom" value="${nom}" class="form-control" required=""/>
                            </div>

                            <div class="form-group">
                                <label>Prénom</label>
                                <input type="text" name="prenom" value="${prenom}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>Adresse</label>
                                <input type="text" name="adresse" value="${adresse}" class="form-control" required/>
                            </div>

                            <div class="form-group">
                                <label>Téléphone</label>
                                <input type="text" name="tel" value="${telephone}" class="form-control"  maxlength="9" 
                                       onkeypress=" return event.charCode >= 48 && event.charCode <= 57" required/>
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

