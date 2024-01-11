<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bookstore</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
	integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="data:image/png;base64,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"
				alt="Bootstrap" height="40">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Combo
							giảm giá</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Thể loại </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Quần Jean</a></li>
							<li><a class="dropdown-item" href="#">Áo thun</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Áo sơ mi</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link disabled">Hết hàng</a>
					</li>
				</ul>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search"
						placeholder="Nội dung tìm kiếm" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Tìm</button>
					
					<%
						Object obj = session.getAttribute("khachHang");
						KhachHang khachHang = null;
						if(obj!=null){
							// ép kiểu biến obj thành kiểu khách hàng
							khachHang = (KhachHang)obj;
						}
						if(khachHang==null){
							
					%>
					
					<a class="btn btn-primary" style="white-space: nowrap;" href="khachhang/dangnhap.jsp">
						Đăng nhập
					</a>
					
					<%} else { %>
					
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor">
						<li class="nav-item dropdown dropstart">
							<a class="nav-link dropdown-toggle" href="#" role="button"data-bs-toggle="dropdown" 
							aria-expanded="false"><b> <%= khachHang.getHoVaTen() %> </b> </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">Đơn hàng của tôi</a></li>
								<li><a class="dropdown-item" href="#">Thông báo</a></li>
								<li><a class="dropdown-item" href="khachhang/thaydoithongtin.jsp">Thay đổi thông tin</a></li>
								<li><a class="dropdown-item" href="khachhang/doimatkhau.jsp">Đổi mật khẩu</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="khach-hang?hanhDong=dang-xuat&">Đăng xuất</a></li>
							</ul></li>
						</li>
					</ul>
					
					<!--
					<div class="row text-center" style="margin-left: 0.05em">
						<div class="row"> <b> <%= khachHang.getHoVaTen() %> </b> </div>
						<div class="row">
							<a style="white-space: nowrap;" href="dang-xuat">Đăng xuất</a>
						</div>
					</div>
					-->
					
					<% } %>
					
				</form>
			</div>
		</div>
	</nav>
	<!-- End Navbar -->

	<!-- Page content -->
	<div class="container mt-4">
		<div class="row">
			<!-- Menu left -->
			<div class="col-lg-3">
				<div class="list-group ">
					<a href="#" class="list-group-item list-group-item-action">
						Thời trang nam </a> <a href="#"
						class="list-group-item list-group-item-action">Thời trang nữ</a> <a
						href="#" class="list-group-item list-group-item-action">Dành
						cho bé</a>
				</div>
			</div>
			<!-- End Menu left -->

			<!-- Slider and Products -->
			<div class="col-lg-9">
				<!-- Slider -->
				<div id="carouselExampleIndicators" class="carousel slide mb-4"
					data-bs-ride="true">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="0" class="active" aria-current="true"
							aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="1" aria-label="Slide 2"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="2" aria-label="Slide 3"></button>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="img/slider/slider1.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/slider/slider2.img" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/slider/slider3.png" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
				<!-- End Slider -->
				<!-- Products -->
				<div class="row">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="img/slider/ao1.jpg"
								alt=""></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Áo thun Pro-S1</a>
								</h4>
								<h5>50.000</h5>
								<p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ
									màu sắc tốt.</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="img/slider/ao2.jpg"
								alt="" ></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Áo thun Pro-S1</a>
								</h4>
								<h5>50.000</h5>
								<p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ
									màu sắc tốt.</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small>
							</div>
						</div>
					</div>


					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="img/slider/ao3.jpg"
								alt="" ></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Áo thun Pro-S1</a>
								</h4>
								<h5>50.000</h5>
								<p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ
									màu sắc tốt.</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="img/slider/ao4.jpg"
								alt="" ></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Áo thun Pro-S1</a>
								</h4>
								<h5>50.000</h5>
								<p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ
									màu sắc tốt.</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="img/slider/ao5.jpg"
								alt="" ></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Áo thun Pro-S1</a>
								</h4>
								<h5>50.000</h5>
								<p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ
									màu sắc tốt.</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="img/slider/ao6.jpg"
								alt="" ></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Áo thun Pro-S1</a>
								</h4>
								<h5>50.000</h5>
								<p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ
									màu sắc tốt.</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small>
							</div>
						</div>
					</div>
				</div>
				<!-- End Products -->
			</div>
			<!-- End Slider and Products -->
		</div>
	</div>
	<!-- End Page content -->

	<!-- Footer -->
	<footer class="py-3 my-4">
		<ul class="nav justify-content-center border-bottom pb-3 mb-3">
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Trag chủ</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Quy định giao hàng</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Quy định trả hàng</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Liên hệ</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Blogs</a></li>
		</ul>
		<p class="text-center text-muted">© 2022 TITV.vn, Inc</p>
	</footer>
	<!-- End footer -->
	
	
	<div class="input-group mb-3">
	 
	  <div class="form-floating">
	    <input type="text" class="form-control" id="floatingInputGroup1" placeholder="Username">
	    <label for="floatingInputGroup1">Username</label>
	  </div>
	  
	   <span class="input-group-text">@gmail.com</span>
	</div>
</body>
</html>