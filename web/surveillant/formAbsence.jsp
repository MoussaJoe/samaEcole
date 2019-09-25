<%-- 
    Document   : formAbsence
    Created on : 7 sept. 2019, 02:08:48
    Author     : Moussa Joseph D Sarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Absence</title>
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
                            <table
                                <input type="hidden" name="action" value="saveAbsence">
                                <input type="hidden" name="annee" value="${anInscr}">

                                <div class="form-group">
                                    <label>Matricule</label>
                                    <input type="text" name="login" required class="form-control">
                                </div>

                                <div class="form-group">
                                    <label>Matière</label>
                                    <select name="nomMatiere" class="form-control" required="">
                                        <c:forEach var="m" items="${matieres}">
                                            <option>${m}</option> 
                                        </c:forEach>
                                    </select>
                                </div>    

                                <div class="form-group">
                                    <label>Semestre</label>
                                    <select name="semestre" class="form-control" required="">                         
                                        <option value="1er_semestre">1èr semestre</option> 
                                        <option value="2eme_semestre">2nd semestre</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Absence ou retard</label>
                                    <select name="sujet" class="form-control" required="">                         
                                        <option>Retard</option> 
                                        <option id="modNom" onselect="Absencedd()">Absence</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Durée d'absence</label>
                                    <input type="number" name="absence" value="0" class="form-control">
                                </div>

                                <div>
                                    <button class="btn btn-success btn-block" type="submit">Valider</button>
                                </div>
                                <br>
                                <div>
                                    <button class="btn btn-primary btn-block">
                                        <a style="color: white" href="Surveillant?action=listeAbsence" >Lister les absences</a>                                    
                                    </button>
                                </div>                                
                                <div id="demo3"> </div>

                                <script>
                                    function Absencedd() {
                                        mod = document.getElementById("mod3").innerHTML;

                                        document.getElementById("demo3").innerHTML = mod;
                                    }
                                </script>
                            </table>
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
