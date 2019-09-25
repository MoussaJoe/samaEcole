<%-- 
    Document   : ajoutSurv
    Created on : 15 févr. 2019, 23:58:29
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Ajouter Personnel</title>
    </head>
    <body>
        <%             if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <script>
            <c:if test="${!empty msg}">
            alert("L'enregistrement effectué avec succés");
            </c:if>
        </script>
        <script>
            <c:if test="${!empty erreurProfil}">
            alert("le profil saisi n'est pas correct");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Formulaire d'ajout du personnel</p>
                    <c:if test="${!empty msg}">
                        <p class="titre">Login:&nbsp;&nbsp;${loginSurv}</p>
                        <p class="titre">Mot de passe:&nbsp;&nbsp;${mdpSurv}</p>
                    </c:if>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Directeur" method="Post">
                            <input type="hidden" name="action" value="formSurv"/>

                            <div class="form-group">
                                <label>Profil</label>
                                <select name="profil" value="" class="form-control" required="">
                                    <option>--choisir un profil--</option>
                                    <option>Directeur des études</option>
                                    <option>Surveillant Général</option>
                                    <option>Surveillant</option>
                                    <option>Comptable</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Nom</label>
                                <input type="text" name="nom" value="" class="form-control" required=""/>
                            </div>

                            <div class="form-group">
                                <label>Prénom</label>
                                <input type="text" name="prenom" value="" class="form-control" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Adresse</label>
                                <input type="text" name="adresse" value="" class="form-control" required=""/>
                            </div>

                            <div class="form-group">
                                <label>Téléphone</label>
                                <div class="form-inline">
                                    <select name="tel1" class="form-control" required="">                                
                                        <option>30</option>
                                        <option>33</option>
                                        <option>70</option>
                                        <option>75</option>
                                        <option>76</option>
                                        <option>77</option>
                                        <option>78</option>
                                    </select> 
                                    <input type="text" name="tel2" value="" maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control" required=""/>
                                </div>
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
