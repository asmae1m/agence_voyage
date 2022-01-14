<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import  ="beans.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>Notre Agence</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="css/style.css">
      <!-- Responsive-->
      <!-- fevicon -->
      <link rel="icon" href="images/fevicon.png" type="image/gif" />
      <!-- Scrollbar Custom CSS -->
      <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
      <!-- Tweaks for older IEs-->
      <!-- owl stylesheets --> 
      <link rel="stylesheet" href="css/owl.carousel.min.css">
      <link rel="stylesheet" href="css/owl.theme.default.min.css">
      
      <link rel="stylesheet" href="assets/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
      <link rel="stylesheet" href="assets/vendors/iconfonts/ionicons/dist/css/ionicons.css">
      <link rel="stylesheet" href="assets/vendors/iconfonts/flag-icon-css/css/flag-icon.min.css">
      <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
      <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.addons.css">
      <!-- endinject -->
      <!-- inject:css -->
      <link rel="stylesheet" href="assets/css/shared/style.css">
      <!-- endinject -->
      <!-- Layout styles -->
      <link rel="stylesheet" href="assets/css/demo_1/style.css">
      <!-- End Layout styles -->
      <link rel="shortcut icon" href="assets/images/favicon.ico" />
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
   </head>
   <!-- body -->
   <body class="main-layout">
      <!-- loader  -->
      <div class="loader_bg">
         <div class="loader"><img src ="images/loading.gif" alt="#" /></div>
      </div>
      <!-- end loader -->
      <!-- header -->
      <header>
         <!-- header inner -->
         <div class="header">
            <div class="header_white_section">
               <div class="container-fluid">
                  <div class="row">
                     <div class="col-md-12">
                        <div class="header_information">
                           <ul>
                              <li><img src="images/1.png" alt="#"/> Bachelor U.MY.ISMAIL</li>
                              <li><img src="images/2.png" alt="#"/> +212 707718122</li>
                              <li><img src="images/3.png" alt="#"/> notreagencebgi@hmail.com</li>
                           </ul>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="container">
               <div class="row">
                  <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                     <div class="full">
                        <div class="center-desk">
                           <div class="logo"> <a href="index.html"><img src="images/logo.png" alt="#"></a> </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
                     <div class="menu-area">
                        <div class="limit-box">
                           <nav class="main-menu">
                              <ul class="menu-area-main">
                                 <li class="active"> <a href="#">Trouver</a> </li>
                                 <li> <a href="#about">About</a> </li>
                                 <li><a href="#travel">Activités</a></li>
                                 <li><a href="#blog">Blog</a></li>
                                 <li><a href="voyagesClient">Nos offres</a></li>
                                 <li><a href="register.jsp">Sign Up</a></li>
                                 <li><a href="login.jsp">Sign In</a></li>
                              </ul>
                           </nav>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <!-- end header inner -->
      </header>
      <!-- end header -->
      <section >
         <div class="banner-main">
            <img src="images/banner.jpg" alt="#"/>
            <div class="container">
               <div class="text-bg">
                  <h1>Voyagez<br><strong class="white">où vous voulez</strong></h1>
                  <div class="button_section"> <a class="main_bt" href="#">Read More</a>  </div>
                  <div class="container">
                     <form action="searchNoConn" method="POST" class="main-form">
                        <h3>Find Your Tour</h3>
                        <div class="row">
                           <div class="col-md-12">
                              <div class="row">
                                 <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <label >Thème</label>
                                    
                                    <select class="form-control" name="theme" >
                                    <option>---</option>
                                    <option> Haute-montagne</option>
                                    <option> Croisières</option>
                                    <option> Terres polaires</option>
                                    <option> Aventures</option>
                                    <option> Voyage neige</option>
                                    <option> Chemins de St-Jaques</option>
                                    </select>
                                   
                                 </div>
                                
                                 <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <label >Activités</label>
                                    <select class="form-control" name="activite">
                                    <option>---</option>
                                    <option> Randonnée</option>
                                    <option> Trek </option>
                                    <option> Safari</option>
                                    <option> Observation animaux</option>
                                    <option> Raquette</option>
                                    <option> Ski de fond / ski nordique</option>
                                    <option> Ski de randonnée / Freeride</option>
                                    <option> Traineau à chiens</option>
                                    <option> Alpinisme</option>
                                    <option> Velo</option>
                                    <option> Kayak et canoe </option>
                                    <option> Decouverte</option>
                                    </select>
                                 </div>
                                 
                                 <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <label >Destination</label>
                                    <select class="form-control" name="destination" >
                                    <option>---</option>
                                    <option> meknes</option>
                                    <option> khenifra</option>
                                    <option> rabat</option>
                                    <option> marrakech</option>
                                    <option>eljadida</option>
                                    <option>ouarzazate</option>
                                    <option>fes</option>
                                    
                                    </select>
                                 </div>
                                 <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <label >Date de départ </label>
                                    <input value="date" class="form-control"  min="<%= new java.sql.Date(System.currentTimeMillis()) %>" type="date" name="date_depart" >
                                 </div>
                                 <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <label >Durée </label>
                                    <input value="---"class="form-control" placeholder="jours" type="text" name="duree" >
                                 </div>
                                 <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <label >Type de voyage</label>
                                    <select class="form-control" name="type" required="required">
                                       <option>---</option>
                                       <option>Circuit accompagné</option>
                                       <option>Voyage en individuel</option>
                                    </select>
                                 
                              </div>
                              <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <label >Votre Budget </label>
                                    <input value="0"class="form-control" placeholder="jours" type="text" name="budget" >
                                 </div>
                             
                           </div>
                           
                              <input type="submit" value="Rechercher">
                           </div>
                           
                        </div>
                     </form>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <!-- about -->
      <div id="blog" class="blog">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="titlepage">
                     <h2>Nos voyages</h2>
                     <span>CI-dessous nos voyages, si vous chercher un voyage spécifique utilisez les filtres de recherche!</span> 
                  </div>
               </div>
            </div>
            <div class="row">
            <c:forEach items="${list}" var="voyage">
               <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                  <div class="blog-box">
                     <figure><img src="data:image/jpg;base64, ${voyage.base64Image}" alt="#"/>
                     <span>${ voyage.getDate_depart() }</span>
                     </figure>
                     <div class="travel">
                        <span>Destination :  ${ voyage.getDestination() }</span> 
                        <p><strong class="Comment"> ${ voyage.getPrix() } DHS</strong>  Prix</p>
                        <h3>${ voyage.getDestination() } Amazing Tour</h3>
                              <ul class="list-unstyled">
                                <li><i class="fa fa-car"></i><strong> Date d'arrivée: </strong>${ voyage.getDate_arrivee() } </li>
                                <li><i class="fa fa-phone"></i> <strong>Durée du voyage : </strong>${ voyage.getDuree() } jours</li>
                                <li><i class="fa fa-map-marker"></i> <strong> Endroit de départ : </strong>${ voyage.getEndroit_depart() }</li>
                                <li><i class="fa fa-sun-o"></i> <strong> Type de voyage : </strong> ${ voyage.getType_voyage() }</li>
                                
                              </ul>
                              <div class=" bottom text-center">
                           
                           
                            <div class=" col-sm-7 emphasis">
                            <button onclick="window.location.href = 'consulterVoyage?id=${ voyage.getId() }';" type="submit" class="btn btn-success btn-sm">
                                <i class="fa fa-anchor"> </i> Consult
                              </button>
                              <button onclick="window.location.href = 'login.jsp';" type="submit" class="btn btn-primary btn-sm">
                                <i class="fa fa-plus"> </i> Ajouter au panier
                              </button>
                              
                            </div>
                          </div>
                     </div>
                     
                  </div>
               </div>
               </c:forEach>
            </div>
         </div>
      </div>
      <!-- end about -->
      <!-- traveling -->
      
      <!-- end traveling -->
      <!--London -->
      
      <!-- end London -->
      <!--Tours -->
      
      <!-- end Tours -->
      <!-- Amazing -->
      
      <!-- end Amazing -->
      <!-- our blog -->
      
      <!-- end our blog -->
      <!-- footer -->
      <footer>
         <div id="contact" class="footer" style="background-color:#3555dc;">
            <div class="container">
               <div class="row pdn-top-30">
                  <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                     <ul class="location_icon">
                        <li> <a href="#"><img src="icon/facebook.png"></a></li>
                        <li> <a href="#"><img src="icon/Twitter.png"></a></li>
                        <li> <a href="#"><img src="icon/linkedin.png"></a></li>
                        <li> <a href="#"><img src="icon/instagram.png"></a></li>
                     </ul>
                  </div>
                  <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
                     <div class="Follow">
                        <h3>CONTACT US</h3>
                        <span>123 Second Street Fifth <br>Avenue,<br>
                        Manhattan, New York<br>
                        +987 654 3210</span>
                     </div>
                  </div>
                  <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
                     <div class="Follow">
                        <h3>ADDITIONAL LINKS</h3>
                        <ul class="link">
                           <li> <a href="#">About us</a></li>
                           <li> <a href="#">Terms and conditions</a></li>
                           <li> <a href="#"> Privacy policy</a></li>
                           <li> <a href="#">News</a></li>
                           <li> <a href="#"> Contact us</a></li>
                        </ul>
                     </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                     <div class="Follow">
                        <h3> Contact</h3>
                        <form action="sendEmail" method="post">
                        <div class="row">
                           <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
                              <input class="Newsletter" name="subjet" placeholder="Sujet" type="text">
                           </div>
                           <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
                              <input class="Newsletter" name="email" placeholder="Email" type="text">
                           </div>
                           <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
                              <input class="Newsletter" name="password" placeholder="code de votre adresse gmail" type="password">
                           </div>
                           <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                              <textarea class="textarea" name="commentaire" placeholder="commentaire" type="text">Commentaire</textarea>
                           </div>
                        </div>
                        <button class="Subscribe">Submit</button>
                        </form>
                     </div>
                  </div>
               </div>
               <div class="copyright">
                  <div class="container">
                   
                  </div>
               </div>
            </div>
         </div>
      </footer>
      <!-- end footer -->
      <!-- Javascript files-->
      <script src="js/jquery.min.js"></script>
      <script src="js/popper.min.js"></script>
      <script src="js/bootstrap.bundle.min.js"></script>
      <script src="js/jquery-3.0.0.min.js"></script>
      <!--<script src="js/plugin.js"></script>-->
      <!-- sidebar -->
      <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="js/custom.js"></script>
      <!-- javascript --> 
      <script src="js/owl.carousel.js"></script>
      <script>
         $(document).ready(function() {
           var owl = $('.owl-carousel');
           owl.owlCarousel({
             margin: 10,
             nav: true,
             loop: true,
             responsive: {
               0: {
                 items: 1
               },
               600: {
                 items: 2
               },
               1000: {
                 items: 3
               }
             }
           })
         })
      </script>
    
   </body>
</html>