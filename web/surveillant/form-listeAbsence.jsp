<%-- 
    Document   : form-listeAbsence
    Created on : 8 sept. 2019, 15:28:13
    Author     : Moussa Joseph D Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Liste des absences</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {
                // String profils = (String) session.getAttribute("profils");
        %>

        <%@include file="barreNavSurveillant.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Formulaire pour renseigner les absences</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Surveillant" method="Post">                            
                            <input type="hidden" name="action" value="absents">
                            <input type="hidden" name="annee" value="${anInscr}">

                            <div class="form-group">
                                <label>Nom classe</label>
                                <select name="nomClasse" class="form-control" required="">
                                    <c:forEach var="p" items="${classes}">
                                        <option>${p.nomClasse}</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="form-group">
                                <label>Régime</label>
                                <select name="regime" class="form-control" required="">
                                    <c:forEach var="r" items="${regimes}">
                                        <option>${r}</option>       
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Semestre</label>
                                <select name="semestre" class="form-control" required="">                         
                                    <option value="1er_semestre">1er semestre</option> 
                                    <option value="2eme_semestre">2nd semestre</option>
                                </select>
                            </div>

                            <div>
                                <button class="btn btn-success btn-block" type="submit">Valider</button>
                            </div>
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
