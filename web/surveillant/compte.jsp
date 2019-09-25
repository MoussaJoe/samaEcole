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
        <%@include file="barreNavSurveillant.jsp" %>                       
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre"></p>

                    <form method="POST" action="Surveillant">
                        <input type="hidden" name="action" value="confirmPasswd">
                        <div class="col-lg-2"></div>
                        <div class="col-lg-4">

                            <div class="form-group">
                                <label>Login</label>
                                <input type="text" value="${log}" disabled="disabled"  class="form-control" required=""/>
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
                                <label>Confirmer Nouveau Mot de Passe</label>
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
                        <form action="Surveillant" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="action" value="photoProfil"/>
                            <input type="hidden" name="login" value="${log}" />
                            <!--<input type="hidden" name="profils" value="${profils}"/>-->
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
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%> 
    </body>
</html>
