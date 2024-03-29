<<<<<<< HEAD
<%-- 
    Document   : inscrire-eleve
    Created on : 8 mai 2019, 20:30:10
    Author     : Ouzy NDIAYE
--%>

 
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavCompta.jsp" %> 
        <script>
            <!-- Message erreur date-->
            <c:if test="${!empty msgeErreurDate}">
            alert("Veuillez revoir la date de naissance saisie");
            </c:if>
                <!--Message erreur R�gime -->
            <c:if test="${!empty msgErreurRegime}">
            alert("Veuillez choisir un r�gime");
            </c:if>
                <!--Message Erreur NumTelElv -->
            <c:if test="${!empty msgErreurTelElv}">
            alert("Num�ro de T�l�phone de l'�l�ve incorrect");
            </c:if>
            <!--Message Erreur NumTelPar -->
            <c:if test="${!empty msgErreurTelPar}">
            alert("Num�ro de T�l�phone du parent incorrect");
            </c:if>
            <c:if test="${!empty msgErreurMontant}">
            alert("Veuillez v�rifier le montant saisi");
            </c:if>
            <c:if test="${!empty msgErreurEnreg}">
            alert("l'inscription a �chou�!!!");
            </c:if>
            <c:if test="${!empty msgErreurLogAnsPar}">
            alert("Le login du parent saisi n'existe pas dans la base");
            </c:if>
            <c:if test="${!empty msgEnregReussit}">
            alert("l'inscription a r�ussi!!!");
            </c:if>
        </script>
        <!--///////////////////////////////////// -->
        <div class="container">
            <div class="row">
                
                <p class="titre">Inscription �l�ve</p>
        <form action="Comptable" method="Post">
            <input type="hidden" name="connect" value="valider-inscription" />
            <div class="col-lg-12">
                <p class="edp_Classe">Informations de l'�l�ve</p>
            </div>
            <div class="col-lg-4">                
                <div class="form-group">
                    <label for="prenom">Pr�nom</label>
                    <input id="prenom" type="text" name="prenom" class="form-control" placeholder="Saisir le pr�nom" required>
                </div>
                <!--////////////////////////////////////// -->
                <div class="form-group">
                    <label for="nom">Nom</label>
                    <input id="prenom" type="text" name="nom" class="form-control" placeholder="Saisir le nom" maxlength="20" required>
                </div>
                <!--////////////////////////////////////// -->
                <div class="form-group">
                    <label for="sexe">Sexe</label>
                    <select id="sexe" class="form-control" name="sexe">
                        <option value="Garcon">Gar�on</option>
                        <option value="Fille">Fille</option>
                    </select>
                </div>
            </div>
            <!--|||||||||||||||||||||||||||||||||||||||||||||| -->
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="nom">Adresse</label>
                    <input id="prenom" type="text" name="adresse" class="form-control" placeholder="Saisir l'adresse" maxlength="40" required>
                </div>
                <!--////////////////////////////////////// -->
                <!-- Message erreur date-->
                <c:if test="${!empty msgeErreurDate}">
                    <div class="form-group">
                        <label for="dateNaiss">Date Naissance</label>
                        <input id="prenom" type="date" name="dateNaiss" class="form-control alert alert-danger" required>
                    </div>
                </c:if>
                <c:if test="${empty msgeErreurDate}">
                    <div class="form-group">
                        <label for="dateNaiss">Date Naissance</label>
                        <input id="prenom" type="date" name="dateNaiss" class="form-control" required>
                    </div>
                </c:if>

                <!--////////////////////////////////////// -->
                <div class="form-group">
                    <label for="lieuNaiss">Lieu de Naissance</label>
                    <input id="prenom" type="text" name="lieuNaiss" class="form-control" placeholder="Saisir le lieu de naissance" maxlength="20" required>
                </div>
            </div>
            <!--|||||||||||||||||||||||||||||||||||||||||||||| -->
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="numTel">Num�ro de T�l�phone</label>
                    <div class="form-inline">
                        <select name="opeTelElv" class="form-control">                                
                            <option>30</option>
                            <option>33</option>
                            <option>70</option>
                            <option>76</option>
                            <option>77</option>
                            <option>78</option>
                        </select>
                        <!--Message Erreur NumTelElv -->
                        <c:if test="${!empty msgErreurTelElv}">
                            <input type="text" name="numTelElv" placeholder="Facultatif"  maxlength="7" 
                                   onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control alert alert-danger"/>                                                
                        </c:if>
                        <c:if test="${empty msgErreurTelElv}">
                            <input type="text" name="numTelElv" placeholder="Facultatif"  maxlength="7" 
                                   onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control"/>                                                
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label>S�lectionner un r�gime</label>
                    <select id="regime" onchange="myFunction()" class="form-control" name="regime">
                        <option value="">S�lectionnez un r�gime</option>
                        <option value="Privee">Priv�e</option>
                        <option value="Public">Public</option>
                    </select>
                    <p id="demo" ></p>
                    <script>                        
                        var codePrivee = "\n\
                    <div class='form-group'>\n\
                        <label>Nom Clasee</label>\n\
                        <select class='form-control' name='nomClassePriv'>\n\
                            <c:forEach var='e2' items='${classePrivee}'>\n\n\
                                <option value='${e2}'>${e2}</option>\n\
                            </c:forEach>\n\n\
                        </select>\n\
                    </div>";
                    </script>
                    <script>
                        var codePublic = "\n\
                    <div class='form-group'>\n\
                        <label>Nom Clasee</label>\n\
                        <select class='form-control' name='nomClassePub'>\n\
                            <c:forEach var='e2' items='${classePublic}'>\n\n\
                                <option value='${e2}'>${e2}</option>\n\
                            </c:forEach>\n\n\
                        </select>\n\
                    </div>";
                    </script>
                    <script>
                        function myFunction() {
                            var choix = document.getElementById("regime").value;
                            if (choix == "Privee") 
                                document.getElementById("demo").innerHTML = codePrivee;
                            
                            if (choix == "Public") 
                                document.getElementById("demo").innerHTML = codePublic;
                            
                        }                                            
                    </script>
                </div>
            </div>
            <!--|||||||||||||||||||||||||||||||||-----------Eleve-------------|||||||||||||||||||||||||||||- -->
            <div class="col-lg-12">
                <p class="edp_Classe">Informations du parent</p>
            </div>
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="">Selectioner le parent</label>
                    <select id="selectPar" onchange="functionPar()" class="form-control" name="selectPar">
                        <option value="">-----Veuillez s�lectionner-------</option>
                        <option value="nouveau">Nouveau parent</option>
                        <option value="ancien">Ancien parent</option>
                    </select>
                    <p id="demo2" ></p>                    
                    <script>
                        var newParent = "\n\
                        <div class='form-group'>\n\
                        <label>Pr�nom</label>\n\
                        <input id='prenom' type='text' name='prenomPar' class='form-control' placeholder='Saisir le pr�nom' required/>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                        <label for='nom'>Nom</label>\n\
                        <input id='prenom' type='text' name='nomPar' class='form-control' placeholder='Saisir le nom' maxlength='20' required/>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                        <label for='nom'>Email</label>\n\
                        <input id='prenom' type='email' name='email' class='form-control' placeholder='Saisir l Email (Facultatif)' maxlength='50'/>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                        <label for='numTel'>Num�ro de T�l�phone</label>\n\
                        <div class='form-inline'>\n\
                        <select name='opeTelPar' class='form-control' required>\n\
                        <option>30</option>\n\
                        <option>33</option>\n\
                        <option>70</option>\n\
                        <option>76</option>\n\
                        <option>77</option>\n\
                        <option>78</option>\n\
                        </select>\n\
                        <input type='text' name='numTelPar' placeholder='Saisir le num�ro de t�l�phone'  maxlength='7' onkeypress=' return event.charCode >= 48 && event.charCode <= 57' class='form-control' required/>\n\
                        </div>\n\
                        ";
                    </script>
                        <script>
                        var ancienParent = "\n\
                        <div class='form-group'>\n\
                        <label>Login Parent</label>\n\
                        <input id='login' type='text' name='loginPar' class='form-control' placeholder='Saisir le login' required/>\n\
                        </div>\n\
                        ";
                        </script>
                    <script>
                        function functionPar() {
                            var choix = document.getElementById("selectPar").value;
                            if (choix == "nouveau") 
                                document.getElementById("demo2").innerHTML = newParent;
                            
                            if (choix == "ancien") 
                                document.getElementById("demo2").innerHTML = ancienParent;
                            
                        }                                            
                    </script>
                </div>
            </div>

            <!--|||||||||||||||||||||-----------Comptabilit�-----------------||||||||||||||||||||||||||||||||| -->
            <div class="col-lg-12">
                <p class="edp_Classe">Comptabilit�</p>
            </div>
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="montantInsc">Montant Inscription</label>
                    <input id="prenom" type="text" name="montantInsc" class="form-control" placeholder="Saisir le montant"
                           maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" required>
                </div>
                <!--////////////////////////////////////// -->
            </div>
            <div class="col-lg-12">
                <div class="col-lg-3"></div>
                <div class="col-lg-7">
                    <button class="btn btn-success btn-block" type="submit">Inscrire</button>
                </div>

            </div>   
        </form>

        </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
