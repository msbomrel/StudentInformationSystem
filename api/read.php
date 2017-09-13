<?php 
require_once 'connect.php'; 

$query = "SELECT * FROM student ORDER BY name";

$result = mysqli_query($connect,$query);

$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row; 
}

echo ($result) ? 
json_encode(array("code" => 1, "result"=>$array)) :
json_encode(array("code" => 0, "mesage"=>"Data Not Found"));


?>