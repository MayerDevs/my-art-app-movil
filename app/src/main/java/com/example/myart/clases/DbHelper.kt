package com.example.myart.clases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, "MyArt", null, 1) {

    private val nom_bd: String = "MyArt"
    private val version: Int = 1

    override fun onCreate(db: SQLiteDatabase?) {
        val Mensajes: String =
            "CREATE TABLE Mensajes(ide_men VARHCAR(50),men_con VARCHAR(500),ide_usu INT,ide_rec INT)"
        val Contacts: String =
            "CREATE TABLE Contacts(ide_con varchar(50),nom_con VARCHAR(50),tip_con varchar(50))"

        db!!.execSQL(Mensajes)
        db!!.execSQL(Contacts)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val Borrar = "DROP TABLE IF EXISTS MyArt"
        db!!.execSQL(Borrar)
        onCreate(db)

    }


    fun addC(ide_con:String,nom_con: String,tip_con:String) {
        // val Añadir="DROP TABLE IF EXISTS MyArt"
        val datos = ContentValues()
        datos.put("ide_con", ide_con)
        datos.put("nom_con", nom_con)
        datos.put("tip_con", tip_con)
        val db = writableDatabase
        db.insert("Contacts", null, datos)
        db.close()
    }
    fun deleteM(ide_com: Int): Int {
        // val Añadir="DROP TABLE IF EXISTS MyArt"}
        val args = arrayOf(ide_com.toString())
        val db = writableDatabase
        var del = db.delete("Comentarios", "ide_usu=?", args)
        db.close()
        return del
    }
    fun addM(ide_men:String,men_con: String,ide_usu:String,ide_rec:String) {
        // val Añadir="DROP TABLE IF EXISTS MyArt"
        val datos = ContentValues()
        datos.put("ide_men", ide_men)
        datos.put("com_con", men_con)
        datos.put("ide_usu", ide_usu)
        datos.put("ide_rec", ide_rec)
        val db = writableDatabase
        db.insert("Mensajes", null, datos)
        db.close()
    }

}