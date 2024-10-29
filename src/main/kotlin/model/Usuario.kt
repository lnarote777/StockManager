package org.example.model

import jakarta.persistence.*

@Entity
@Table
data class Usuario(

    @Column(nullable = false, length = 20)
    val password: String,

    @Id
    val nombre: String

) {

}