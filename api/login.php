<?php
require_once('connect.php');

$username = $_POST['username'];
$password = $_POST['password'];
 
$sql = "select * from user where username='$username' and password='$password'";
 
$res = mysqli_query($con,$sql);
 
$check = mysqli_fetch_array($res);
 
	if(isset($check)){
	echo 'success';
	}else{
	echo 'failure';
	}
mysqli_close($con);
?>
