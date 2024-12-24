package com.example.project1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: Int)

    @Query("SELECT * FROM products WHERE isInBasket = 1")
    suspend fun getBasketProducts(): List<ProductEntity> // Получение только продуктов в корзине

    @Query("UPDATE products SET isInBasket = 1 WHERE id = :productId")
    suspend fun addToBasket(productId: Int) // Добавить в корзину

    @Query("UPDATE products SET isInBasket = 0 WHERE id = :productId")
    suspend fun removeFromBasket(productId: Int) // Удалить из корзины

    @Query("SELECT * FROM products WHERE shop = :shopName")
    suspend fun getProductsByShop(shopName: String): List<ProductEntity>
}
