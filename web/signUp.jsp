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
                margin-bottom: 15px;
                color:black;
            }
            .field{
                padding: 10px;
                width: 350px;
                margin: auto;
                border-color: #717874;
                margin-bottom: 20px;
            }
            .submitButton{
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
                margin-bottom: 10px;
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
                border-top-style: solid;
                border-top-color: #717874;
                border-top-width: 1px;
            }
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body class = "body">
        <form action = "login" method = "post">
            <script>
                var check = function() {
                    if (document.getElementById('newPass').value == document.getElementById('conPass').value) {
                        document.getElementById('message').style.color = 'white';
                        document.getElementById('message').innerHTML = 'Passwords match!';
                        document.getElementById('signUp').disabled = false;
                    } else {
                        document.getElementById('message').style.color = 'white';
                        document.getElementById('message').innerHTML = 'Passwords do not match';
                        document.getElementById('signUp').disabled = true;
                    }
                }
            </script>
            <div class ="header">
                <h1>
                    <a href = "index.jsp"><img class = "img" src = "Images/spootify.png"></a>
                    Spootify
                </h1>
            </div>
            <fieldset class = "field">
                <div class ="shadow">
                    <table>
                        <tr>
                            <td><input class = "input" type = "text" placeholder = "Enter username" name ="newuser" required> </td>
                        </tr>
                        <tr>
                            <td> <input class = "input" type = "password" placeholder = "Enter password" name ="newpass" id = "newPass" required  aria-label="Password" onkeyup='check();'/></td>
                        </tr>
                        <tr>
                            <td> <input class = "input" type = "password" placeholder = "Confirm password" name="conpass" id = "conPass" required aria-label="Confirm Password" onkeyup='check();'/></td>
                        </tr>
                        <tr>
                            <span id ='message'></span>
                            <td><select class = "input" name = "role" required>
                                    <option value = "admin">Admin</option>
                                    <option value = "guest">Guest</option>
                                    <option value ="user">User</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <input type ="submit" id = "signUp" class = "submitButton" value = "Sign-Up" onchange='check_pass();'/>
<!--                <div class ="modal fade" id = "modal1">
                    <div class = "modal-dialog">
                        <div class ="modal-content">
                            <div class="modal-header">
                                <h3 style ="font-family: Arial; color: black;">Success!</h3>
                            </div>
                            <div class ="modal-body">
                                <p style ="font-family: Arial; color: black">You have successfully created an account!</p>
                            </div>
                            <div class ="modal-footer">
                                <button type ="button" class ="submitButton" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>-->
               
            </fieldset>
            <div class ="footer">
                <h4 style = "font-size:20px;"> WebApp made by:</h4>
                <ul id = "menu">
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name1"));%> </li>
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name2"));%></li>
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name3"));%> </li>
                    <li style ="font-size: 10px;"><%out.println(getServletContext().getInitParameter("name4"));%> </li>
                </ul>
                <h6>Current time and date:  <%out.println((Date) getServletContext().getAttribute("date"));%></h6>
            </div>
        </form>
    </body>
</html>
