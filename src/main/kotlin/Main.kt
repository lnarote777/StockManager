package org.example

import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.model.Usuario
import org.example.output.Console
import org.example.repository.RepoProduct
import org.example.repository.RepoProveedor
import org.example.repository.UserRepo
import org.example.service.ProductService
import org.example.service.ProveedorService

fun main() {

//    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("StockManager")
//    val em = emf.createEntityManager()
//
//    em.transaction.begin()
//
//    val u = Usuario("1234", "larux")
//
//    //dp de hacer el persist, ya estaria en el persistence context
//    em.persist(u)
//
//    //Ya estaria enn la bd
//    em.transaction.commit()
//    em.close()

    //Instancias repository
    val productRepo = RepoProduct()
    val proveedorRepo = RepoProveedor()
    val userRepo = UserRepo()

    //Istancias Services
    val productService = ProductService(productRepo)
    val proveedorService = ProveedorService(proveedorRepo)

    val consola = Console()

    val menu = Menu(consola, proveedorService, productService, userRepo)
    menu.menuLogin()

}