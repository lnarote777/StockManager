package org.example.model

import jakarta.persistence.*

@Entity
@Table(name = "proveedor")
data class Proveedor(

    @Column(nullable = false, unique = true, length = 50)
    val nombre: String,

    @Column(nullable = false)
    val direccion: String,

    @OneToMany(mappedBy = "proveedor",cascade = [CascadeType.ALL])
    val productos: List<Producto> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

){
    constructor() : this("", "", mutableListOf())
    override fun toString(): String {
        return "Proveedor(id=$id, nombre='$nombre', direccion='$direccion')"
    }
}