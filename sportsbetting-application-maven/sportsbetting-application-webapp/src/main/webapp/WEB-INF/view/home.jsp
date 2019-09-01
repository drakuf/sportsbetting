<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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

<link rel="stylesheet" type="text/css" href="resources/styles.css">
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
					<li class="nav-item active"><a class="nav-link" href="home">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="events">Events</a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Language </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">English</a> <a
								class="dropdown-item" href="#">Magyar</a>
						</div></li>
				</ul>
				<form action="${contextPath}/logout" class="form-inline my-2 my-lg-0">
					<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Logout</button>
				</form>
			</div>
		</nav>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-xl-12">
				<div class="card">
					<h5 class="card-header">Account details</h5>
					<div class="card-body">
						<form:form action="saveplayer/${player.id}"
							modelAttribute="player" method="post">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="name">Name</span>
								</div>
								<form:input path="name" id="name" type="text"
									class="form-control" value="${player.name}" />
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="birth">Date of Birth</span>
								</div>
								<form:input type="date" path="birth" class="form-control"
									value="${player.birth}" />
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">Account
										number</span>
								</div>
								<form:input type="number" path="accountNumber"
									class="form-control" value="${player.accountNumber}" />
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">Currency</span>
								</div>
								<form:select path="currency" class="custom-select" id="currency">
									<form:options />
								</form:select>
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">Balance</span>
								</div>
								<form:input type="number" path="balance" class="form-control"
									value="${player.balance}" />
							</div>

							<button type="submit" id="saveButton" name="saveButton"
								class="btn btn-primary btneffect">Save</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<br>
		<div class="card">
			<h5 class="card-header">Wagers</h5>
			<div class="card-body">
				<table class="table table-responsive-lg">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">#</th>
							<th scope="col">Event title</th>
							<th scope="col">Event type</th>
							<th scope="col">Bet type</th>
							<th scope="col">Outcome value</th>
							<th scope="col">Outcome odd</th>
							<th scope="col">Wager amount</th>
							<th scope="col">Winner</th>
							<th scope="col">Processed</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:set var="count" value="1" scope="page" />
							<c:forEach items="${wagers}" var="wagers">
								<tr>
									<td><a href="<c:url value='/removewager/${wagers.id}' />">
											<button type="submit" id="removeWager"
												class="btn btn-primary btneffect">Remove</button>
									</a></td>
									<th scope="row">${count}</th>
									<td>${wagers.getOdd().getOutcome().getBet().getEvent().getTitle()}</td>
									<td>Football match</td>
									<td>${wagers.getOdd().getOutcome().getBet().getType()}</td>
									<td>${wagers.getOdd().getValue()}</td>
									<td>${wagers.getOdd().getOutcome().getDescription()}</td>
									<td>${wagers.getAmount()}</td>
									<td>${wagers.isWin()}</td>
									<td>${wagers.isProcessed()}</td>
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