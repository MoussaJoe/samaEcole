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
            <a class="navbar-brand" href="#" style="color: white">SUNU ECOLE</a>                            
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <!--///////////////////////////////////////// -->
                <li>
                    <a id="lien1" href="Surveillant?action=validerInscr">
                        <span></span>&nbsp;&nbsp;&nbsp;Valider Inscription</a>
                </li>                                

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Classes<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="Surveillant?action=absence"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Cahier d'Absences</a> </li>
                        <li><a href="Surveillant?action=formAffClass"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Lister Classe</a> </li>
                        <li>
                            <a href="Surveillant?action=recap"><span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;&nbsp;R�capitulatif</a>
                        </li>
                    </ul>
                </li>
                <!--FIn Dropdown -->

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Professeurs<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li>
                            <a href="Surveillant?action=ajoutProf"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;&nbsp;Ajouter Professeur</a> 
                        </li>
                        <li>
                            <a href="Surveillant?action=listerProfs"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Lister Professeur</a>
                        </li>
                    </ul>
                </li>
                <!--FIn Dropdown -->

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Notes<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="Surveillant?action=formNote"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;Consulter notes</a> </li>                    
                        <li><a href="Surveillant?action=bulletin"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;&nbsp;Bulletins</a> </li>
                    </ul>
                </li>
                <!--FIn Dropdown -->

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Compte<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="Surveillant?action=compte&login=${log}"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;Mon compte</a> </li>
                        <li><a href="Controleur?action=deconnection"><span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;&nbsp;Se d�connecter</a> </li> 
                    </ul>
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
                    <a href="Comptable?login=${log}&connect=compte">
                        <img src="ImageUser/Avatar.png" 
                             class="img-circle" style="width: 50px;height: 50px;" alt="Photo profil"/>
                    </a>
                </c:if>
                <c:if test="${imgProfils ne null}">
                    <a href="Comptable?login=${log}&connect=compte">
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

