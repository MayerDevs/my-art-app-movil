package com.example.myart.data

import com.google.firebase.database.Exclude

data class Content(
    val con_con: String? = null,
    val txt_con: String? = null,
    val lik_con: ArrayList<String>? = arrayListOf()
){
    @Exclude
    @set: Exclude
    @get: Exclude
    var uid: String? = null

    constructor(): this(null, null, null)
}
