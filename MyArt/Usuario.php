<?php

include 'conexion.php';
$consulta=$_POST['consulta'];

if($consulta=="insert"){
    $nom_usu=$_POST['nom_usu'];
    $ape_usu=$_POST['ape_usu'];
    $tip_usu=$_POST['tip_usu'];
    $cor_usu=$_POST['cor_usu'];
    $eda_usu=$_POST['eda_usu'];
    $cel_usu=$_POST['cel_usu'];
    $con_usu=$_POST['con_usu'];
    $usu_usu=$_POST['usu_usu'];
    insertar($nom_usu,$ape_usu,$tip_usu,$cor_usu,$eda_usu,$cel_usu,$con_usu,$usu_usu);
}
if($consulta=="delete"){
    $ide_usu=$_POST['ide_usu'];
    eliminar($ide_usu);
}
if($consulta=="update"){
    $ide_usu=$_POST['ide_usu'];
    $nom_usu=$_POST['nom_usu'];
    $ape_usu=$_POST['nom_usu'];
    $tip_usu=$_POST['tip_usu'];
    $cor_usu=$_POST['cor_usu'];
    $eda_usu=$_POST['eda_usu'];
    $cel_usu=$_POST['cel_usu'];
    actualizar($ide_usu,$nom_usu,$ape_usu,$tip_usu,$cor_usu,$eda_usu,$cel_usu);
}
if($consulta=="login"){
    $cor_usu=$_POST['cor_usu'];
    $con_usu=$_POST['con_usu'];
    login($cor_usu,$con_usu);
}

function insertar($nom_usu,$ape_usu,$tip_usu,$cor_usu,$eda_usu,$cel_usu,$con_usu,$usu_usu){
    include 'conexion.php';
    $consulta="INSERT into Usuarios(nom_usu,ape_usu,tip_usu,cor_usu,eda_usu,cel_usu,con_usu,usu_usu) values('$nom_usu','$ape_usu','$tip_usu','$cor_usu','$eda_usu',$cel_usu,'$con_usu','$usu_usu')";
    mysqli_query($conexion,$consulta);
    mysqli_close($conexion);


 }
 function eliminar($ide_usu){
    include 'conexion.php';
    $consulta="DELETE from Usuarios where ide_usu='".$ide_usu."' ";
    mysqli_query($conexion,$consulta);
    mysqli_close($conexion);
 }
 function actualizar($ide_usu,$nom_usu,$ape_usu,$tip_usu,$cor_usu,$eda_usu,$cel_usu){
    include 'conexion.php';
    $consulta="UPDATE Usuarios set nom_usu='$nom_usu',tip_usu='$tip_usu',cor_usu='$cor_usu',eda_usu='$eda_usu',cel_usu='$cel_usu',ape_usu='$ape_usu'  where ide_usu='".$ide_usu."' ";
    mysqli_query($conexion,$consulta);
    mysqli_close($conexion);
 }
 function login($cor_usu,$con_usu){
    include 'conexion.php';

    $consulta=$conexion->prepare("SELECT FROM Usuarios where cor_usu=? and con_usu=? ");
    $consulta->bind_param('ss',$cor_usu,$con_usu);
    $consulta->execute();
    $resultado=$consulta->get_result();
    if($fila=$resultado->fetch_assoc()){
        echo "Sesion iniciada";

    }
    $consulta->close();
    $conexion->close();
 }

?>