<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$username = $_POST['username'];
		$password = $_POST['password'];
		$email = $_POST['email'];

		if($username == '' || $password == '' || $email == ''){
			echo 'please fill all values';
		}else{
			require_once('dbConnect.php');
			$sql = "SELECT * FROM user WHERE username='$username' OR email='$email'";

			$check = mysqli_fetch_array(mysqli_query($con,$sql));

			if(isset($check)){
				echo 'username or email already exist';
			}else{
				$sql = "INSERT INTO user (username,password,email) VALUES('$username','$password','$email')";
				if(mysqli_query($con,$sql)){
					echo 'Successfully Registered';
				}else{
					echo 'oops! Please try again!';
				}
			}
			mysqli_close($con);
		}
}else{
echo 'error';
}
?>
