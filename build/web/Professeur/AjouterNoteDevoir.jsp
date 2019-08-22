

<%-- 
    Document   : AloutNote
    Created on : 12 juil. 2018, 12:49:56
    Author     : ibrah
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Ajouter Notes Devoir</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {

        %>
        <%@include file="barreNavProf.jsp" %>
        <script>
            <c:if test="${!empty message}">
            alert("les notes ne doivent pas etre sup�rieur � 20");
            </c:if>
            <c:if test="${!empty message1}">
            alert("les notes ont �t� ajouter avec succ�e");
            </c:if>
            function verifTaille() {
                var val1 = document.getElementById("note1").value,
                        val2 = document.getElementById("note2").value,
                        val3 = document.getElementById("note3").value,
                        taille1 = document.getElementById("verifNote1"),
                        taille2 = document.getElementById("verifNote2"),
                        taille3 = document.getElementById("verifNote3");
                /*taille.innerHTML = val1;*/
                if (val1 > 20) {
                    /*onkeypress="verifTaille()"*/
                    taille1.innerHTML = "<span style='color: red';font-weight: bold>La note ne peut etre sup�rieur � 20</span>";
                }
                if (val2 > 20) {
                    /*onkeypress="verifTaille()"*/
                    taille2.innerHTML = "<span style='color: red';font-weight: bold>La note ne peut etre sup�rieur � 20</span>";
                }
                if (val3 > 20) {
                    /*onkeypress="verifTaille()"*/
                    taille3.innerHTML = "<span style='color: red';font-weight: bold>La note ne peut etre sup�rieur � 20</span>";
                }
            }
        </script>
        <%--  <h1>${message}</h1> --%>
        <div class="container">
            <div class="row">
                <div class="col-lg-12"> 
                    <form method="post" action="ControleurProf">
                        <input type="hidden" name="action" value="ajouterNoteDevoir"/>
                        <input type="hidden" name="annee" value="${annee}"/>
                        <input type="hidden" name="semestre" value="${semestre}"/>
                        <input type="hidden" name="matiere" value="${matiere}"/>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col" id="entete">Pr�nom</th>
                                    <th scope="col" id="entete">Nom</th>
                                    <th scope="col" id="entete">Note Devoir</th>
                                </tr>
                            </thead>
                            <c:forEach var="i" items="${eleve}">
                                <tbody>
                                    <tr>
                                        <td>${i.prenom}</td>
                                        <td>${i.nom}</td>
                                        <td>
                                            <div class="form-group">
                                                <input id="note1" type="float" onkeypress='return event.charCode >= 46 && event.charCode <= 57' name="devoir" required="" maxlength="5" class="form-control" onkeyup="verifTaille()"/><span id="verifNote1"></span>
                                            </div>
                                        </td>
                                    </tr>    
                                </tbody>
                            </c:forEach> 
                        </table>
                        <div class="col-lg-3"></div>
                        <div class="col-lg-6">
                            <button class="btn btn-success btn-block" type="submit">Valider</button>
                        </div>
                    </form>  
                </div>
            </div>
        </div>
        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
