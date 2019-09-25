<<<<<<< HEAD
<%-- 
    Document   : compteDirecteur
    Created on : 31 oct. 2018, 02:18:36
    Author     : Moussa Joseph Sarr
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${profils} | Paramètre Compte</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>

        <%@include file="barreNavDirecteur.jsp" %>
        <script>
            <c:if test="${!empty message}">
            alert("Echec du changement de mot de passe!");
            </c:if>
            <c:if test="${!empty mes}">
            alert("Votre mot de Passe a été modifié avec succés!!");
            </c:if>
            <c:if test="${!empty msg}">
            alert("Nous ne prenons en charge que les images PNG, GIF ou JPG.");
            </c:if>
            <c:if test="${!empty msgSupp}">
            alert("Une erreur s'est produite lors de la suppression de la photo de profil.\n\n\
        Veuillez réessayer");
            </c:if>
            </script>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12"> 

                        <form method="POST" action="Directeur">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-4">
                                <p class="titre">Changer les paramètres du compte</p> 
                                <input type="hidden" name="action" value="confirmPasswd">

                                <div class="form-group">
                                    <label>Login</label>
                                    <input type="text" value="${log}" disabled="disabled"  class="form-control"/>
                                </div>


                                <div class="form-group">
                                    <label>Ancien mot de passe</label>
                                    <input type="password" name="oldpasswd" value="" class="form-control" required="">
                                </div>    

                                <div class="form-group">
                                    <label>Nouveau mot de passe</label>
                                    <input type="password" name="newpasswd" value="" class="form-control" required="">
                                </div>

                                <div class="form-group">
                                    <label>Confirmer nouveau mot de passe</label>
                                    <input type="password" name="newpasswd1" value="" class="form-control" required="">
                                </div>
                                <div>
                                    <button class="btn btn-success btn-block" type="submit">Valider</button>
                                </div><br>
                                <div>
                                    <button class="btn btn-danger btn-block" type="reset">Annuler</button>
                                </div>
                            </div>
                            <div class="col-lg-2"></div>
                        </form>
                        <div class="col-lg-4">
                            <p class="titre">Changer photo de profil</p>
                            <form action="Directeur" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="photoProfil"/>
                                <input type="hidden" name="login" value="${log}" />
                                <label>Photo de profil</label>
                                <c:if test="${nomImgPers ne null}">
                                    <img src="ImageUser/${nomImgPers}" class="img-responsive" alt="Photo profil" >
                                </c:if>
                                <c:if test="${nomImgPers eq null}">  
                                    <img src="ImageUser/Avatar.png" class="img-responsive" alt="Photo profil"/>
                                </c:if>


                                <br>
                                <button onclick="myFunction()" class="btn btn-success glyphicon glyphicon-edit">  Changer photo</button>
                                <c:if test="${nomImgPers ne null}">
                                    <button class="btn btn-danger ">
                                        <a id="lien_form" onclick="javascript: return confirmation();" class="glyphicon glyphicon-remove" href="Surveillant?action=suppPhotoProfil&login=${log}&profils=${profils}">  Supprimer photo</a>
                                    </button>
                                </c:if>
                                <p id="demo"></p>                                                          
                                <script>
                                    var champs = "<input type='file' name='nomImage' class='form-control' required/>\n\
                                <div>\n\
                                <button class='btn btn-success btn-block' type='submit'>Valider</button>\n\
                                </div>"
                                    function myFunction() {
                                        document.getElementById("demo").innerHTML = champs;
                                    }

                                    function confirmation() {
                                        var code = "Voulez vous vraiment supprimer la photo de profil ?\
            ";
                                        var msg = confirm(code);
                                        if (msg) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }

                                </script> 

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
=======
<%-- 
    Document   : compteDirecteur
    Created on : 31 oct. 2018, 02:18:36
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ParamÃ¨tre Compte</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <c:choose>
            <c:when test="${profils eq 'Directeur'}">
                <%@include file="accueilDirecteur.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="../../surveillant.jsp" %>
            </c:otherwise>
        </c:choose>

        <h1>ParamÃ¨tre Compte :</h1>

        <script>
            <c:if test="${!empty message}">
            alert("Echec du changement de mot de passe!");
            </c:if>
            <c:if test="${!empty mes}">
            alert("Votre mot de Passe a Ã©tÃ© modifiÃ© avec succÃ©s!!");
            </c:if>
        </script>

        <div id="tab">
            <form method="POST" action="ControleurDirecteur">
                <input type="hidden" name="action" value="confirmPasswd">
                <table class="table table-bordered">
                    <tr>
                        <td>Login</td>
                        <td>
                            <div class="form-group">
                                <input type="text" value="${log}" disabled="disabled"  class="form-control" required=""/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Ancien mot de Passe</td>
                        <td>
                            <div class="form-group">
                                <input type="password" name="oldpasswd" value="" class="form-control" required="">
                            </div></td>
                    </tr>
                    <tr>
                        <td>Nouveau Mot de Passe</td>
                        <td>
                            <div class="form-group">
                                <input type="password" name="newpasswd" value="" class="form-control" required="">
                            </div></td>
                    </tr>
                    <tr>
                        <td>Confirmer Nouveau Mot de Passe</td>
                        <td>
                            <div class="form-group">
                                <input type="password" name="newpasswd1" value="" class="form-control" required="">
                            </div></td>
                    </tr>
                    <tr>
                        <td><button class="btn btn-success">Valider</button></td>
                        <td><button type="reset" class="btn btn-success">Annuler</button></td>
                    </tr>
                </table>
            </form>
        </div>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
