package org.example.repository

import org.example.model.Proveedor

class RepoProveedor {

    fun getProveedorProducto(idProducto: String): Proveedor{}

    fun getTodosProveedores(): List<Proveedor>{}

}