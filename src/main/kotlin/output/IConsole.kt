package org.example.output

interface IConsole {
    fun mostrarMensaje(mensaje: Any, salto : Boolean = true)
    fun mostrarListado(lista: List<Any>)
    fun pedirIdProducto(): String
    fun pedirIdProveedor(): Long
    fun pedirString(mensaje: String, salto: Boolean): String
    fun pedirInt(mensaje: String, salto: Boolean): Int
    fun pedirFloat(mensaje: String, salto: Boolean): Float
}