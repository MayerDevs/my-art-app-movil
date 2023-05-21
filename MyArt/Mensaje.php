<?php

include 'conexion.php';
$consulta=$_GET['consulta'];
if($consulta==null){
    $consulta=$_POST['consulta'];
}

if($consulta=="insert"){
    $ide_usu=$_GET['ide_usu'];
    $men_con=$_GET['men_con'];
    $ide_rec=$_GET['ide_rec'];
    insertar($ide_usu,$men_con,$ide_rec);
}
if($consulta=="delete"){
    $ide_com=$_GET['ide_men'];
    eliminar($ide_com);
}



function insertar($ide_usu,$men_con,$ide_rec){
    include 'conexion.php';
    $consulta="INSERT into Mensajes(ide_usu,men_con,ide_rec) values('$ide_usu','$men_con','$ide_rec')";
    mysqli_query($conexion,$consulta);
    $consulta=$conexion->prepare("SELECT * FROM Mensajes");
    $consulta->execute();
    $resultado=$consulta->get_result();
    $count=0;
    while($fila = $resultado->fetch_assoc()){
        $count=$count+1;
    }
    $consulta=$conexion->prepare("SELECT * FROM Mensajes where ide_men=?");
    $consulta->bind_param('s',$count);
    $consulta->execute();
    $resultado=$consulta->get_result();
    if($fila = $resultado->fetch_assoc()){
        echo json_encode($fila);
    }
    mysqli_close($conexion);
 }
 function eliminar($ide_men){
    include 'conexion.php';
    $consulta=$conexion->prepare("DELETE FROM Mensajes where ide_men=?");
    $consulta->bind_param('s',$ide_men);
    $consulta->execute();
    $resultado=$consulta->get_result();
    if($resultado->fetch_assoc()){
            echo "deleted";
    }
    $consulta->close();
    $conexion->close();
 }



?>