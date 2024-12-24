package com.example.project1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: String,
    val imageResId: Int,
    val description: String,
    val isInBasket: Boolean,
    val shop: String
)