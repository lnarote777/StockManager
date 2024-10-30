package org.example.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.model.Producto
import org.example.model.Proveedor

class RepoProveedor {

    companion object {
        private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("StockManager")
        val em: EntityManager = emf.createEntityManager()
    }

    fun getProveedorProducto(idProducto: String): Proveedor?{
        return try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, idProducto)
            val proveedor = producto?.proveedor
            em.transaction.commit()
            proveedor
        } catch (e: Exception) {
            em.transaction.rollback()
            null
        }
    }

    fun getTodosProveedores(): List<Proveedor>?{
        return try {
            em.transaction.begin()
            val proveedores = em.createQuery("SELECT p FROM Proveedor p", Proveedor::class.java).resultList
            em.transaction.commit()
            proveedores
        } catch (e: Exception) {
            em.transaction.rollback()
            null
        }
    }

}