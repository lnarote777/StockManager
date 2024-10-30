package org.example.service

import org.example.model.Proveedor
import org.example.repository.RepoProveedor

class ProveedorService(val proveedorRepo : RepoProveedor) {
    fun getProveedorProducto(idProducto: String): Proveedor?{
        return proveedorRepo.getProveedorProducto(idProducto)
    }

    fun getTodosProveedores(): List<Proveedor>?{
        return proveedorRepo.getTodosProveedores()
    }
}