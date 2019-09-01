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
<body id="bodyId">
	<header>
		<div class="jumbotron text-center">
			<h1>Welcome to SportsBet!</h1>
			<p>Sports betting is the activity of predicting sports results
				and placing a wager on the outcome.</p>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<h3>
				<span class="text-primary">Login</span> or <span
					class="text-primary">Register</span> to start!
			</h3>
		</div>
		<div class="row">
			<div class="card">
				<h5 class="card-header">Login</h5>
				<div class="card-body">
					<form action="perform_login" method="post">
						<div class="form-group">
							<input type="email" class="form-control" id="email" name="username"
								aria-describedby="emailHelp" placeholder="Email">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password"
								placeholder="Password">
						</div>
						<button type="submit" id="loginButton" class="btn btn-primary btneffect">Login</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
