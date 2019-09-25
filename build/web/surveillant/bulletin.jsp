<%-- 
    Document   : bulletin
    Created on : 11 sept. 2019, 17:33:31
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
        <title>${profils} | Bulletin ${bulletin.prenom} ${bulletin.nom}</title>

        <style>
            .entete{
                width: 100vw;
                display:flex;
                flex-direction: row;
                align-items : center;
                align-content : space-around;
                justify-content : flex-start;
            }
            h1{
                text-align: center;
                margin-left: 0px;
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
        <h1>Bulletin de Notes</h1>
        <div class="infos">
            <div class="gauche">Prénom : ${bulletin.prenom}</div>
            <div class="droite">Nom : ${bulletin.nom}</div>
        </div>
        <div class="infos">
            <div class="gauche">Naissance : ${fn:replace(bulletin.dateNaissance, '-', '/')}</div>
            <div class="droite">Classe: ${bulletin.nomClasse}</div>
        </div>
        <div class="infos">
            <div class="gauche">Matricule : ${bulletin.login}</div>
            <div class="droite">Nbres d'éléves : ${bulletin.nbreEleve}</div>
        </div>
        <div class="infos">
            <div class="gauche">Composition : ${fn:replace(bulletin.semestre, '_', ' ')}</div>
            <div class="droite">Année Scolaire : ${bulletin.annee}</div>
        </div>
        <br>
        <div class="tableau">
            <table id="tabl1">
                <tr>
                    <th>matières</th>
                    <th>Devoir</th>
                    <th>Comp</th>
                    <th>Coef</th>
                    <th>Appréciations</th>
                </tr>
                <c:forEach var="eval" items="${bulletin.evaluation}">
                    <tr>
                        <td>

                            ${eval.nomMatiere}

                        </td>
                        <td>
                            ${eval.devoir}
                        </td>
                        <td>
                            ${eval.composition}
                        </td>

                        <td>
                            ${eval.coef}
                        </td>
                        <td>
                            ${eval.appreciations}
                        </td>
                    </tr>
                </c:forEach>

                    <tr style="background: #d5d5d5; border-left: #ffffff; ">
                    <td colspan="2">TOTAL : ${bulletin.totalMoyenne}/${bulletin.totalCoef}  <br> Moyenne : ${bulletin.moyenneGenerale}/${bulletin.totalCoef}</td>
                    <td colspan="2">Rang : ${bulletin.rang}</td>
                    <td >Absences : ${bulletin.absences} <br> Retards : ${bulletin.retards}</td>
                </tr>   
                <tr >
                    <td colspan="5">
                        Appréciation du Travail :<br>
                        <c:choose>
                            <c:when test="${bulletin.getMoyenneGenerale() >= 14}">
                                <div style=" color: green;border: black solid;margin-left: 30%;margin-right :30%;" ng-if="${bulletin.getMoyenneGenerale() > 6}">
                                    Félicitation X
                                </div>
                            </c:when>
                            <c:otherwise><div style=" border: black solid;margin-left: 30%;margin-right :30%;" ng-if="${bulletin.getMoyenneGenerale() > 6}">
                                    Félicitation
                                </div></c:otherwise>
                        </c:choose>
                         <c:choose>
                            <c:when test="${bulletin.getMoyenneGenerale() >= 14 and bulletin.getMoyenneGenerale() < 20}">
                                <div style=" color :#0092ef;border: black solid;margin-left: 30%;margin-right :30%;">
                                    Encouragements X
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style=" border: black solid;margin-left: 30%;margin-right :30%;">
                                    Encouragements 
                                </div>
                            </c:otherwise>
                        </c:choose>
                         <c:choose>
                            <c:when test="${bulletin.getMoyenneGenerale() >= 12 and bulletin.getMoyenneGenerale() < 20}">
                                <div style=" color :#0092ef;border: black solid;margin-left: 30%;margin-right :30%;">
                                    Tableau d'Honneur X
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style=" border: black solid;margin-left: 30%;margin-right :30%;">
                                    Tableau d'Honneur
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bulletin.getMoyenneGenerale() >= 10 and bulletin.getMoyenneGenerale() < 12}">
                                <div style="color : #2ecc71; border: black solid;margin-left: 30%;margin-right :30%;">
                                    Peut Mieux Faire X
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style=" border: black solid;margin-left: 30%;margin-right :30%;">
                                    Peut Mieux Faire
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bulletin.getMoyenneGenerale() > 0 and bulletin.getMoyenneGenerale() < 10}">
                                <div style="color: red;border: black solid;margin-left: 30%;margin-right :30%;">
                                    Insuffisant X
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style="border: black solid;margin-left: 30%;margin-right :30%;">
                                    Insuffisant
                                </div>
                            </c:otherwise>
                        </c:choose>
                        Conduite :
                    </td>

                </tr>

                <tr>
                    <td>Bonne</td>
                    <td colspan="2">Passable</td>
                    <td>Avertissement</td>
                    <td>Blame</td>

                </tr>

            </table>
        </div>

        <c:if test="${bulletin.semestre eq '2eme_semestre'}">
            <br>
            <div class="infos">
                <table>
                    <tr>
                        <th colspan="2">Décision du Conseil des Maitres</th>
                    </tr>
                    <tr>
                        <td>Admis(e) en classe supérieure</td>
                        <td style="color: white">textetexte</td>
                    </tr>
                    <tr>
                        <td>Autoriser à redoubler</td>
                        <td>&nbsp</td>
                    </tr>
                    <tr>
                        <td>Exclusion</td>
                        <td>&nbsp</td>
                    </tr>
                </table>
                <br>

                <table>
                    <tr>
                        <th>Récapitulation</th>
                    </tr>
                    <tr><td>1er Semestre : ${bulletin.moySemestre1}<br>
                            2e Semestre : ${bulletin.moySemestre2}<br>
                            Moy. Génerale :${bulletin.moySemestre2+bulletin.moySemestre1 div 3}</td></tr>
                </table>
            </div>
        </c:if>
        <br>

        <div class="infos">
            <div style="font-weight: bold;text-decoration: underline;">Le Maitre :</div>
            <div style="font-weight: bold;text-decoration: underline;">Le Directeur :</div>
        </div>


        <style media="print">
            #pasaffiche{
                visibility: hidden;
            }
        </style>
        <div>
        <a id="pasaffiche" href="javascript:print()">Imprimer</a>
        </div>
    </body>
</html>
