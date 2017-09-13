<?php 
 require_once 'connect.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$id = $_POST['id'];
 	$name = $_POST['name'];
 	$batch = $_POST['batch'];
 	$email = $_POST['email'];
	$mobile = $_POST['mobile'];

 	$query = "UPDATE  student SET name = '$name', batch = '$batch', email = '$email', mobile='$mobile' WHERE id='$id'";

 	$exeQuery = mysqli_query($connect, $query); 

 	echo ($exeQuery) ? json_encode(array('code' =>1, 'message' => 'Data Updated')) :  json_encode(array('code' =>2, 'message' => 'Data not Updated'));
 }
 else
 {
 	 echo json_encode(array('code' =>101, 'message' => 'Request Not Valid'));
 }

 ?>