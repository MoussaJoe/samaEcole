<%-- 
    Document   : demandeMatClasse
    Created on : 17 juil. 2018, 09:37:24
    Author     : ibrah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.css" rel="stylesheet"/>
        <link href="dist/css/mystyle.css" rel="stylesheet"/>
        <title>Professeur</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="acceuil.jsp" %>
        <h1 align="center">Veuillez renseigner la classe concernées</h1>

        <%--  <h2 align="center">${message}</h2> --%>
        <form action="ControleurProf" method="POST">
            <input type="hidden" name="action" value="impressionDevoir" />
            <div id="tab">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td>Classe</td>
                            <td> 
                                <div class="form-group">
                                    <select name="nomClasse" class="form-control">
                                        <c:forEach var="i" items="${listClasse2}">
                                            <option value="${i.nomClasse}///${i.regime}">${i.nomClasse} (${i.regime})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Année Scolaire</td>
                            <td>
                                <div class="form-group">
                                    <select name="annee" class="form-control">
                                        <c:forEach var="i" items="${listAnnee2}">
                                            <option>${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><button class="btn btn-success">Valider</button></td>
                        </tr>
                    </tbody>
                </table>
                <c:if test="${!empty message}">
                    <button onclick="popup()">Cliquer pour imprimer le fichier</button>
                </c:if>
            </div>  
        </form>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>

    <SCRIPT LANGUAGE="JavaScript">
        function popup() {
            w = open("Fiche/fiche_devoir.pdf", 'popup', 'width=600,height=900,toolbar=no,scrollbars=no,resizable=yes');
            
       /* w.document.write("<BODY id='facbody'>");
            
             w.document.write("<p id='centerTitre' RELEVE DE NOTE <p>");
             w.document.write(" <?php echo date('d/m/Y');?></h1></div><BR><BR>");
             w.document.write("<h1>CLIENT</h1>");
             w.document.write("<h3>Prenom: "+document.fac.prenom.value+"<BR>");
             w.document.write("Nom: "+document.fac.nom.value+"<BR>");
             w.document.write("Adresse: "+document.fac.adclient.value+"<BR>");
             w.document.write("Email: "+document.fac.mail.value+"<BR>");
             w.document.write("TÈlÈphone: "+document.fac.telclient.value+"</h3><BR><BR><BR>");
             
             w.document.write("<p id='center'><font color='#e40060'>****************************SUNUHOTEL***************************</font></p>");
             
             w.document.write("<h1>RESERVATON</h1>");
             w.document.write("<table id='tabclientlist'  border='2' cellspacing='10'><tr><td id='td'><font color='#e40060'>Chambre</font></td><td id='td'><font color='#e40060'>Date EntrÈe</font></td><td id='td'><font color='#e40060'>Date Sortie</font></td><td id='td'><font color='#e40060'>Prix</font></td><td id='td'><font color='#e40060'>Nombre de jours</font></td></tr>");
             w.document.write("<tr><td>"+document.fac.typechambre.value+" "+document.fac.numchambre.value+"</td>");
             w.document.write("<td>"+document.fac.d1.value+"</td>");
             w.document.write("<td>"+document.fac.d2.value+"</td>");
             w.document.write("<td>"+document.fac.prix.value+" F</td>");
             w.document.write("<td>"+document.fac.nbjour.value+"</td></tr>");
             w.document.write("<tr><td colspan='5'>Net ‡ payer</td><td>"+(document.fac.nbjour.value*document.fac.prix.value)+" F</td></tr>");
             

            w.document.write("</BODY>");
            w.document.close();
            w.print();
            w.close(); */
        }
    </SCRIPT>
</html>
