<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, css, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Informations| </title>

    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
  </head>

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
       
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">

          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Infos sur ce voyage</h3>
              </div>

              <div class="title_right">
               
              </div>
            </div>
            
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_title">
                   
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  
                  
                 <h3 class="prod_title">${ voyage.getDestination() } Amazing Tour</h3>
                    <div class="col-md-7 col-sm-7 ">
                      <div class="product-image">
                      
                        <img src="data:image/jpg;base64, ${voyage.base64Image}" alt="..." />
                        
                        
                      </div>
                   
                    </div>
                    
                 
                    <div class="col-md-5 col-sm-5 " style="border:0px solid #e5e5e5;">

 
                       
                       <div class="travel">
                        
                        
                        
                              <ul class="list-unstyled">
                                <li style="font-size:1.5vw"><i class="fa fa-sun-o" hidden="hidden"></i> <strong> ID: </strong> ${ voyage.getId() }</li>
                                
                               
                               
                                <li style="font-size:1.5vw"><i class="fa fa-adjust"></i> <strong>Durée du voyage : </strong>${ voyage.getDuree() } Jours<br></li>
                               
                                <li style="font-size:1.5vw"><i class="fa fa-sun-o"></i> <strong> Date de départ : </strong> ${ voyage.getDate_depart() }<br></li>
                                <li style="font-size:1.5vw"><i class="fa fa-check"></i><strong> Type de voyage :  </strong>${ voyage.getType_voyage()} <br></li>
                                <li style="font-size:1.5vw"><i class="fa fa-phone"></i> <strong>Destination du voyage: </strong>${ voyage.getDestination() } <br></li>
                                <li style="font-size:1.5vw"><i class="fa fa-map-marker"></i> <strong> Prix : </strong>${ voyage.getPrix() } DHS<br></li>
                                
                                <li style="font-size:1.5vw"><i class="fa fa-map"></i><strong> Thèmes du voyage :  </strong>  
                                <c:forEach items="${themes}" var="theme">
                                 <ul>
                                 <li> ${ theme.getNom() } </li>
                                 </ul>
                                 </c:forEach>
                                </li>
                                <li style="font-size:1.5vw"><i class="fa fa-map"></i> <strong>Activités du voyage </strong>${ activite.getId()}
                                <c:forEach items="${activites}" var="activite">
                                 <ul>
                                 <li> ${ activite.getNom() } </li>
                                 </ul>
                                 </c:forEach>
                                </li>
                                
                               
                                
                                
                                
                                
                              </ul>
                              
                              <div class=" bottom text-center">
                           
                           
                          </div>
                     </div>
                    </div>
                   </div>
                


                
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
            NOTRE AGENCE | BGI <a href="https://www.umi.ac.ma"></a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
   <script src="vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>
  </body>
</html>