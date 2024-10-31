package org.example.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.model.Producto
import org.example.model.Usuario

class UserRepo {
    companion object {
        private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("StockManager")

    }

    fun login(userInput:String , passInput: String): Usuario?{
        val em: EntityManager = emf.createEntityManager()
        try {
            val usuario = em.find(Usuario::class.java, userInput)
            if (usuario != null) {
                if (usuario.password == passInput) {
                    return usuario
                }
            }
            return null
        }catch (e: Exception){
            em.transaction.rollback()
            return null
        }finally {
            em.close()
        }
    }
}