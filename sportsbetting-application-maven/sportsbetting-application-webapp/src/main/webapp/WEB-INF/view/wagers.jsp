<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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

<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/styles.css">
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
					<li class="nav-item"><a class="nav-link"
						href="${contextPath}/home">Home</a></li>
					<li class="nav-item active"><a class="nav-link" href="${contextPath}/events">Events
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
				<form action="${contextPath}/logout"
					class="form-inline my-2 my-lg-0">
					<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Logout</button>
				</form>
			</div>
		</nav>
	</header>
	<div class="container">
		<br>
		<div class="card">
			<h5 class="card-header">${bet.event.title}
				${bet.event.startDate} ${bet.event.endDate} ${bet.type}</h5>
			<div class="card-body">
				<form:form action="${contextPath}/savewager/${outcome.get(0).id}"
					modelAttribute="outcome" method="post">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="name">Outcome</span>
						</div>
						<form:select path="" name="outcome" class="custom-select" id="outcome">
							<form:options items="${outcome}" itemValue="id"
								itemLabel="description" />
						</form:select>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">Amount</span>
						</div>
						<input type="number" id="amount" name="amount"
							class="form-control" />
					</div>
					<button type="submit" id="saveButton" name="saveButton"
						class="btn btn-primary btneffect">Save</button>
				</form:form>
			</div>

		</div>
	</div>
</body>
</html>