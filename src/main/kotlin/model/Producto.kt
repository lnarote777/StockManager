package org.example.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table
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
    val precioConIva: Float = precioSinIva * 1.21f,

    @Column(name = "fecha_alta",nullable = false)
    val fechaAlta: Date,

    @Column(nullable = false)
    var stock: Int,

    @ManyToOne()
    @JoinColumn(name = "proveedor_id")
    val proveedor: Proveedor,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String
) {
}