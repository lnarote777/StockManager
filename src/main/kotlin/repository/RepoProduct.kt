package org.example.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.model.Producto
import org.example.model.Proveedor
import java.time.Instant
import java.util.Date

class RepoProduct {

    companion object {
        private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("StockManager")
    }
    private fun getEntityManager(): EntityManager {
        return emf.createEntityManager()
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
        val em = getEntityManager()
        try {
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
                fechaAlta = Date.from(Instant.now()),
                stock = 0,
                precioConIva = precioSinIva * 1.21f,
                id = idProducto)
            em.persist(proveedor)
            em.persist(producto)
            em.transaction.commit()
            return producto
        }catch (ex:Exception){
            em.transaction.rollback()
            return null
        }finally {
            em.close()
        }


    }

    fun bajaProducto(id: String): Producto?{
        val em = getEntityManager()
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
        }finally {
            em.close()
        }
    }

    fun modificarNombreProducto(id: String, nuevoNombre: String): Producto?{
        val em = getEntityManager()
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
        }finally {
            em.close()
        }
    }

    fun modificarStockProducto(id: String, nuevoStock: Int): Producto?{
        val em = getEntityManager()
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
        }finally {
            em.close()
        }
    }

    fun getProducto(id: String): Producto?{
        val em = getEntityManager()
        return em.find(Producto::class.java, id)
    }

    fun getProductosConStock(): List<Producto>?{
        val em = getEntityManager()
        return try {
            em.transaction.begin()
            val productos = em.createQuery("SELECT p FROM Producto p WHERE p.stock > 0", Producto::class.java).resultList
            em.transaction.commit()
            productos
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }finally {
            em.close()
        }
    }

    fun getProductosSinStock(): List<Producto>?{
        val em = getEntityManager()
        return try{
            em.transaction.begin()
            val productos = em.createQuery("SELECT p FROM Producto p WHERE p.stock = 0", Producto::class.java)
            em.transaction.commit()
            productos.resultList.toList()
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }finally {
            em.close()
        }
    }


}