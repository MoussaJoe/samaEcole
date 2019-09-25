<<<<<<< HEAD:build/web/surveillant/inscription.jsp
<%-- 
    Document   : inscription
    Created on : 2 nov. 2018, 17:40:18
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="surveillant.jsp" %>
        <script>
            <c:if test="${!empty message4}">
            alert("Veuillez revoir la date de naissance saisie");
            </c:if>
            <c:if test="${!empty message1}">
            alert("Inscription  effectué avec succé");
            </c:if>
            <c:if test="${!empty message2}">
            alert("Inscription a échoué!!!");
            </c:if>
        </script>
        <c:if test="${!empty loginIns && !empty mdpIns && !empty loginPar && !empty mdpPar}">
            <h2>Eleve:&nbsp;&nbsp;${loginIns}&nbsp;&nbsp;&nbsp;${mdpIns}</h2>
            <h2>Parent:&nbsp;&nbsp;${loginPar}&nbsp;&nbsp;&nbsp;${mdpPar}</h2>
        </c:if>     
        <table id="tab" class="table table-bordered">
            <tr>
                <td class="test" style="background: grey">
                    <a href="Surveillant?action=inscription" style="color: white">Inscription</a>
                </td>
                
        </table>
        <form action="Surveillant" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="formEleve" />
                <tr>
                    <th>Classe :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control" required="">
                                <c:forEach var="p" items="${classes}">
                                    <option>${p.nomClasse}</option>
                                </c:forEach>

                            </select>
                        </div></td>
                </tr>
                <tr>
                    <th>Nom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nom" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    <th>Prénom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenom" value="" class="form-control" required=""/>
                        </div></td>
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
                    <th>date de Naissance :</th>
                    <td>
                        <div class="form-group">
                            <input type="date" name="dateNaissance" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    <th>Lieu de Naissance :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="lieuNaissance" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    
                    <td>
                        <div class="form-group">
                            <input type="hidden" name="annee" value="${anInscr}" class="form-control"/>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Prénom du Papa ou du Tuteur:</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenomParent" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    <th>téléphone du Papa ou du Tuteur:</th>
                    <td>
                        <div class="form-group">
                            <div class="form-inline">
                                <select name="telParent1" class="form-control" required="">                                
                                    <option>30</option>
                                    <option>33</option>
                                    <option>70</option>
                                    <option>76</option>
                                    <option>77</option>
                                    <option>78</option>
                                </select> 
                                <input type="text" name="telParent2" value="" maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control" required=""/>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success">Valider</button>
                    </td>
                </tr>
            </table>
        </form>
        <% } else {
        %>
        <jsp:forward page="../vue/SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
=======
<%-- 
    Document   : inscription
    Created on : 2 nov. 2018, 17:40:18
    Author     : Moussa Joseph Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="../../surveillant.jsp" %>
        <script>
            <c:if test="${!empty message4}">
            alert("Veuillez revoir la date de naissance saisie");
            </c:if>
            <c:if test="${!empty message1}">
            alert("Inscription  effectué avec succé");
            </c:if>
            <c:if test="${!empty message2}">
            alert("Inscription a échoué!!!");
            </c:if>
        </script>
        <c:if test="${!empty loginIns && !empty mdpIns && !empty loginPar && !empty mdpPar}">
            <h2>Eleve:&nbsp;&nbsp;${loginIns}&nbsp;&nbsp;&nbsp;${mdpIns}</h2>
            <h2>Parent:&nbsp;&nbsp;${loginPar}&nbsp;&nbsp;&nbsp;${mdpPar}</h2>
        </c:if>     
        <table id="tab" class="table table-bordered">
            <tr>
                <td class="test" style="background: grey">
                    <a href="ControleurDirecteur?action=inscription" style="color: white">Inscription</a>
                </td>
                
        </table>
        <form action="ControleurDirecteur" method="POST">
            <table id="tab">
                <input type="hidden" name="action" value="formEleve" />
                <tr>
                    <th>Classe :</th>
                    <td>
                        <div class="form-group">
                            <select name="nomClasse" class="form-control" required="">
                                <c:forEach var="p" items="${classes}">
                                    <option>${p}</option>
                                </c:forEach>

                            </select>
                        </div></td>
                </tr>
                <tr>
                    <th>Nom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nom" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    <th>Prénom :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenom" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    <th>Adresse :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="adresse" value="" class="form-control" required=""/>
                        </div></td>
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
                    <th>date de Naissance :</th>
                    <td>
                        <div class="form-group">
                            <input type="date" name="dateNaissance" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    <th>Lieu de Naissance :</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="lieuNaissance" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    
                    <td>
                        <div class="form-group">
                            <input type="hidden" name="annee" value="${anInscr}" class="form-control"/>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Prénom du Papa ou du Tuteur:</th>
                    <td>
                        <div class="form-group">
                            <input type="text" name="prenomParent" value="" class="form-control" required=""/>
                        </div></td>
                </tr>
                <tr>
                    <th>téléphone du Papa ou du Tuteur:</th>
                    <td>
                        <div class="form-group">
                            <div class="form-inline">
                                <select name="telParent1" class="form-control" required="">                                
                                    <option>30</option>
                                    <option>33</option>
                                    <option>70</option>
                                    <option>76</option>
                                    <option>77</option>
                                    <option>78</option>
                                </select> 
                                <input type="text" name="telParent2" value="" maxlength="7" onkeypress=" return event.charCode >= 48 && event.charCode <= 57" class="form-control" required=""/>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success">Valider</button>
                    </td>
                </tr>
            </table>
        </form>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>  
    </body>
</html>
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403:build/web/vue/surveillant/inscription.jsp
