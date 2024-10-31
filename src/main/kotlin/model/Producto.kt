package org.example.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "producto")
data class Producto(

    @Column(nullable = false, length = 10)
    val categoria: String,

    @Column(nullable = false, length = 50)
    var nombre: String,

    @Column(nullable = false)
    val descripcion: String,

    @Column(name = "precio_sin_iva",nullable = false)
    val precioSinIva: Float,

    @Column(name = "precio_con_iva", nullable = false)
    val precioConIva: Float,

    @Column(name = "fecha_alta",nullable = false)
    val fechaAlta: Date,

    @Column(nullable = false)
    var stock: Int,

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    val proveedor: Proveedor?,

    @Id
    val id: String? = null
) {
    constructor() : this("", "", "", 0f, 0f, Date(), 0, null, null)

    override fun toString(): String {
        return "Producto(id=$id, categoria=$categoria, nombre=$nombre, descripcion=$descripcion, precioSinIva=$precioSinIva, precioConIva=$precioConIva, fechaAlta=$fechaAlta, stock=$stock)"
    }
}