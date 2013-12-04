


<head>


<link rel="stylesheet" type="text/css" href="style.css" media="screen" />

<title>UClickApp</title>

</head>

	<body>

		<div id="wrapper">

<?php include('includes/header.php'); ?>

<?php include('includes/nav.php'); ?>

<div id="content">


<form name="form1" method="post" action="checklogin.php">
<td>Username</td>
<td width="294"><input name="myusername" type="text" id="myusername"></td>
<td>Password</td>
<td><input type="password" 
<td><input type="submit" name="Submit" value="Login"></td>

</div> <!-- end #content -->

<?php include('includes/sidebar.php'); ?>

<?php include('includes/footer.php'); ?>

		</div> <!-- End #wrapper -->

	</body>

</html>