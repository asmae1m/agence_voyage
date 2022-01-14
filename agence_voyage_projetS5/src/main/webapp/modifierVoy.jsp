<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Star Admin Premium Bootstrap Admin Dashboard Template</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="assets/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="assets/vendors/iconfonts/ionicons/dist/css/ionicons.css">
    <link rel="stylesheet" href="assets/vendors/iconfonts/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.addons.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="assets/css/shared/style.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="assets/css/demo_1/style.css">
    <!-- End Layout styles -->
    <link rel="shortcut icon" href="assets/images/favicon.ico" />
  </head>
  
  <body>
    <div class="container-scroller">
      <!-- partial:partials/_navbar.html -->
      <jsp:include page="TopNavBarAdmin.jsp" />
      <!-- partial -->
      <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_sidebar.html -->
        <jsp:include page="sideBarAdmin.jsp" />
        <!-- partial -->
        <div class="main-panel">
        <div class="col-12 grid-margin">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Ajout de voyage</h4>
                    <form class="form-sample" action="modifierVoyage?id_voy=${ id_voy }" method="POST">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Destination</label>
                            <div class="col-sm-9">
                              <input type="text" name="destination" value="${ voy.getDestination() }" class="form-control" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Endroit de depart</label>
                            <div class="col-sm-9">
                              <input type="text" name="depart" value="${ voy.getEndroit_depart() }" class="form-control" />
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date de depart</label>
                            <div class="col-sm-9">
                              <input placeholder="dd/mm/yyyy" type="date" value="${ voy.getDate_depart() }" name="date_depart" class="form-control" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date de retour</label>
                            <div class="col-sm-9">
                              <input class="form-control" type="date" value="${ voy.getDate_arrivee() }" name="date_arrive" placeholder="dd/mm/yyyy" />
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Duree du voyage</label>
                            <div class="col-sm-9">
                              <input class="form-control" value="${ voy.getDuree() }" name="duree" placeholder="jours" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Type de voyage</label>
                            <div class="col-sm-9">
                              <select class="form-control" disabled>
                                <option>Circuit accompagné</option>
                                <option>Voyage en individuel</option>
                              </select>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="input-group">
                          <label class="col-sm-3 col-form-label">Prix</label>
                            <div class="input-group-prepend bg-primary border-primary">
                              <span class="input-group-text bg-transparent text-white">$</span>
                            </div>
                            <input name="prix" value="${voy.getPrix()}" class="form-control" aria-label="Amount (to the nearest dollar)">
                            <div class="input-group-append bg-primary border-primary">
                              <span class="input-group-text bg-transparent text-white">.00</span><br>
                            </div>
                          </div>
                        </div>
                      </div>
                      
                    
                        <button type="submit">modifier</button>
                    </form>
                  </div>
                </div>
              </div>
              <footer class="footer">
            <div class="container-fluid clearfix">
              <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © bootstrapdash.com 2020</span>
              <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"> Free <a href="https://www.bootstrapdash.com/bootstrap-admin-template/" target="_blank">Bootstrap admin templates</a> from Bootstrapdash.com</span>
            </div>
          </footer>
              </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="assets/vendors/js/vendor.bundle.base.js"></script>
    <script src="assets/vendors/js/vendor.bundle.addons.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page-->
    <!-- End plugin js for this page-->
    <!-- inject:js -->
    <script src="assets/js/shared/off-canvas.js"></script>
   
    <!-- endinject -->
    <!-- Custom js for this page-->
    <script src="assets/js/demo_1/dashboard.js"></script>
    <!-- End custom js for this page-->
    <script src="assets/js/shared/jquery.cookie.js" type="text/javascript"></script>
  </body>
</html>