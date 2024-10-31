package org.example.output

class Console: IConsole {
    override fun mostrarMensaje(mensaje: Any, salto: Boolean) {
        if (salto) println(mensaje) else print(mensaje)
    }

    override fun mostrarListado(lista: List<Any>) {
        lista.forEach { println(it) }
    }

    override fun pedirIdProducto(): String {
        mostrarMensaje("Id de producto: ", false)
        val id = readln()
        return id
    }

    override fun pedirIdProveedor(): Long {
        mostrarMensaje("Id de proveedor: ", false)
        val id = readln().toLong()
        return id
    }

    override fun pedirString(mensaje: String): String {
        mostrarMensaje(mensaje)
        val string = readln()
        return string
    }

    override fun pedirInt(mensaje: String): Int {
        mostrarMensaje(mensaje)
        val int = readln().toInt()
        return int
    }

    override fun pedirFloat(mensaje: String): Float {
        mostrarMensaje(mensaje)
        val float = readln().toFloat()
        return float
    }


}