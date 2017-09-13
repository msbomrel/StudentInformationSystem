<?php 
 require_once 'connect.php';
 if($_SERVER['REQUEST_METHOD'] == 'POST'){
 	$name = $_POST['name'];
 	$batch = $_POST['batch'];
 	$email = $_POST['email'];
	$mobile = $_POST['mobile'];
if (!empty($name) && !empty($batch) && !empty($email) && !empty($mobile)) {
if (strlen($mobile)==10){
 	$query = "INSERT INTO student (name, batch, email, mobile) VALUES ('$name','$batch','$email','$mobile')";
 	$exeQuery = mysqli_query($connect,$query); 
 	echo ($exeQuery) ? json_encode(array('code' =>1, 'message' => 'success')) : 
             json_encode(array('code' =>2, 'message' => 'failed'));
}else {
echo json_encode(array('code' => 4, 'message' => 'Phone number is not correct'));
}
}else {
         echo json_encode(array('code' =>3, 'message' => 'Please fill all fields'));
}
 }
 else
 {
 	 echo json_encode(array('code' =>101, 'message' => 'Request not Valid'));
 }

 ?>