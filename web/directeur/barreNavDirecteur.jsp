<%-- 
    Document   : barreNavCompta
    Created on : 8 mai 2019, 17:31:26
    Author     : Ouzy NDIAYE
--%>

<%            if (session.getAttribute("log") != null) {

%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="Directeur?action=accueilProviseur" style="color: white">SUNU ECOLE</a>                            
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <!--///////////////////////////////////////// -->                                                               
                <li><a id="lien1" href="Directeur?action=formNote"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Consulter Notes</a> </li>
                    <c:if test="${profils eq 'Directeur des études'}"> 
                    <!--//DropDown// -->
                    <li class="dropdown">
                        <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">EDT<span class="caret"></span></a>
                        <ul class="dropdown-menu">                                
                            <li><a href="EDT?connect=accueilEDT"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Créer EDT</a> </li>
                            <li><a href="EDT?connect=afficherEDT"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Afficher EDT</a> </li>
                        </ul>
                    </li>
                    <!--FIn Dropdown -->
                </c:if>
                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administration<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="Directeur?action=admin"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Notre Personnel</a> </li>
                        <li><a href="Directeur?action=listerProfs"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Nos Professeurs</a> </li>

                    </ul>
                </li>
                <!--FIn Dropdown -->

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Classes<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="Directeur?action=saveClasse"><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;&nbsp;Enregistrer Classe</a> </li>
                        <li><a href="Directeur?action=listeclasses"><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;&nbsp;Voir les classes</a> </li>
                        <li><a href="Directeur?action=formAffClass"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Lister Elèves</a> </li>
                    </ul>
                </li>
                <!--FIn Dropdown -->

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Ajouter<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="Directeur?action=ajoutSurv"><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;&nbsp;Personnel</a> </li>
                        <li><a href="Directeur?action=saveMatiere"><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;&nbsp;Matiére</a> </li>
                        <li><a href="Directeur?action=annee"><span class="glyphicon glyphicon-calendar"></span>&nbsp;&nbsp;&nbsp;Année-Scolaire</a> </li>
                    </ul>
                </li>                

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Compte<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="Directeur?action=compte&login=${log}"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;Compte</a> </li>
                        <li><a href="Connexion?connect=deconnection"><span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;&nbsp;Se Déconnecter</a> </li> </ul>
                </li>
                <!--FIn Dropdown -->
                <!-- ////////////////////////////////////////////-->

            </ul>
            <form action="#" method="post" class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="hidden" name="action" value="rechercherParSurv"/>
                    <input type="text" class="form-control" placeholder="Rechercher" style="width: 100px;">
                </div>
                <button type="submit" class="btn btn-default">
                    <img style="width: 20px; height: 20px;" src="vue/search.png"/>
                </button>
            </form>


            <div class="nav navbar-nav navbar-right">

                <c:if test="${imgProfils eq null}">  
                    <a href="Directeur?action=compte&login=${log}">
                        <img src="ImageUser/Avatar.png" 
                             class="img-circle" style="width: 50px;height: 50px;" alt="Photo profil"/>
                    </a>
                </c:if>
                <c:if test="${imgProfils ne null}">
                    <a href="Directeur?action=compte&login=${log}">
                        <img src="ImageUser/${imgProfils}" 
                             class="img-circle" style="width: 50px;height: 50px;" alt="Photo profil"/>
                    </a>
                </c:if>                                    
            </div>
            <ul class="nav navbar-nav navbar-right">   
                <li>${profils}</li><br>
                <li id="nom">${prenom}&nbsp;${nom}</li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!--////////////////////////////////////////////////////////////////////// --->    

<% } else {
%>
<jsp:forward page="vue/SeConnecter.jsp"/>
<% }%>
</body>
</html>

