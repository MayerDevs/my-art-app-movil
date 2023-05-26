<?php

include 'conexion.php';

$consulta=$_POST['consulta'];

if($consulta=="insert"){
    // $ide_con=$_POST['ide_con'];
    $ide_usu=$_POST['ide_usu'];
    $tip_con=$_POST['tip_con'];
    $txt_con=$_POST['txt_con'];
    $ide_lik=$_POST['ide_lik'];
    $con_con=$_POST['con_con'];
    insertar($ide_usu, $tip_con, $txt_con, $ide_lik, $con_con);
}
if($consulta=="delete"){
    $ide_con=$_POST['ide_con'];
    eliminar($ide_con);
}
if($consulta=="update"){
    // $ide_con=$_POST['ide_con'];
    $ide_usu=$_POST['ide_usu'];
    $tip_con=$_POST['tip_con'];
    $txt_con=$_POST['txt_con'];
    $ide_lik=$_POST['ide_lik'];
    $con_con=$_POST['con_con'];
    actualizar($ide_usu, $tip_con, $txt_con, $ide_lik, $con_con);
}

function insertar($ide_usu, $tip_con, $txt_con, $ide_lik, $con_con){
    include 'conexion.php';
    $consulta = "INSERT INTO contenido(ide_usu, tip_con, txt_con, ide_lik, con_con) 
                VALUES('$ide_con', $ide_usu, '$tip_con', '$txt_con', $ide_lik='0', '$con_con')";
                
    mysqli_query($conexion,$consulta);
    mysqli_close($conexion);


 }
 function eliminar($ide_usu){
    include 'conexion.php';
    $consulta="DELETE FROM contenido WHERE ide_con='".$ide_con."' ";
    mysqli_query($conexion,$consulta);
    mysqli_close($conexion);
 }
 function actualizar($ide_usu, $tip_con, $txt_con, $ide_lik, $con_con){
    include 'conexion.php';
    $consulta = "UPDATE contenido SET ide_usu='$ide_usu', tip_con='$tip_con', 
                txt_con='$txt_con', ide_lik='$ide_lik',con_con='$con_con' WHERE ide_con='".$ide_con."' ";
    mysqli_query($conexion,$consulta);
    mysqli_close($conexion);
 }

?>