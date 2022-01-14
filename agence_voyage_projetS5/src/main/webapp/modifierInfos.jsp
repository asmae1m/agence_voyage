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
        <br>
        <div class="right_col" role="main">
          <div class="">

            <div class="clearfix"></div>
<br>
            <div class="row">
            <br>
               
                
                    <div class="col-12 grid-margin">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Vos informations personnelles</h4>
                    <br>
                    <br>
                    <form class="class-form" action ="modifierInfos" method="POST" >
                        
                   
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label" >identifiant : </label>
                            <div class="col-sm-9">
                           <input  class="form-control" name="id" value='${client1.id}'>
                            </div>
                         
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Adresse</label>
                            <div class="col-sm-9">
                              <input class="form-control" name="adresse" value='${client1.adresse}' required="required">
                            </div>
                          </div>
                        </div>
                      </div>
                     
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Nationalité</label>
                            <div class="col-sm-9">
                               <input class="form-control" name="nationalite" value='${client1.nationalite}'>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Nom</label>
                            <div class="col-sm-9">
                              <input class="form-control" name="nom" value='${client1.nom}'>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Prénom</label>
                            <div class="col-sm-9">
                              <input class="form-control" name="prenom" value='${client1.prenom}'>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">N°telephone</label>
                            <div class="col-sm-9">
                             <input class="form-control" name="telephone" value='${client1.telephone}'>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Code Postal</label>
                            <div class="col-sm-9">
                              <input class="form-control" name="codePostal" value='${client1.codePostal}'>
                            </div>
                          </div>
                        </div>
                        
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Email</label>
                            <div class="col-sm-9">
                               <input class="form-control" name="email" value='${client1.email}'>
                            </div>
                          </div>
                        </div>
                      </div>
                      <br>
                      <div class="col-12 grid-margin stretch-card">
                   
                  </div>
                  <button class="btn btn-success mr-2" formaction="editInfos" formaction="post" >Sauvegardez les modifications</button>
                    </form>    
                   <a href="homeClient.jsp" class="btn btn-light">Retour à la page principale</a>
                    
                    
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
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