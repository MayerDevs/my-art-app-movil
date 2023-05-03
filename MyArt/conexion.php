<?php
        $host = "localhost";
        $dbname = "MyArt";
        $user = "root";
        $password = "password=";

        $conexion=new mysqli($host,$user,$password,$dbname);
        if($conexion->connect_errno){

            echo "Lo sentimos hubo un error al conectar con el servidor";

        }
?>