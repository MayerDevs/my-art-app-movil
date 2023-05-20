package clases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, "MyArt", null, 1) {

    private val nom_bd: String = "MyArt"
    private val version: Int = 1

    override fun onCreate(db: SQLiteDatabase?) {
        val Usuarios: String =
            "CREATE TABLE Usuarios(ide_usu INT  PRIMARY KEY,cor_usu VARCHAR(50),con_usu VARCHAR(50))"
        val Comentarios: String =
            "CREATE TABLE Comentarios(ide_com INT  PRIMARY KEY,con_com VARCHAR(500),ide_con INT)"
        val Likes: String =
            "CREATE TABLE Likes(ide_lik INT PRIMARY KEY,ide_con INT)"

        db!!.execSQL(Usuarios)
        db!!.execSQL(Comentarios)
        db!!.execSQL(Likes)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val Borrar = "DROP TABLE IF EXISTS MyArt"
        db!!.execSQL(Borrar)
        onCreate(db)

    }

    fun add(cor_usu: String, con_usu: String) {
        // val Añadir="DROP TABLE IF EXISTS MyArt"
        val datos = ContentValues()
        datos.put("ide_usu", 1)
        datos.put("cor_usu", cor_usu)
        datos.put("con_usu", cor_usu)
        val db = writableDatabase
        db.insert("Usuarios", null, datos)
        db.close()


    }

    fun delete(ide_usu: Int): Int {
        // val Añadir="DROP TABLE IF EXISTS MyArt"}
        val args = arrayOf(ide_usu.toString())
        val db = writableDatabase
        var del = db.delete("Usuarios", "ide_usu=?", args)
        db.close()
        return del
    }

    fun addC(ide_com: Int,con_com:String,ide_con:Int) {
        // val Añadir="DROP TABLE IF EXISTS MyArt"
        val datos = ContentValues()
        datos.put("ide_com", ide_com)
        datos.put("con_com", con_com)
        datos.put("ide_con", ide_con)
        val db = writableDatabase
        db.insert("Comentarios", null, datos)
        db.close()
    }
    fun deleteC(ide_com: Int): Int {
        // val Añadir="DROP TABLE IF EXISTS MyArt"}
        val args = arrayOf(ide_com.toString())
        val db = writableDatabase
        var del = db.delete("Comentarios", "ide_usu=?", args)
        db.close()
        return del
    }


}