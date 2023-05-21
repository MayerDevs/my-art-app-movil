<?php

include 'conexion.php';
$consulta=$_GET['consulta'];
if($consulta==null){
    $consulta=$_POST['consulta'];
}

if($consulta=="insert"){
    $ide_usu=$_POST['ide_usu'];
    $con_com=$_POST['con_com'];
    $ide_con=$_POST['ide_con'];
    insertar($ide_usu,$con_com,$ide_con);
}
if($consulta=="delete"){
    $ide_com=$_GET['ide_com'];
    eliminar($ide_com);
}
if($consulta=="update"){
    $con_com=$_POST['con_com'];
    $ide_con=$_POST['ide_com'];
    actualizar($ide_com,$con_com);
}
if($consulta=="consult"){
    $ide_usu=$_GET['ide_usu'];
    consult($ide_usu);
}
function consult($ide_com){
    include 'conexion.php';
    $consulta=$conexion->prepare("SELECT * FROM Comentarios where ide_com=? ");
    $consulta->bind_param('s',$ide_com);
    $consulta->execute();
    $resultado=$consulta->get_result();
    if($fila = $resultado->fetch_assoc()){
        echo json_encode($fila);
    }
    else{
        echo"Something went wrong";
    }
    $consulta->close();
    $conexion->close();
   

 }

function insertar($ide_usu,$con_com,$ide_con){
    include 'conexion.php';
    $consulta="INSERT into Comentarios(ide_usu,con_com,ide_con) values('$ide_usu','$con_com','$ide_con')";
    mysqli_query($conexion,$consulta);
    $consulta=$conexion->prepare("SELECT * FROM Comentarios");
    $consulta->bind_param('s',$ide_com);
    $consulta->execute();
    $resultado=$consulta->get_result();
    $count=0;
    while($fila = $resultado->fetch_assoc()){
        $count=$count+1;
    }
    $consulta=$conexion->prepare("SELECT * FROM Comentarios where ide_com=?");
    $consulta->bind_param('s',$count);
    $consulta->execute();
    $resultado=$consulta->get_result();
    if($fila = $resultado->fetch_assoc()){
        echo json_encode($fila);
    }
    mysqli_close($conexion);
 }
 function eliminar($ide_com){
    include 'conexion.php';
    $consulta=$conexion->prepare("DELETE FROM Comentarios where ide_com=?");
    $consulta->bind_param('s',$ide_com);
    $consulta->execute();
    $resultado=$consulta->get_result();
    if($resultado->fetch_assoc()){
            echo "deleted";
    }
    $consulta->close();
    $conexion->close();
 }
 function actualizar($ide_com,$con_com){
    include 'conexion.php';
    $consulta="UPDATE Comentarios set con_com='$con_com' where ide_com='$ide_com' ";
    mysqli_query($conexion,$consulta);
    mysqli_close($conexion);
 }


?>