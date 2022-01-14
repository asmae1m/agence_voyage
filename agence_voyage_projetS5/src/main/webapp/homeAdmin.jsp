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
  <% if(session.getAttribute("admin")==null){ 
        response.sendRedirect("login.jsp");} 
   	 %>
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
                    <form class="form-sample" action="ajoutVoyage" method="POST">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Destination</label>
                            <div class="col-sm-9">
                              <input type="text" name="destination" class="form-control" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Endroit de depart</label>
                            <div class="col-sm-9">
                              <input type="text" name="depart" class="form-control" />
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date de depart</label>
                            <div class="col-sm-9">
                              <input placeholder="dd/mm/yyyy" type="date" name="date_depart" class="form-control" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date de retour</label>
                            <div class="col-sm-9">
                              <input class="form-control" type="date" name="date_arrive" placeholder="dd/mm/yyyy" />
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Duree du voyage</label>
                            <div class="col-sm-9">
                              <input class="form-control"  name="duree" placeholder="jours" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Type de voyage</label>
                            <div class="col-sm-9">
                              <select class="form-control" name="type">
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
                            <input name="prix" class="form-control" aria-label="Amount (to the nearest dollar)">
                            <div class="input-group-append bg-primary border-primary">
                              <span class="input-group-text bg-transparent text-white">.00</span>
                            </div>
                          </div>
                        </div>
                      </div>
                      
                    <p class="card-description"><br/>Thèmes</p>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <div class="form-check">
                              <label class="form-check-label">
                                <input type="checkbox" name="themes" value="Haute-montagne" class="form-check-input"> Haute-montagne </label>
                            </div>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input type="checkbox" name="themes" value="Croisières" class="form-check-input"> Croisières </label>
                            </div>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input type="checkbox" name="themes" value="Terres polaires" class="form-check-input"> Terres polaires </label>
                            </div>
                            
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group">
                            <div class="form-check">
                              <label class="form-check-label">
                                <input type="checkbox" name="themes" value="Aventures" class="form-check-input"> Aventures </label>
                            </div>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input type="checkbox" name="themes" value="Voyage neige" class="form-check-input"> Voyage neige </label>
                            </div>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input type="checkbox" name="themes" value="Chemins de St-Jaques" class="form-check-input"> Chemins de St-Jaques </label>
                            </div>
                          </div>
                        </div>
                      </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Membership</label>
                            <div class="col-sm-4">
                              <div class="form-radio">
                                <label class="form-check-label">
                                  <input type="radio" class="form-check-input" name="membershipRadios" id="membershipRadios1" value="" checked> Free </label>
                              </div>
                            </div>
                            <div class="col-sm-5">
                              <div class="form-radio">
                                <label class="form-check-label">
                                  <input type="radio" class="form-check-input" name="membershipRadios" id="membershipRadios2" value="option2"> Professional </label>
                              </div>
                            </div>
                          </div>
                        </div>
                        
                        <button type="submit">ajouter</button>
                    </form>
                  </div>
                </div>
              </div>
              <footer class="footer">
            <div class="container-fluid clearfix">
             
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
    <script src="assets/js/shared/misc.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page-->
    <script src="assets/js/demo_1/dashboard.js"></script>
    <!-- End custom js for this page-->
    <script src="assets/js/shared/jquery.cookie.js" type="text/javascript"></script>
  </body>
</html>