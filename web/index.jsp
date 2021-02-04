<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .body{
                font-family: "Arial";
                color: white;
                background-color: #343A40;
            }
            .input{
                width:150%;
                padding:20px;
                border:1px solid gray;
                border-radius:25px;
                margin-bottom: 10px;
                color: black;
            }
            .field{
                padding: 20px;
                width: 350px;
                margin: auto;
                border-color: #717874;
                margin-bottom: 10px;
            }
            .submitButton{
                margin-top:20px;
                align-content:center;
                background: linear-gradient(to bottom left, #07CF54 0%, #97D54B 100%);
                border:none;
                color:white;
                text-align:center;
                display:inline-block;
                font-size:16px;
                border-radius:25px;
                width:150px;
                padding:15px;
                margin-left: 90px;
            }
            h1{
                font-family: Tahoma;
            }
            .header{
                padding: 10px;
                border-bottom-style: solid;
                border-bottom-color: #717874;
                border-bottom-width: 1px;
                margin-bottom: 20px;
            }
            .img{
                height:60px;
                width: 50px;
            }
            .footer{
                padding: 2%;
                margin-top: 30px;
                border-top-style: solid;
                border-top-color: #717874;
                border-top-width: 1px;
            }
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <title>LOGIN</title>
    </head>
    <body class = "body">
        <form action="login" method="post">
            <div class ="header">
                <h1>
                    <a href = "index.jsp"><img class = "img" src = "Images/spootify.png"></a>
                         Spootify
                </h1>
            </div>
                <fieldset class = "field">
                        <table>
                            <tr>
                                <td><input class = "input"  type = "text" placeholder = "Enter username" name = "user"> </td>
                            </tr>
                            <tr>
                                <td> <input class = "input" type = "password" placeholder = "Enter password" name = "pass"></td>
                            </tr>
                        </table>
                        <input class = "submitButton" type ="submit" value = "Login"> 
                        <input class = "submitButton" type ="submit" value = "Sign Up" formaction = "signUp.jsp">
                </fieldset>
           <div class ="footer">
               <h4 style = "font-size:20px;"> WebApp made by:</h4>
                <ul id = "menu">
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name1"));%></li>
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name2"));%></li>
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name3"));%></li>
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name4"));%></li>
                </ul>
                <h6>Current time and date:  <%out.println((Date) getServletContext().getAttribute("date"));%></h6>
           </div>
        </form>
    </body>
</html>
