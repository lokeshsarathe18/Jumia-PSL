<%@page import="java.util.Map.Entry"%>
<%@page import="com.jumia.bean.Product"%>
<%@page import="java.util.Map"%>
<%@page import="com.jumia.dao.ProductDAO"%>
<%@page import="com.jumia.bean.Order"%>
<%@page import="java.util.Set"%>
<%@page import="com.jumia.dao.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if ("admin".equals(session.getAttribute("user"))) {
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Admin - Jumia</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="admin_home.jsp">
				<div class="sidebar-brand-text mx-3">Admin - Jumia</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Menu</div>

			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="admin_home.jsp">
					<i class="fas fa-fw fa-chart-area"></i> <span>Recent Orders</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="admin_popular_orders.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>Popular Orders</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="admin_product_consumption.jsp"> <i
					class="fas fa-fw fa-table"></i> <span>Category wise
						consumption</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="admin_progress_report.jsp"> <i class="fas fa-fw fa-table"></i>
					<span>Progress Report</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="admin_logout.jsp">
					<i class="fas fas fa-sign-out-alt"></i> <span>Logout</span>
			</a></li>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<!-- Begin Page Content -->
					<div class="container-fluid">

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Most Popular
									Orders:</h6>
								products that are either delivered or in active order are
								considered for this list
							</div>
							<form action="admin_popular_orders.jsp" method="post">
								<input type="date" name="fromDate"
									value="<%=request.getParameter("fromDate")%>"> <input
									type="date" name="toDate"
									value="<%=request.getParameter("toDate")%>"> <input
									type="submit" name="submit" value="Get Popular Products">
							</form>
							<%
								if ("post".equalsIgnoreCase(request.getMethod())) {
										String fromDate = request.getParameter("fromDate");
										String toDate = request.getParameter("toDate");
										Map<Product, Integer> products = new ProductDAO().getPopularProductsBetween(fromDate, toDate);
							%>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%"
										cellspacing="0">
										<thead>
											<tr>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Product Description</th>
												<th>Price</th>
												<th>Category Name</th>
												<th>Units Available</th>
												<th>Units Sold</th>
											</tr>
										</thead>
										<tbody>
											<%
												if (products != null) {
															for (Entry<Product, Integer> entry : products.entrySet()) {
																Product p = entry.getKey();
											%>
											<tr>
												<td><%=p.getCode()%></td>
												<td><%=p.getName()%></td>
												<td><%=p.getDescription()%></td>
												<td><%=p.getPrice()%></td>
												<td><%=p.getCategory_name()%></td>
												<td><%=p.getUnits()%></td>
												<td><%=entry.getValue()%></td>
											</tr>
											<%
												}
														}
											%>
										</tbody>
									</table>
								</div>
							</div>
							<%
								}
							%>
						</div>
					</div>
					<!-- /.container-fluid -->
				</div>
				<!-- End of Main Content -->
			</div>
			<!-- End of Content Wrapper -->
		</div>
		<!-- End of Page Wrapper -->

		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fas fa-angle-up"></i>
		</a>

		<!-- Bootstrap core JavaScript-->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

		<!-- Core plugin JavaScript-->
		<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="js/sb-admin-2.min.js"></script>

		<!-- Page level plugins -->
		<script src="vendor/chart.js/Chart.min.js"></script>

		<!-- Page level custom scripts -->
		<script src="js/demo/chart-area-demo.js"></script>
		<script src="js/demo/chart-pie-demo.js"></script>
</body>
</html>
<%
	} else {
%>
<jsp:forward page="admin_login.jsp"></jsp:forward>
<%
	}
%>