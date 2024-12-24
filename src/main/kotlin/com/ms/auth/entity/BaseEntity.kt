/*
package com.ms.auth.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null;

    @CreatedDate
    private lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    private lateinit var updatedAt: LocalDateTime

    fun getId() = this.id

    fun setId(id: Int) {
        this.id = id
    }

    @PrePersist
    fun setCreatedAt() {
        this.createdAt = LocalDateTime.now()
        this.updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun setUpdatedAt() {
        this.updatedAt = LocalDateTime.now()
    }

}*/
