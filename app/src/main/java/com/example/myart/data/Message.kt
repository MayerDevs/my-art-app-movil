package com.example.myart.data

data class Message(
    var ide_usu: String,
    var ide_rec: String,
    var men_con: String,
    var dat_men: com.google.firebase.Timestamp?
)