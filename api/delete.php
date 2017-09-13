<?php 
 require_once 'connect.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$id = $_POST['id'];

 	$query = "DELETE FROM student WHERE id ='$id'";

 	$exeQuery = mysqli_query($connect, $query); 

 	echo ($exeQuery) ? json_encode(array('code' =>1, 'message' => 'success')) : 
            json_encode(array('code' =>2, 'message' => 'failed'));
 }
 else
 {
 	 echo json_encode(array('code' =>101, 'message' => 'Request not Valid'));
 }

 ?>