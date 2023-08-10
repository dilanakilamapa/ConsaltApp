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
</head>

<body class="background-image-div">
  <header>
    <nav class="navbar  navbar-expand-lg  bg-dark fixed-top">
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
            <a class="nav-link green" href="#">Appoinment</a>
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
        <h1 class="text-center text-uppercase mb-5" style="margin-bottom: 100px;">Why Choose Colombo Consultant Center?
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
                you're seeking a position in engineering, healthcare, IT, hospitality, or any other field, we've got you
                covered.</p>
            </div>
          </div>
        </div>
        <div class="col-3">
          <div class="card text-center" style="width: 18rem; height: 20rem;">
            <div class="card-body">
              <h4 class="card-title">Visa Assistance</h4>
              <p class="card-text"> Navigating the complex world of visa applications can be daunting. Our experts will
                guide you through the visa application process, ensuring that you're well-prepared and informed every
                step
                of the way.</p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section id="Appoinment">
      <form action="" method="post">
        <div class="row justify-content-center">
          <div class="col-9 mb-5 ">
            <div class="card p-5">
              <div class="card-hedding">
                <h2>Make an Appoinment</h2>
              </div>
              <div class="row">
                <div class="col-6">
                  <span class="">First name</span>
                  <input class="form-control form-control-sm" type="text" name="F_name" id="F_name">
                </div>
                <div class="col-6">
                  <span class="">Last name</span>
                  <input class="form-control form-control-sm" type="text" name="L_name" id="L_name">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Email</span>
                  <input class="form-control form-control-sm" type="email" name="email" id="email">
                </div>
                <div class="col-6">
                  <span class="">Phone Number</span>
                  <input class="form-control form-control-sm" type="number" name="phone_number" id="phone_number">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Select Country</span>
                  <select class="form-select" name="Country" id="Country">
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>
                </div>
                <div class="col-6">
                  <span class="">Select Job</span>
                  <select class="form-select" name="Job" id="Job">
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Select Consultant</span>
                  <select class="form-select" name="Consultant" id="Consultant">
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>
                </div>
              </div>
              <div class="row mt-3">
                <div class="input-group">
                  <span class="input-group-text">Note</span>
                  <textarea class="form-control" name="Note" id="Note"
                    placeholder="If you have an additional message to add, write here. Not mandatory."></textarea>
                </div>
              </div>
              <div class="row mt-3 justify-content-center">
                <div class="col-3">
                  <input class="btn btn-dark" type="submit" value="Book">
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
            <i class="ion-ios-heart" aria-hidden="true"></i> by dilanMapa.com
          </p>
        </div>
      </div>
    </div>
  </footer>
</body>

</html>