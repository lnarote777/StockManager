package org.example.model

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
data class Usuario(

    @Column(nullable = false, length = 20)
    val password: String,

    @Id
    val nombre: String

){
}