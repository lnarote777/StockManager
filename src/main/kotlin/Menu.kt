package org.example

import org.example.output.IConsole
import org.example.repository.UserRepo
import org.example.service.ProductService
import org.example.service.ProveedorService

class Menu(
    private val consola: IConsole,
    private val proveedorService: ProveedorService,
    private val productService: ProductService,
    private val userRepo: UserRepo
) {
    fun menuLogin(){
        var login = false
        do {
            consola.mostrarMensaje(
                """
            ******************************************************
            ****    Bienvenid@ a StockControl               ******
            ******************************************************
                            
            Introduzca su usuario y contrasena para continuar (0 para salir)
            """
            )
            consola.mostrarMensaje("user: ", false)
            val userInput = readln()

            if (userInput == "0") {
                consola.mostrarMensaje("Saliendo...")
                login = true
            } else {
                consola.mostrarMensaje("password: ", false)
                val passwordInput = readln()

                val user = userRepo.login(userInput, passwordInput)

                if (user != null) {
                    consola.mostrarMensaje("Bienvenid@: ")
                    login = true
                }else{
                    consola.mostrarMensaje("No se encontró al usuario.")
                }
            }
        } while (!login)

    }

    fun menuGestion(){
        var opc: String

        do {
            consola.mostrarMensaje(
                """
            ******************************************************
            ****            APP STOCK CONTROL               ******
            ******************************************************
                            
            1. Alta producto
            2. Baja producto
            3. Modificar nombre producto
            4. Modificar stock producto
            5. Get producto por id
            6. Get productos con stock
            7. Get productos sin stock
            8. Get proveedores de un producto
            9. Get todos los proveedores
            0. Salir
            """
            )
            consola.mostrarMensaje("Seleccione una opción: ", false)
            opc = readln()

            try {
                when (opc) {
                    "1" -> {
                        val nombreProducto = consola.pedirString("Nombre producto: ", false)
                        val precioSinIva = consola.pedirFloat("Precio producto (sin iva): ", false)
                        val descripcion = consola.pedirString("Descripcion: ", false)
                        val categoria = consola.pedirString("Categoria: ",false)
                        val idProveedor = consola.pedirIdProveedor()
                        val nombreProveedor = consola.pedirString("Nombre proveedor: ",false)
                        val direccionProveedor = consola.pedirString("Direccion proveedor: ",false)

                        productService.altaProducto(nombreProducto, precioSinIva, descripcion, idProveedor, nombreProveedor, direccionProveedor, categoria)
                    }
                    "2" -> {
                        val idProducto = consola.pedirIdProducto()
                        productService.bajaProducto(idProducto)
                    }
                    "3" -> {
                        val idProducto = consola.pedirIdProducto()
                        val nuevoNombre = consola.pedirString("Nuevo nombre: ", false)
                        productService.modificarNombreProducto(idProducto, nuevoNombre)
                    }
                    "4" -> {
                        val idProducto = consola.pedirIdProducto()
                        val stock = consola.pedirInt("Stock: ",false)
                        productService.modificarStockProducto(idProducto, stock)
                    }
                    "5" -> {
                        val idProducto = consola.pedirIdProducto()
                        val producto = productService.getProducto(idProducto)
                        if (producto != null) {
                            consola.mostrarMensaje(producto)
                        }else{
                            consola.mostrarMensaje("No se encontró ningún producto.")
                        }
                    }
                    "6" -> {
                        val productorStock = productService.getProductosConStock()
                        if (productorStock != null) {
                            consola.mostrarListado(productorStock)
                        }
                    }
                    "7" -> {
                        val productorSinStock = productService.getProductosSinStock()
                        if (productorSinStock != null) {
                            consola.mostrarListado(productorSinStock)
                        }
                    }
                    "8" -> {
                        val idProducto = consola.pedirIdProducto()
                        val proveedor = proveedorService.getProveedorProducto(idProducto)
                        if (proveedor != null) {
                            consola.mostrarMensaje(proveedor)
                        }
                    }
                    "9" -> {
                        val proveedores = proveedorService.getTodosProveedores()
                        if (proveedores != null) {
                            consola.mostrarListado(proveedores)
                        }
                    }
                    "0" -> {
                        consola.mostrarMensaje("Saliendo...")
                    }
                    else -> consola.mostrarMensaje("Error en la elección")
                }
            } catch (e: Exception) {
                println("ERROR CONTROLADO")
            }
        } while (opc != "0")
    }
}