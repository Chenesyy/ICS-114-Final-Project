<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <head>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/9d8c1d262f.js" crossorigin="anonymous"></script>

        <link href="css/animate.min.css" rel="stylesheet">
        <link href="css/bootstrap-dropdownhover.min.css" rel="stylesheet">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-dropdownhover.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


        <style>

            .container {

                padding-left: 2%;
                padding-top: 2%;
            }

            td, th {

                vertical-align: middle;
            }

            h3 {

                align-self: center;
                padding: 1%;

            }


            i {

                align-self: center;

            }

            form {

                padding-top: 2%;
                padding-left: 15%;
            }

            #drop {

                color: white;
            }

            #rightalign {

                padding-left: 55%;
            }

            .footer{
                padding: 2%;
                margin-top: 30px;
                border-top-style: solid;
                border-top-color: #717874;
                border-top-width: 1px;
            }


        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body class="bg-dark text-white">


        <nav class="navbar bg-dark navbar-dark">

            <!-- Brand -->
            <p class="navbar-brand font-weight-bold bg-dark" href="#"> <img src="Images/spootify.png" width="5%"> Spootify</p>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="collapsibleNavbar">

                <ul class="navbar-nav">

                    <li class="nav-item">

                        <a class="nav-link" href="insert.jsp"> Add Songs </a>

                    </li>

                    <li class="nav-item">

                        <div class="dropdown nav-link">

                            <a class="nav-link" href="#" data-toggle="dropdown">
                                Edit
                            </a>

                            <div class="dropdown-menu bg-dark">
                                <a class="dropdown-item" id="drop" href="ctrl" data-toggle="modal" data-target="#myModal1" name="update" value="title"> Title </a>
                                <a class="dropdown-item" id="drop" href="ctrl" data-toggle="modal" data-target="#myModal2" name="update" value="genre"> Genre </a>
                                <a class="dropdown-item" id="drop" href="ctrl" data-toggle="modal" data-target="#myModal3" name="update" value="year"> Year </a>
                            </div>

                        </div>



                    </li>

                    <li class="nav-item">

                        <a class="nav-link" href="logout">Log out</a>

                    </li>

                </ul>
            </div>

        </nav>


        <div class="container">

            <h4 class="text-center"> Welcome to Spootify, <%String role = (String) request.getAttribute("role");%><%=role%>! </h4>

        </div>

        <form class="form-inline" action="ctrl" method="post">

            <button class="btn btn-dark" type="submit"> <i class="fas fa-search" aria-hidden="true" style="color:white"></i> </button>

            <input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search" aria-label="Search" name="search">

        </form>

        <div class="container">

            <div class="row">
                <div class="col-12">


                    <div class="shadow">
                        <div class="card bg-dark">
                            <h3 class="font-weight-bold"> 

                                <i class='fas fa-play-circle' style='font-size:35px;color:greenyellow'></i>
                                My Playlist

                            </h3> 
                            <%
                                ArrayList<String> title = (ArrayList<String>) request.getAttribute("title");
                                ArrayList<String> genre = (ArrayList<String>) request.getAttribute("genre");
                                ArrayList<String> year = (ArrayList<String>) request.getAttribute("year");
                            %>

                            <div class="table-responsive">

                                <table class="table table-bordered table-dark table-hover">
                                    <thead>

                                    <th> Title </th>
                                    <th> Genre </th>
                                    <th> Year </th>

                                    </thead>
                                <% for (int i=0; i < title.size(); i++) {
                                String ttl = title.get(i);
                                String gnr = genre.get(i);
                                String yr = year.get(i); %>
                                    <tr>

                                        <td> <%=ttl%> </td>
                                        <td> <%=gnr%> </td>
                                        <td> <%=yr%> </td>
                                        
                                    </tr>
                                <%}%>

                                </table>


                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>



        <div class="container" id="rightalign">

            <form class="form-inline" action="ctrl" method="post">


                <button class="btn btn-dark" type="submit" value="Delete"> <i class="fas fa-times-circle" aria-hidden="true" style="color:white"></i> </button>

                <input class="form-control form-control-sm ml-3 " type="text" placeholder="Delete" aria-label="Delete" name="delete">

            </form>


        </div>


        <!-- The Modal -->
        <div class="modal fade" id="myModal1">
            <div class="modal-dialog">
                <div class="modal-content">


                    <!-- Modal body -->
                    <div class="modal-body">

                        <table class="table table-borderless">

                            <tr>

                                <td style="color: black"> Title: </td>
                                <td> <input type="text" class="form-control"> </td>

                            </tr>

                            <tr>

                                <td style="color: black"> New Title: </td>
                                <td> <input type="text" class="form-control"> </td>

                            </tr>



                        </table>

                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn" style="background-color: greenyellow;" >Save Changes</button>
                    </div>

                </div>
            </div>
        </div>

        <!-- The Modal -->
        <div class="modal fade" id="myModal2">
            <div class="modal-dialog">
                <div class="modal-content">


                    <!-- Modal body -->
                    <div class="modal-body">

                        <table class="table table-borderless">

                            <tr>

                                <td style=" color: black;"> Genre: </td>
                                <td> <input type="text" class="form-control"> </td>

                            </tr>

                            <tr>

                                <td style="color: black"> New Genre: </td>
                                <td> <input type="text" class="form-control"> </td>

                            </tr>



                        </table>

                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn" style="background-color: greenyellow;" data-dismiss="modal">Save Changes</button>
                    </div>

                </div>
            </div>
        </div>

        <!-- The Modal -->
        <div class="modal fade" id="myModal3">
            <div class="modal-dialog">
                <div class="modal-content">


                    <!-- Modal body -->
                    <div class="modal-body">

                        <table class="table table-borderless">

                            <tr>

                                <td style="color: black"> Year: </td>
                                <td> <input type="text" class="form-control"> </td>

                            </tr>

                            <tr>

                                <td style="color: black"> New Year: </td>
                                <td> <input type="text" class="form-control"> </td>

                            </tr>



                        </table>

                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn" style="background-color: greenyellow;" data-dismiss="modal">Save Changes</button>
                    </div>

                </div>
            </div>
        </div>

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

    </body>

</html>
