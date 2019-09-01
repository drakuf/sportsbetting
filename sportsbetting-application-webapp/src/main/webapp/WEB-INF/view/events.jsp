<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Best Sports Betting Page Ever</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="<c:url value = "/resources/styles.css"/>">
</head>
<body>
	<header>
		<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">SportsBetting</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="home">Home</a>
					</li>
					<li class="nav-item active"><a class="nav-link" href="events">Events
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Language </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">English</a> <a
								class="dropdown-item" href="#">Magyar</a>
						</div></li>
				</ul>
				<form action="logout" class="form-inline my-2 my-lg-0">
					<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Logout</button>
				</form>
			</div>
		</nav>
	</header>
	<div class="container">
		<br>
		<div class="card">
			<h5 class="card-header">Events</h5>
			<div class="card-body">
				<table class="table table-responsive-lg">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Event title</th>
							<th scope="col">Event type</th>
							<th scope="col">Event start</th>
							<th scope="col">Event end</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:set var="count" value="1" scope="page" />
							<c:forEach items="${event}" var="event">
								<tr>
									<th scope="row">${count}</th>
									<td>${event.title}</td>
									<td>Football match</td>
									<td>${event.startDate}</td>
									<td>${event.endDate}</td>
									<td><a href="<c:url value='/bets/${event.id}' />">
											<button type="submit" id="bets"
												class="btn btn-primary btneffect">Bets</button>
									</a></td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page" />
							</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
</html>