package org.example.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.model.Producto
import org.example.model.Proveedor
import java.util.Date

class RepoProduct {

    companion object {
        private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("StockManager")
        val em: EntityManager = emf.createEntityManager()
    }

    fun altaProducto(
        idProducto: String,
        nombreProducto: String,
        precioSinIva: Float,
        descripcioProducto: String,
        idProveedor: Long,
        nombreProveedor: String,
        direccionProveedor: String,
        categoria: String
    ): Producto?{
        return try {
            em.transaction.begin()
            val proveedor = em.find(Proveedor::class.java, idProveedor) ?: Proveedor(
                nombre = nombreProveedor,
                direccion = direccionProveedor)
            val producto = Producto(
                categoria = categoria,
                nombre = nombreProducto,
                descripcion = descripcioProducto,
                precioSinIva = precioSinIva,
                proveedor = proveedor,
                fechaAlta = Date(),
                stock = 0,
                precioConIva = precioSinIva * 1.21f,
                id = idProducto)
            em.persist(producto)
            em.transaction.commit()
            producto
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }
    }

    fun bajaProducto(id: String): Producto?{
        return try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, id)
            if (producto != null){
                em.remove(producto)
            }
            em.transaction.commit()
            producto
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }
    }

    fun modificarNombreProducto(id: String, nuevoNombre: String): Producto?{
        return try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, id)
            if (producto != null){
                producto.nombre = nuevoNombre
                em.merge(producto)
            }
            em.transaction.commit()
            producto
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }
    }

    fun modificarStockProducto(id: String, nuevoStock: Int): Producto?{
        return try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, id)
            if (producto != null){
                producto.stock = nuevoStock
                em.merge(producto)
            }
            em.transaction.commit()
            producto
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }
    }

    fun getProducto(id: String): Producto?{
        return try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, id)
            em.transaction.commit()
            producto
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }
    }

    fun getProductosConStock(): List<Producto>?{
        return try {
            em.transaction.begin()
            val productos = em.createQuery("SELECT p FROM Producto p WHERE p.stock > 0", Producto::class.java).resultList
            em.transaction.commit()
            productos
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }
    }

    fun getProductosSinStock(): List<Producto>?{
        return try{
            em.transaction.begin()
            val productos = em.createQuery("SELECT p FROM Producto p WHERE p.stock = 0", Producto::class.java).resultList
            em.transaction.commit()
            productos
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }
    }


}