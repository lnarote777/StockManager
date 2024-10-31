package org.example

import org.example.output.Console
import org.example.repository.RepoProduct
import org.example.repository.RepoProveedor
import org.example.repository.UserRepo
import org.example.service.ProductService
import org.example.service.ProveedorService

fun main() {
    //Instancias repository
    val productRepo = RepoProduct()
    val proveedorRepo = RepoProveedor()
    val userRepo = UserRepo()

    //Istancias Services
    val productService = ProductService(productRepo, proveedorRepo)
    val proveedorService = ProveedorService(proveedorRepo)

    val consola = Console()

    val menu = Menu(consola, proveedorService, productService, userRepo)
    menu.menuLogin()
    menu.menuGestion()
}