package org.example.repository

import org.example.model.Producto

class RepoProduct {

    fun altaProducto(
        idProducto: String,
        nombreProducto: String,
        precioSinIva: Float,
        descripcioProducto: String,
        idProveedor: Long,
        nombreProveedor: String,
        direccionProveedor: String): Producto {

    }

    fun bajaProducto(id: String): Producto {}

    fun modificarNombreProducto(id: String, nuevoNombre: String): Producto {}

    fun modificarStockProducto(id: String, nuevoStock: Int): Producto {}

    fun getProducto(id: String): Producto {}

    fun getProductosConStock(): List<Producto> {}

    fun getProductosSinStock(): List<Producto> {}


}