=======
<%-- 
    Document   : inscrire-eleve
    Created on : 8 mai 2019, 20:30:10
    Author     : Ouzy NDIAYE
--%>

 
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavCompta.jsp" %> 
        <script>
            <!-- Message erreur date-->
            <c:if test="${!empty msgeErreurDate}">
            alert("Veuillez revoir la date de naissance saisie");
            </c:if>
                <!--Message erreur R�gime -->
            <c:if test="${!empty msgErreurRegime}">
            alert("Veuillez choisir un r�gime");
            </c:if>
                <!--Message Erreur NumTelElv -->
            <c:if test="${!empty msgErreurTelElv}">
            alert("Num�ro de T�l�phone de l'�l�ve incorrect");
            </c:if>
            <!--Message Erreur NumTelPar -->
            <c:if test="${!empty msgErreurTelPar}">
            alert("Num�ro de T�l�phone du parent incorrect");
            </c:if>
            <c:if test="${!empty msgErreurMontant}">
            alert("Veuillez v�rifier le montant saisi");
            </c:if>
            <c:if test="${!empty msgErreurEnreg}">
            alert("l'inscription a �chou�!!!");
            </c:if>
            <c:if test="${!empty msgErreurLogAnsPar}">
            alert("Le login du parent saisi n'existe pas dans la base");
            </c:if>
            <c:if test="${!empty msgEnregReussit}">
            alert("l'inscription a r�ussi!!!");
            </c:if>
        </script>
        <!--///////////////////////////////////// -->
        <div class="container">
            <div class="row">
                
        <h1>Inscription �l�ve</h1>
        <form action="Comptable" method="Post">
            <input type="hidden" name="connect" value="valider-inscription" />
            <div class="col-lg-12">
                <p class="edp_Classe">Informations de l'�l�ve</p>
            </div>
            <div class="col-lg-4">                
                <div class="form-group">
                    <label for="prenom">Pr�nom</label>
                    <input id="prenom" type="text" name="prenom" class="form-control" placeholder="Saisir le pr�nom" required>
                </div>
                <!--////////////////////////////////////// -->
                <div class="form-group">
                    <label for="nom">Nom</label>
                    <input id="prenom" type="text" name="nom" class="form-control" placeholder="Saisir le nom" maxlength="20" required>
                </div>
                <!--////////////////////////////////////// -->
                <div class="form-group">
                    <label for="sexe">Sexe</label>
                    <select id="sexe" class="form-control" name="sexe">
                        <option value="Garcon">Gar�on</option>
                        <option value="Fille">Fille</option>
                    </select>
                </div>
            </div>
            <!--|||||||||||||||||||||||||||||||||||||||||||||| -->
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="nom">Adresse</label>
                    <input id="prenom" type="text" name="adresse" class="form-control" placeholder="Saisir l'adresse" maxlength="40" required>
                </div>
                <!--////////////////////////////////////// -->
                <!-- Message erreur date-->
                <c:if test="${!empty msgeErreurDate}">
                    <div class="form-group">
                        <label for="dateNaiss">Date Naissance</label>
                        <input id="prenom" type="date" name="dateNaiss" class="form-control alert alert-danger" required>
                    </div>
                </c:if>
                <c:if test="${empty msgeErreurDate}">
                    <div class="form-group">
                        <label for="dateNaiss">Date Naissance</label>
                        <input id="prenom" type="date" name="dateNaiss" class="form-control" required>
                    </div>
                </c:if>

                <!--////////////////////////////////////// -->
                <div class="form-group">
                    <label for="lieuNaiss">Lieu de Naissance</label>
                    <input id="prenom" type="text" name="lieuNaiss" class="form-control" placeholder="Saisir le lieu de naissance" maxlength="20" required>
                </div>
            </div>
            <!--|||||||||||||||||||||||||||||||||||||||||||||| -->
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="numTel">Num�ro de T�l�phone</label>
                    <div class="form-inline">
                        <select name="opeTelElv" class="form-control">                                
                            <option>30</option>
                            <option>33</option>
                            <option>70</option>
                            <option>76</option>
                            <option>77</option>
                            <option>78</option>
                        </select>
                        <!--Message Erreur NumTelElv -->
                        <c:if test="${!empty msgErreurTelElv}">
                            <input type="text" name="numTelElv" placeholder="Facultatif"  maxlength="7" 
                                   onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control alert alert-danger"/>                                                
                        </c:if>
                        <c:if test="${empty msgErreurTelElv}">
                            <input type="text" name="numTelElv" placeholder="Facultatif"  maxlength="7" 
                                   onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control"/>                                                
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label>S�lectionner un r�gime</label>
                    <select id="regime" onchange="myFunction()" class="form-control" name="regime">
                        <option value="">S�lectionnez un r�gime</option>
                        <option value="Privee">Priv�e</option>
                        <option value="Public">Public</option>
                    </select>
                    <p id="demo" ></p>
                    <script>                        
                        var codePrivee = "\n\
                    <div class='form-group'>\n\
                        <label>Nom Clasee</label>\n\
                        <select class='form-control' name='nomClassePriv'>\n\
                            <c:forEach var='e2' items='${classePrivee}'>\n\n\
                                <option value='${e2}'>${e2}</option>\n\
                            </c:forEach>\n\n\
                        </select>\n\
                    </div>";
                    </script>
                    <script>
                        var codePublic = "\n\
                    <div class='form-group'>\n\
                        <label>Nom Clasee</label>\n\
                        <select class='form-control' name='nomClassePub'>\n\
                            <c:forEach var='e2' items='${classePublic}'>\n\n\
                                <option value='${e2}'>${e2}</option>\n\
                            </c:forEach>\n\n\
                        </select>\n\
                    </div>";
                    </script>
                    <script>
                        function myFunction() {
                            var choix = document.getElementById("regime").value;
                            if (choix == "Privee") 
                                document.getElementById("demo").innerHTML = codePrivee;
                            
                            if (choix == "Public") 
                                document.getElementById("demo").innerHTML = codePublic;
                            
                        }                                            
                    </script>
                </div>
            </div>
            <!--|||||||||||||||||||||||||||||||||-----------Eleve-------------|||||||||||||||||||||||||||||- -->
            <div class="col-lg-12">
                <p class="edp_Classe">Informations du parent</p>
            </div>
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="">Selectioner le parent</label>
                    <select id="selectPar" onchange="functionPar()" class="form-control" name="selectPar">
                        <option value="">-----Veuillez s�lectionner-------</option>
                        <option value="nouveau">Nouveau parent</option>
                        <option value="ancien">Ancien parent</option>
                    </select>
                    <p id="demo2" ></p>                    
                    <script>
                        var newParent = "\n\
                        <div class='form-group'>\n\
                        <label>Pr�nom</label>\n\
                        <input id='prenom' type='text' name='prenomPar' class='form-control' placeholder='Saisir le pr�nom' required/>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                        <label for='nom'>Nom</label>\n\
                        <input id='prenom' type='text' name='nomPar' class='form-control' placeholder='Saisir le nom' maxlength='20' required/>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                        <label for='nom'>Email</label>\n\
                        <input id='prenom' type='email' name='email' class='form-control' placeholder='Saisir l Email (Facultatif)' maxlength='50'/>\n\
                        </div>\n\
                        <div class='form-group'>\n\
                        <label for='numTel'>Num�ro de T�l�phone</label>\n\
                        <div class='form-inline'>\n\
                        <select name='opeTelPar' class='form-control' required>\n\
                        <option>30</option>\n\
                        <option>33</option>\n\
                        <option>70</option>\n\
                        <option>76</option>\n\
                        <option>77</option>\n\
                        <option>78</option>\n\
                        </select>\n\
                        <input type='text' name='numTelPar' placeholder='Saisir le num�ro de t�l�phone'  maxlength='7' onkeypress=' return event.charCode >= 48 && event.charCode <= 57' class='form-control' required/>\n\
                        </div>\n\
                        ";
                    </script>
                        <script>
                        var ancienParent = "\n\
                        <div class='form-group'>\n\
                        <label>Login Parent</label>\n\
                        <input id='login' type='text' name='loginPar' class='form-control' placeholder='Saisir le login' required/>\n\
                        </div>\n\
                        ";
                        </script>
                    <script>
                        function functionPar() {
                            var choix = document.getElementById("selectPar").value;
                            if (choix == "nouveau") 
                                document.getElementById("demo2").innerHTML = newParent;
                            
                            if (choix == "ancien") 
                                document.getElementById("demo2").innerHTML = ancienParent;
                            
                        }                                            
                    </script>
                </div>
            </div>

            <!--|||||||||||||||||||||-----------Comptabilit�-----------------||||||||||||||||||||||||||||||||| -->
            <div class="col-lg-12">
                <p class="edp_Classe">Comptabilit�</p>
            </div>
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="montantInsc">Montant Inscription</label>
                    <input id="prenom" type="text" name="montantInsc" class="form-control" placeholder="Saisir le montant"
                           maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" required>
                </div>
                <!--////////////////////////////////////// -->
            </div>
            <div class="col-lg-12">
                <div class="col-lg-3"></div>
                <div class="col-lg-7">
                    <button class="btn btn-success btn-block" type="submit">Inscrire</button>
                </div>

            </div>   
        </form>

        </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="vue/SeConnecter.jsp"/>
        <% }%>
    </body>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
</html>