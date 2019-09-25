<%-- 
    Document   : listeSurv
    Created on : 15 mars 2019, 15:41:24
    Author     : ibrah
--%>
<%@page import="model.Professeur"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Administration</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavDirecteur.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">  
                    <c:if test="${profils eq 'Proviseur'}">
                        <c:if test="${empty pers1}">
                            <table class="table table-bordered table-hover table-responsive" >
                                <thead>
                                    <tr>
                                <p class="titre"> 
                                    Aucun(e) ${profils_DE} n'a été ajouté 
                                </p>
                                <div class="col-lg-4"></div>
                                <div class="col-lg-4">
                                    <button class="btn btn-success btn-block">
                                        <a style="text-decoration: none; color: white; font-size: 15px;"  href="Directeur?action=ajoutSurv">
                                            Ajouter ${profils_DE}
                                        </a>
                                    </button>
                                </div> 
                                </tr>
                                </thead>
                            </table>
                        </c:if>
                        <c:if test="${!empty pers1}">
                            <p class="titre">${profils_DE}</p>
                            <table class="table table-bordered table-hover table-responsive" >
                                <thead>
                                    <tr>
                                        <th id="entete" class="col">Nom</th>
                                        <th id="entete" class="col">Prénom</th>
                                        <th id="entete" class="col">Adresse</th>
                                        <th id="entete" class="col">Téléphone</th>
                                            <%-- <th id="entete" class="col">Désactiver</th> --%>
                                        <th id="entete" class="col">Modifier</th>
                                        <th id="entete" class="col">Supprimer</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="p" items="${pers1}">
                                        <tr>
                                            <td>${p.nom}</td>
                                            <td>${p.prenom}</td>
                                            <td>${p.adresse}</td>
                                            <td>${p.tel}</td>
                                            <td>
                                                <a href="Directeur?action=modifierAdmin&&login=${p.login}&profils_DE=${profils_DE}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                            </td>
                                            <td>
                                                <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </c:if>
                </div>

                <div class="col-lg-12">
                    <c:if test="${empty pers3}">
                        <table class="table table-bordered table-hover table-responsive" >
                            <thead>
                                <tr>
                            <p class="titre"> 
                                Aucun(e) ${profils_Surv_Gene} n'a été ajouté 
                            </p>
                            <div class="col-lg-4"></div>
                            <div class="col-lg-4">
                                <button class="btn btn-success btn-block">
                                    <a style="text-decoration: none; color: white; font-size: 15px;"  href="Directeur?action=ajoutSurv">
                                        Ajouter ${profils_Surv_Gene}
                                    </a>
                                </button>
                            </div>  
                            </tr>
                            </thead>
                        </table>
                    </c:if>                        

                    <c:if test="${!empty pers3}">
                        <p class="titre">${profils_Surv_Gene}</p>
                        <table class="table table-bordered table-hover table-responsive" >
                            <thead>
                                <tr>
                                    <th id="entete" class="col">Nom</th>
                                    <th id="entete" class="col">Prénom</th>
                                    <th id="entete" class="col">Adresse</th>
                                    <th id="entete" class="col">Téléphone</th>
                                        <%-- <th id="entete" class="col">Désactiver</th> --%>
                                    <th id="entete" class="col">Modifier</th>
                                    <th id="entete" class="col">Supprimer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="p" items="${pers3}">
                                    <tr>
                                        <td>${p.nom}</td>
                                        <td>${p.prenom}</td>
                                        <td>${p.adresse}</td>
                                        <td>${p.tel}</td>
                                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                                        <td>
                                            <a href="Directeur?action=modifierAdmin&&login=${p.login}&profils_Surv_Gene=${profils_Surv_Gene}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                        </td>
                                        <td>
                                            <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>

                <!--////////////////////////////////////////////////////////////////////// -->
                <div class="col-lg-12">

                    <c:if test="${empty pers2}">
                        <table class="table table-bordered table-hover table-responsive" >
                            <thead>
                                <tr>
                            <p class="titre">Aucun(e) ${profils_Surv} n'a été ajouté</p>
                            <div class="col-lg-4"></div>
                            <div class="col-lg-4">
                                <button class="btn btn-success btn-block">
                                    <a style="text-decoration: none; color: white; font-size: 15px;"  href="Directeur?action=ajoutSurv">
                                        Ajouter ${profils_Surv}
                                    </a>
                                </button>
                            </div> 
                            </tr>
                            </thead>
                        </table>
                    </c:if>
                    <c:if test="${!empty pers2}">
                        <p class="titre">${profils_Surv}</p>
                        <table class="table table-bordered table-hover table-responsive">
                            <thead>
                                <tr>
                                    <th id="entete" class="col">Nom</th>
                                    <th id="entete" class="col">Prénom</th>
                                    <th id="entete" class="col">Adresse</th>
                                    <th id="entete" class="col">Téléphone</th>
                                        <%-- <th id="entete" class="col">Désactiver</th> --%>
                                    <th id="entete" class="col">Modifier</th>
                                    <th id="entete" class="col">Supprimer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="p" items="${pers2}">
                                    <tr>
                                        <td>${p.nom}</td>
                                        <td>${p.prenom}</td>
                                        <td>${p.adresse}</td>
                                        <td>${p.tel}</td>
                                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                                        <td>
                                            <a href="Directeur?action=modifierAdmin&&login=${p.login}&profils_Surv=${profils_Surv}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                        </td>
                                        <td>
                                            <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                </div>

                <div class="col-lg-12">
                    <c:if test="${empty pers4}">
                        <table class="table table-bordered table-hover table-responsive" >
                            <thead>
                                <tr>
                            <p class="titre">
                                Aucun(e) ${profils_Comp} n'a été ajouté
                            </p>
                            <div class="col-lg-4"></div>
                            <div class="col-lg-4">
                                <button class="btn btn-success btn-block">
                                    <a style="text-decoration: none; color: white; font-size: 15px;"  href="Directeur?action=ajoutSurv">
                                        Ajouter ${profils_Comp}
                                    </a>
                                </button>
                            </div> 
                            </tr>
                            </thead>
                        </table>
                    </c:if>

                    <c:if test="${!empty pers4}">
                        <p class="titre">${profils_Comp}</p>
                        <table class="table table-bordered table-hover table-responsive" >
                            <thead>
                                <tr>
                                    <th id="entete" class="col">Nom</th>
                                    <th id="entete" class="col">Prénom</th>
                                    <th id="entete" class="col">Adresse</th>
                                    <th id="entete" class="col">Téléphone</th>
                                        <%-- <th id="entete" class="col">Désactiver</th> --%>
                                    <th id="entete" class="col">Modifier</th>
                                    <th id="entete" class="col">Supprimer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="p" items="${pers4}">
                                    <tr>
                                        <td>${p.nom}</td>
                                        <td>${p.prenom}</td>
                                        <td>${p.adresse}</td>
                                        <td>${p.tel}</td>
                                        <%-- <td><button><a href="Directeur?action=desactiverSurv&&loginSurv=${p.login}">Désactiver</a></button></td> --%>
                                        <td>
                                            <a href="Directeur?action=modifierAdmin&&login=${p.login}&profils_Comp=${profils_Comp}"><img src="modifier.png" alt="Modifier" id="modifier"/></a>
                                        </td>
                                        <td>
                                            <a href="Directeur?action=supprimerPers&&login=${p.login}"><img src="delete.png" alt="Supprimer" id="modifier"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
