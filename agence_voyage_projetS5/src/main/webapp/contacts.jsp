<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Contact Form | Gentelella Alela! by Colorlib</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.10.2/css/all.css" />
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css" />
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
  </head>
<% if(session.getAttribute("client1")==null){ 
        response.sendRedirect("login.jsp");} 
   	 %>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="afficherVoyages?id_client=${client1.getId() }" class="site_title"><i class="fa fa-money"></i> <span>Nos offres</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <div class="nav toggle">
                  <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                </div>
                
            </div>
          </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Votre panier</h3>
              </div>

            </div>

            <div class="clearfix"></div>

            <div class="row">
                <div class="x_panel">
                  <div class="x_content">
                      
                      <div class="clearfix"></div>
					<p style="color:red; text-align:center;"><c:out value="${zero }"></c:out></p>
                    <c:forEach items="${list}" var="voyage">
                      <div class="col-md-6 col-sm-6 profile_details">
                        <div class="well profile_view">
                          <div class="col-sm-12">
                            <h4 class="brief"><i>Informations du voyage</i></h4>
                            <div class="left col-sm-7">
                              <h2>Destination :  ${ voyage.getDestination() }</h2>
                              
                              <br>
                              <ul class="list-unstyled">
                                <li><i class="fa fa-plane"></i><strong> Date de départ: </strong>${ voyage.getDate_depart() } </li>
                                <li><i class="fa fa-car"></i><strong> Date d'arrivée: </strong>${ voyage.getDate_arrivee() } </li>
                                <li><i class="fa fa-phone"></i> <strong>Durée du voyage : </strong>${ voyage.getDuree() } jours</li>
                                <li><i class="fa fa-map-marker"></i> <strong> Endroit de départ : </strong>${ voyage.getEndroit_depart() }</li>
                                <li><i class="fa fa-money"></i> <strong>Prix du voyage : </strong>${ voyage.getPrix() } </li>
                                <li><i class="fa fa-sun-o"></i> <strong> Type de voyage : </strong> ${ voyage.getType_voyage() }</li>
                                
                              </ul>
                            </div>
                            <div class="right col-sm-5 text-center">
                              <img src="data:image/jpg;base64, ${voyage.base64Image}" alt="" height = "500" width="300" class="img-edge img-fluid">
                            </div>
                          </div>
                          <div class=" bottom text-center">
                           
                            <div class=" col-sm-7 emphasis">
                              <button type="button" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#modalForm"> <i class="fa fa-user">
                                </i> <i class="fa fa-comments-o"></i> Reserver</button>
                              <button onclick="window.location.href = 'supprimerPanier?id_client=${client1.getId() }&idVoy=${ voyage.getId() }';" type="button" class="btn btn-primary btn-sm">
                                <i class="fa fa-user"> </i> Supprimer
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
<!-- Modal -->
<div class="modal fade" id="modalForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Reservation</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="reserverVoyage?idVoy=${voyage.getId() }&id_client=${client1.getId()}" method="POST">
                    <div class="mb-3">
                        <label class="form-label">Hebergement voulu</label>
                        <select name="hebergement" >
                        <option value="Chambre hotel">Chambre hotel</option>
                        <option value="Maison d'hote">Maison d'hote</option>
						<option value="Chalet">Chalet</option>
                        </select>
                    </div>
                    <div class="modal-footer d-block">
                        <button type="submit" class="btn btn-warning float-end">Reserver</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
                    </c:forEach>
                  </div>
                </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
         
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="https://www.markuptag.com/bootstrap/5/js/bootstrap.bundle.min.js"></script>
   <script src="vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>
  </body>
</html>