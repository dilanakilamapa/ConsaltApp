<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="ionicons/css/ionicons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  </head>

  <body class="background-image-div">
    <header>
      <nav class="navbar  navbar-expand-lg bg-light  fixed-top">
        <a class="navbar-brand ms-3 green" href="#">COLOMBO</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item active">
              <a class="nav-link green" href="#home">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link green" href="#Why">Why Choose</a>
            </li>
            <li class="nav-item">
              <a class="nav-link green" href="#Appoinment">Appoinment</a>
            </li>
            <li class="nav-item">
              <a class="nav-link green" href="#">Country</a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
    <div class="container-fluid">
      <section id="home">
        <div class="row mb-5">
          <div class="col-md-7 text" style="margin-bottom: 18rem; margin-left: 3rem; margin-top: 5rem;">
            <h2 class=" text-uppercase mt-3 footer-heading">Welcome to </h2>
            <h1>Colombo Consultant Center</h1>
            <h5>Your Pathway to Abroad Opportunities!</h5>
          </div>
        </div>
      </section>
      <section id="Why">
        <div class="row mt-5 mb-5 justify-content-center">
          <h1 class="text-center text-uppercase mb-5" style="margin-bottom: 100px;">Why Choose Colombo Consultant
            Center?
          </h1>
          <div class="col-3">
            <div class="card text-center" style="width: 18rem;height: 20rem;">
              <div class="card-body">
                <h4 class="card-title">Comprehensive Guidance</h4>
                <p class="card-text"> Our consultants possess a wealth of knowledge about various industries and global
                  job
                  markets. We'll work closely with you to understand your skills, preferences, and career goals, helping
                  you
                  identify the best-suited opportunities abroad.</p>
              </div>
            </div>
          </div>
          <div class="col-3">
            <div class="card text-center" style="width: 18rem;height: 20rem;">
              <div class="card-body">
                <h4 class="card-title">Tailored Solutions</h4>
                <p class="card-text ">We recognize that each jobseeker is unique. Our consultants take the time
                  to
                  personalize our advice and recommendations to match your individual aspirations and circumstances.
                  Whether
                  you're seeking a position in engineering, healthcare, IT, hospitality, or any other field, we've got
                  you
                  covered.</p>
              </div>
            </div>
          </div>
          <div class="col-3">
            <div class="card text-center" style="width: 18rem; height: 20rem;">
              <div class="card-body">
                <h4 class="card-title">Visa Assistance</h4>
                <p class="card-text"> Navigating the complex world of visa applications can be daunting. Our experts
                  will
                  guide you through the visa application process, ensuring that you're well-prepared and informed every
                  step
                  of the way.</p>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section id="Appoinment">
        <form action="IndexServelet?parameter=Add" method="post">
          <div class="row justify-content-center">
            <div class="col-9 mb-5 ">
              <div class="card p-5">
                <div class="card-hedding">
                  <h2>Make an Appoinment</h2>
                </div>
                <div class="row">
                  <div class="col-6">
                    <span class="">First name</span>
                    <input class="form-control form-control-sm" type="text" name="F_name" id="F_name" value="${newJobseeker.getFirst_Name()}">
                  </div>
                  <div class="col-6">
                    <span class="">Last name</span>
                    <input class="form-control form-control-sm" type="text" name="L_name" id="L_name" value="${newJobseeker.getLast_Name()}">
                  </div>
                </div>
                <div class="row mt-3">
                  <div class="col-6">
                    <span class="">Email</span>
                    <input class="form-control form-control-sm" type="email" name="email" id="email" value="${newJobseeker.getEmail()}">
                  </div>
                  <div class="col-6">
                    <span class="">Phone Number</span>
                    <input class="form-control form-control-sm" type="number" name="phone_number" id="phone_number" value="${newJobseeker.getPhone_Number()}">
                  </div>
                </div>
                <div class="row mt-3">
                  <div class="col-6">
                    <span class="">Select Country</span>
                    <select class="form-select" name="Country" id="Country">
                      <option value="0">01. Select Country</option>
                      <c:forEach var="specialization" items="${specializations}">
                        <option value="${specialization.getCountry_id()}">${specialization.getCountry_Name()}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-6">
                    <span class="">Select Job</span>
                    <select class="form-select" name="Job" id="Job">
                      <option value="0">02. Select Job</option>
                    </select>
                  </div>
                </div>
                <div class="row mt-3">
                  <div class="col-6">
                    <span class="">Select Consultant</span>
                    <select class="form-select" name="Consultant" id="Consultant">
                      <option value="0">03. Select Consultant</option>
                    </select>
                  </div>
                  <div class="col-6">
                    <span class="">Available Date</span>
                    <select class="form-select" name="DateAvailabilityId" id="DateAvailabilityId">
                      <option value="0">02. Place Select Date </option>
                    </select>
                  </div>
                </div>
                <div class="row mt-3">
                  <div class="col-6">
                    <span class="">Available Time</span>
                    <select class="form-select" name="start_time" id="start_time">
                      <option value="0">03. Place Select Available Time </option>
                    </select>
                  </div>
                  <div class="col-6">
                    <div class="input-group">
                      <span class="input-group-text">Note</span>
                      <textarea class="form-control" name="Note" id="Note"
                        placeholder="If you have an additional message to add, write here. Not mandatory."></textarea>
                    </div>
                  </div>
                </div>
                <div class="row mt-3 justify-content-center">
                  <div class="col-3">
                    <input class="btn btn-dark" type="submit" value="Book">
                  </div>
                </div>
                <div class="row mt-3 justify-content-center">
                <div class="col-6">
	                <ul class="error text text-danger">
		                <c:forEach var="error" items="${errors}">
		                	<li>${error}</li>
		                </c:forEach>
	                </ul>
                </div>
                <div class="col-6">
	                <ul class="error text text-danger">
		                <c:forEach var="error" items="${errors1}">
		                	<li>${error}</li>
		                </c:forEach>
	                </ul>
                </div>
              </div>
                
              </div>
            </div>
          </div>
        </form>
      </section>
    </div>
    <footer class="bg-dark">
      <div class="container">
        <div class="row justify-content-center">
          <div class=" col-md-12 text-center">
            <h2 class="footer-heading logo green">
              Colombo
              Consultant Center
            </h2>
          </div>
        </div>
        <div class="row mt-5">
          <div class="col-md-12 text-center">
            <p class="copyright green">
              Copyright &copy;
              <script>document.write(new Date().getFullYear());</script> All rights reserved
               By DilanMapa.com
            </p>
          </div>
        </div>
      </div>
    </footer>
  </body>

  <script>
    function formatDate(date) {
      var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2)
        month = '0' + month;
      if (day.length < 2)
        day = '0' + day;

      return [year, month, day].join('-');
    }

    $(document).ready(function () {
      $("#Country").on("change", function () {
        var selectedValue = $(this).val();
        console.log(selectedValue);
        $.ajax({
          url: "<%=request.getContextPath()%>/SpecializationServlet",
          type: "GET",
          data: {
            parameter: "GetJobNameByCountry",
            Coun_id: selectedValue
          },
          success: function (data) {
            var job = $("#Job");
            job.empty();
            job.append($("<option>").val(0).text("02. Select Job"));
            $.each(data, function (index, item) {
              job.append($("<option>").val(item.Job_Title_id).text(item.Job_Name));
            });
          },
          error: function (xhr, status, error) {
            console.error("AJAX request error:", error);
          }
        });

      });

      $("#Job").on("change", function () {
        var JobValue = $(this).val();
        var countrySelect = document.getElementById("Country");
        var CountryValue = countrySelect.value;
        $.ajax({
          url: "<%=request.getContextPath()%>/SpecializationServlet",
          type: "GET",
          data: {
            parameter: "GetUserByCountryANDjob",
            Coun_id: CountryValue,
            Job_id: JobValue
          },
          success: function (data) {
            var Consultant = $("#Consultant");
            Consultant.empty();
            Consultant.append($("<option>").val(0).text("03. Select Consultant"));
            $.each(data, function (index, item) {
              Consultant.append($("<option>").val(item.User_ID).text(item.User_F_Name + " " + item.User_L_Name));
            });
          },
          error: function (xhr, status, error) {
            console.error("AJAX request error:", error);
          }
        });

      });

      $("#Consultant").on("change", function () {
        var selectedValue = $(this).val();
        console.log(selectedValue);
        $.ajax({
          url: "<%=request.getContextPath()%>/ConsultantAvailabilityServlet",
          type: "GET",
          data: {
            parameter: "getDates",
            id: selectedValue
          },
          success: function (data) {
            var dateAvailabilityId = $("#DateAvailabilityId");
            dateAvailabilityId.empty();
            $.each(data, function (index, item) {
              dateAvailabilityId.append($("<option>").val(item.DATE).text(item.DATE));
            });
          },
          error: function (xhr, status, error) {
            console.error("AJAX request error:", error);
          }
        });
      });

      $("#DateAvailabilityId").on("change", function () {
        var selectedValue = $(this).val();
        console.log(selectedValue);
        console.log(formatDate(selectedValue));
        $.ajax({
          url: "<%=request.getContextPath()%>/ConsultantAvailabilityServlet",
          type: "GET",
          data: {
            parameter: "getTime",
            date: formatDate(selectedValue)
          },
          success: function (data) {
            var start_time = $("#start_time");
            start_time.empty();
            $.each(data, function (index, item) {
              start_time.append($("<option>").val(item.ID).text(item.Start_Time + " --> " + item.End_Time));
            });
          },
          error: function (xhr, status, error) {
            console.error("AJAX request error:", error);
          }
        });
      });
    });
  </script>

  </html>