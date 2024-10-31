package org.example.service

import org.example.model.Producto
import org.example.repository.RepoProduct
import org.example.repository.RepoProveedor

class ProductService(private val productRepo: RepoProduct) {
    fun altaProducto(nombreProducto: String,
                     precioSinIva: Float,
                     descripcioProducto: String,
                     idProveedor: Long,
                     nombreProveedor: String,
                     direccionProveedor: String,
                     categoria: String
    ){
        val idProducto = generateIdProducto(categoria, nombreProducto,nombreProveedor)
        productRepo.altaProducto(idProducto, nombreProducto, precioSinIva, descripcioProducto, idProveedor, nombreProveedor, direccionProveedor, categoria)
    }
    private fun generateIdProducto(categoria: String, nombreProducto: String, nombreProveedor: String): String{
        val categoriaPart = categoria.substring(0,3)
        val nombrePart = nombreProducto.substring(0,3)
        val provveedorPart = nombreProveedor.substring(0,3)
        val id = categoriaPart + nombrePart + provveedorPart
        return id
    }

    fun bajaProducto(id: String): Producto?{
        return productRepo.bajaProducto(id)
    }

    fun modificarNombreProducto(id: String, nuevoNombre: String): Producto?{
        return productRepo.modificarNombreProducto(id, nuevoNombre)
    }

    fun modificarStockProducto(id: String, nuevoStock: Int): Producto?{
        return productRepo.modificarStockProducto(id, nuevoStock)
    }

    fun getProducto(id: String): Producto?{
        return productRepo.getProducto(id)
    }

    fun getProductosConStock(): List<Producto>?{
        return productRepo.getProductosConStock()
    }

    fun getProductosSinStock(): List<Producto>?{
        return productRepo.getProductosSinStock()
    }

}