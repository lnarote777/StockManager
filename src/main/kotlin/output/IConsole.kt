package org.example.output

interface IConsole {
    fun mostrarMensaje(mensaje: Any, salto : Boolean = true)
    fun mostrarListado(lista: List<Any>)
    fun pedirIdProducto(): String
    fun pedirIdProveedor(): Long
    fun pedirString(mensaje: String): String
    fun pedirInt(mensaje: String): Int
    fun pedirFloat(mensaje: String): Float
}