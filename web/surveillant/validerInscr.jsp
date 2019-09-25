<%-- 
    Document   : validerInscr
    Created on : 4 sept. 2019, 03:09:54
    Author     : Moussa Joseph D Sarr
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>${profils} | Valider inscription</title>
    </head>
    <body>
        <%            if (session.getAttribute("log") != null) {
        %>
        <%@include file="barreNavSurveillant.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                    
                    <p class="titre">Valider l'inscription de l'élève</p>
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4">
                        <form action="Surveillant" method="POST">
                                <input type="hidden" name="action" value="inscrit" />
                                <div class="form-group">
                                    <label>Login ou matricule</label>
                                    <input type="text" name="login" class="form-control" required/>
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

