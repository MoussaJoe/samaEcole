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
            <!--<a href="Controleur?action=accueil">
                <img alt="Logo" src="Image/log.png" style="width: 50px;">
            </a>  -->                  
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->        

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Devoir<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="ControleurProf?login=${login}&action=ajoutNoteDevoir"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Ajouter notes</a></li>
                        <li><a href="ControleurProf?login=${login}&action=NoteDevoir"><span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;Voir notes</a> </li>
                        <li><a href="ControleurProf?login=${login}&action=ficheDevoir"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;&nbsp;Imprimer fiche Devoir</a></li>
                    </ul>
                </li>

                <!--//DropDown// -->
                <li class="dropdown">
                    <a id="lien1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Composition<span class="caret"></span></a>
                    <ul class="dropdown-menu">                                
                        <li><a href="ControleurProf?login=${login}&action=ajoutNote"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Ajouter notes</a></li>
                        <li><a href="ControleurProf?login=${login}&action=NoteCompo"><span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;Voir notes</a> </li>
                    </ul>
                </li>
                <!--FIn Dropdown -->

                <li>
                    <a id="lien1" href="ControleurProf?login=${login}&action=afficheMessage">
                        <span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;&nbsp;Message
                    </a>
                </li>
                <li>
                    <a id="lien1" href="ControleurProf?login=${login}&action=compte"><span class="glyphicon glyphicon-user">                   
                        </span>&nbsp;&nbsp;&nbsp;Compte
                    </a>
                </li>
                <li>
                    <a id="lien1" href="ControleurProf?action=deconnection"><span class="glyphicon glyphicon-lock"></span>
                        &nbsp;&nbsp;&nbsp;Se Deconnecter
                    </a>
                </li>                    
                <!-- ////////////////////////////////////////////-->

            </ul>
            <form action="#" method="post" class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="hidden" name="action" value="rechercher"/>
                    <input type="text" class="form-control" placeholder="Rechercher" style="width: 100px;">
                </div>
                <button type="submit" class="btn btn-default">
                    <img style="width: 20px; height: 20px;" src="vue/search.png"/>
                </button>
            </form>

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

