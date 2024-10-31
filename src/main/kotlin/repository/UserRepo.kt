package org.example.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.model.Usuario

class UserRepo {
    companion object {
        private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("StockManager")

    }

    fun login(userInput:String , passInput: String): Usuario?{
        val em: EntityManager = emf.createEntityManager()
        return try {
            em.transaction.begin()
            val query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombre = :userInput AND u.password = :passInput",
                Usuario::class.java
            )
            query.setParameter("userInput", userInput)
            query.setParameter("passInput", passInput)

            val usuario = query.resultList.firstOrNull()
            em.transaction.commit()
            usuario
        }catch (e: Exception){
            em.transaction.rollback()
            null
        }finally {
            em.close()
        }
    }
}