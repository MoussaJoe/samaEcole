<%-- 
    Document   : recap-moyenne
    Created on : 15 sept. 2019, 14:46:44
    Author     : Moussa Joseph D Sarr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="Image/icone_logo.png" sizes="310x310" />
        <title>Etat Récapitulatif des moyennes</title>

        <style>
            .entete{
                width: 100vw;
                display:flex;
                flex-direction: row;
                align-items : center;
                align-content : space-around;
                justify-content : flex-start;
            }
            h2{
                margin-left: 0px;
                text-align: center;
                color: brown;

            }
            #imag{
                width: 15vw;
            }
            th,td{
                border: black solid;
                text-align: center;
            }
            .tableau{
                display: flex;
                justify-content: center;
            }
            table{
                border: black solid;
                border-collapse: collapse; 
                text-align: center;
            }
            #tabl1{

                border: black solid;
                border-collapse: collapse; 
                text-align: center;
                width: 80vw;
            }
            .ecole{
                margin-left: 5vw;
                font-size: 3vw;
                font-weight: bold;
            }
            .infos{
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                margin-left: 9vw;
                margin-right: 9vw;
            }
            a{
                display: flex;
                justify-content: center;
            }            
        </style>
    </head>
    <body>
        <% String couleur = "red";%>

        <div class="entete">
            <img src="${pageContext.request.contextPath}/Image/log_bul.jpg" style="width: 100px;" id="imag">

            <div class="ecole">Lycée DeLafosse</div>
        </div>
        <h2>Etat Récapitulatif des moyennes de ${nomClasse} au ${fn:replace(semestre, '_', ' ')} ${annee}</h2>
        <div class="infos">
            <div class="gauche"></div>
            <div class="droite">Année scolaire : ${annee}</div>
        </div>
        <div class="infos">
            <div class="gauche"></div>
            <div class="droite">Période: ${fn:replace(semestre, '_', ' ')}</div>
        </div>
        <br>
        <div class="tableau">
            <table id="tabl1">
                <tr>
                    <th>Mat</th>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Moyenne</th>
                    <th>Rang</th>
                    <th>Absences(h)</th>
                    <th>Retards(h)</th>
                    <th>Appréciations</th>
                </tr>
                <c:forEach var="bul" items="${bulletins}">
                    <tr>
                        <td>

                            ${bul.login}

                        </td>
                       
                        <td>
                           ${bul.prenom}
                        </td>

                        <td>
                            ${bul.nom}
                        </td>
                         <td>
                        <c:choose>
                            <c:when test="${semestre == '1er_semestre'}">
                                ${bul.moySemestre1}
                            </c:when>
                            <c:otherwise>
                                 ${bul.moySemestre2}
                            </c:otherwise>
                        </c:choose>
                       
                            
                        </td>
                        <td>
                            ${bul.rang}
                        </td>
                        <td>
                            ${bul.absences}
                        </td>
                        <td>
                            ${bul.retards}
                        </td>
                        <td>
                            ${bul.appreciation}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <style media="print">
            #pasaffiche{
                visibility: hidden;
            }
        </style>
        <a id="pasaffiche" href="javascript:print()">Imprimer</a>
         <a id="pasaffiche" href="Surveillant?action=retourner">Retour</a>
    </body>
</html>
