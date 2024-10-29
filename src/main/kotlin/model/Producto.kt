package org.example.model

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table
data class Producto(

    @Column(nullable = false, length = 10)
    val categoria: String,

    @Column(nullable = false, length = 50)
    val nombre: String,

    @Column(nullable = false)
    val  descripcion: String,

    @Column(name = "precio_sin_iva",nullable = false)
    val precioSinIva: Float,

    @Column(name = "precio_con_iva", nullable = false)
    val precioConIva: Float,

    @Column(name = "fecha_alta",nullable = false)
    val fechaAlta: Date,

    @Column(nullable = false)
    val stock: Int,

    @ManyToOne()
    val proveedor: Proveedor,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String
) {
}