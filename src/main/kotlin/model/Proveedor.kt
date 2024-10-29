package org.example.model

import jakarta.persistence.*

@Entity
@Table
class Proveedor(

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val direccion: String,

    @OneToMany(cascade = [(CascadeType.ALL)])
    val productors: List<Producto>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

) {
}