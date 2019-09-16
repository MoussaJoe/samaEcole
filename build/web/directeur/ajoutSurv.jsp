<%-- 
    Document   : ajoutSurv
    Created on : 15 févr. 2019, 23:58:29
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout Directeur des études</title>
    </head>
    <body>
        <%             if (session.getAttribute("log") != null) {

        %>
        <%@include file="accueilDirecteur.jsp" %>
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
        <h1>Formulaire d'ajout de Surveillant  :</h1>
        <c:if test="${!empty msg}">
            <h2>Login:&nbsp;&nbsp;${loginSurv}</h2>
            <h2>Mot de passe:&nbsp;&nbsp;${mdpSurv}</h2>
        </c:if>
        <form action="Directeur" method="Post">
            <table id="tab">
                <input type="hidden" name="action" value="formSurv"/>
                <tr>
                    <th>Profil :</th>
                    <td>
                        <div class="form-group">
                            <select name="profil" value="" class="form-control" required="">
                                <option>--choisir un profil--</option>
                                <option>Directeur des études</option>
                                <option>Surveillant Général</option>
                                <option>Surveillant</option>
                                <option>Comptable</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Nom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nom" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Prénom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenom" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Adresse :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="adresse" value="" class="form-control" required=""/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Téléphone :</th>
                    <td>
                        <div class="form-group">
                            <div class="form-inline">
                                <select name="tel1" class="form-control" required="">                                
                                    <option>30</option>
                                    <option>33</option>
                                    <option>70</option>
                                    <option>76</option>
                                    <option>77</option>
                                    <option>78</option>
                                </select> 
                                <input type="text" name="tel2" value="" maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control" required=""/>
                            </div>
                        </div>
                    </td>
                </tr>



                <tr>
                    <th></th>
                    <td><button class="btn btn-success" type="submit">Valider</button></td>
                </tr>
            </table>
        </form>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 

    </body>
</html>
