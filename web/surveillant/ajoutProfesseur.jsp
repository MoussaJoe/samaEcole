<%-- 
    Document   : ajoutProfesseur
    Created on : 14 juil. 2018, 14:03:05
    Author     : Moussa Joseph Sarr
--%>

<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Ajouter Professeur</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavSurveillant.jsp" %>
        <script>
            <c:if test="${!empty msg}">
            alert("Professeur enregistré avec succés");
            </c:if>
        </script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <c:if test="${!empty msg}">
                            <h2>Login:&nbsp;&nbsp;${loginProfesseur}</h2>
                            <h2>Mot de passe:&nbsp;&nbsp;${mdpProfesseur}</h2>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-12">                    
                    <p class="titre">Formulaire d'ajout de Professeur</p>                    
                    <form action="Surveillant" method="Post">
                        <input type="hidden" name="action" value="formProf"/>
                        <input type="hidden" name="annee" value="${anInscr}"/>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-4">
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
                                        <option>76</option>
                                        <option>77</option>
                                        <option>78</option>
                                    </select> 
                                    <input type="text" name="tel2" maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control" required=""/>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Nom Classe</label>
                                <select name="nomClasse" multiple="multiple" class="form-control" required="">
                                    <c:forEach var="p" items="${classes}">
                                        <option>${p.nomClasse}</option>       
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Matières</label>
                                <select name="nomMatiere" multiple="multiple" class="form-control" required="">
                                    <c:forEach var="m" items="${matieres}">
                                        <option>${m}</option> 
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-8">
                                <button class="btn btn-success btn-block" type="submit">Valider</button>
                            </div>
                        </div>
                    </form>

                    <% } else {
                    %>
                    <jsp:forward page="../vue/SeConnecter.jsp"/>
                    <% }%> 

                    </body>
                    </html>
