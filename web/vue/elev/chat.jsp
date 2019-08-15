<%-- 
    Document   : chat
    Created on : 11 mai 2019, 00:08:18
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            *{
                margin: 0;
                padding: 0;
                font-family: tahoma, sans-serif;
                box-sizing: border-box;
            }
            body{
                background: #1ddced;
            }
            .chatBox{
                width: 500px;
                min-width: 390px;
                height: 600px;
                background: #fff;
                padding: 25px;
                margin: 20px auto;
                box-shadow: 0 3px #ccc;
            }

            .chatlogs{
                padding: 10px;
                width: 100%;
                height: 450px;
                overflow-x: hidden;
                overflow-y: scroll;
            }
            .chatlogs::-webkit-scrollbar{
                width: 10px;
            }
            .chatlogs::-webkit-scrollbar-thumb{
                border-radius: 5px;
                background: rgba(0,0,0,.1);
            }

            .chat{
                display : flex;
                flex-flow: row wrap;
                align-items: flex-start;
                margin-bottom: 10px;
            }
            .chat .user-photo{
                width: 60px;
                height: 60px;
                background: #ccc;
                border-radius: 50%;
                overflow: hidden;
            }
            .chat .user-photo img{ 
                width: 100%;
            }



            .chat .chat-message{
                width: 80%;
                padding: 15px;
                margin: 5px 10px 0;
                border-radius: 10px;
                color: #fff;
                font-size: 20px; 
            }
            .amis .chat-message{
                background: #1adda4;
            }

            .moi .chat-message{
                background: #1ddced;
                order: -1;
            }

            .chat-form{
                margin-top: 20px;
                display: flex;
                align-items: flex-start;
            }
            .chat-form textarea{
                background: #fbfbfb;
                width: 75%;
                height: 50px;
                border: 2px solid #eee;
                border-radius: 3px;
                resize: none;
                padding: 10px;
                font-size: 18px;
                color: #333;
            }
            .chat-form textarea::-webkit-scrollbar{
                width: 10px;
            }
            .chat-form textarea::-webkit-scrollbar-thumb{
                border-radius: 5px;
                background: rgba(0,0,0,.1);
            }
            .chat-form textarea:focus{
                background: #fff;
            }
            .chat-form button{
                background: #1ddced;
                padding: 5px 15px;
                font-size: 30px;
                color: #fff;
                border: none;
                margin: 0 10px;
                border-radius: 3px;
                box-shadow: 0 3px 0 #0eb2c1;
                cursor: pointer;

                -webkit-transition: background .2% ease;
                -moz-transition :background .2% ease;
                -o-transition:background .2% ease; 
            }
            .chat-form button:hover{
                background: #13c8d9;
            }
        </style>
        <title>Message</title>
    </head>
    <body>
        <%
            if (session.getAttribute("log") != null) {

        %>
        <%@include file="../../eleve.jsp" %>
        <form method="post" action="ControleurEleve">
            <input type="hidden" name="action" value="repondre"/>
            <input type="hidden" name="login" value="${login}"/>
            <div class="chatBox" align="center">
                <div class="chatlogs">

                    <c:forEach var="i" items="${messEvoye}">
                    <div class="chat amis">
                        <div class="user-photo">
                            <img src="">
                        </div>
                        <p class="chat-message">${i}</p>
                    </div>
                    </c:forEach>
                    <!--///////////////////////////////////////////////-->
                    <c:forEach var="i" items="${messRecu}">
                    <div class="chat moi">
                            <div class="user-photo">
                                    <img src="">
                            </div>
                            <p class="chat-message">${i}</p>
                    </div>						
                    </c:forEach>
                </div>

                <div class="chat-form">
                    <textarea name="message"></textarea>
                    <button>Envoyer</button>
                </div>
            </div>
        </form>

        <% } else {
        %>
        <jsp:forward page="../SeConnecter.jsp"/>
        <% }%>
    </body>
</html>
