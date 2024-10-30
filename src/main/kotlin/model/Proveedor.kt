package org.example.model

import jakarta.persistence.*

@Entity
@Table(name = "proveedor")
class Proveedor(

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val direccion: String,

    @OneToMany(mappedBy = "proveedor",cascade = [(CascadeType.ALL)])
    val productos: List<Producto> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

) {
}