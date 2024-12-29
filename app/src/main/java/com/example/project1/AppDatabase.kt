package com.example.project1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ProductEntity::class, UserEntity::class], version = 4) // Обновили версию до 4
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao // Добавили DAO для пользователей

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Миграция с версии 1 до 2
                val MIGRATION_1_2 = object : Migration(1, 2) {
                    override fun migrate(db: SupportSQLiteDatabase) {
                        db.execSQL("ALTER TABLE products ADD COLUMN isInBasket INTEGER NOT NULL DEFAULT 0")
                    }
                }

                // Миграция с версии 2 до 3
                val MIGRATION_2_3 = object : Migration(2, 3) {
                    override fun migrate(db: SupportSQLiteDatabase) {
                        // Создаем таблицу users
                        db.execSQL(
                            """
                            CREATE TABLE users (
                                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                                username TEXT NOT NULL,
                                email TEXT NOT NULL,
                                password TEXT NOT NULL,
                                shop TEXT NOT NULL,
                                logoResId INTEGER NOT NULL
                            )
                            """.trimIndent()
                        )
                    }
                }

                val MIGRATION_3_4 = object : Migration(3, 4) {
                    override fun migrate(db: SupportSQLiteDatabase) {
                        db.execSQL("ALTER TABLE products ADD COLUMN shop TEXT NOT NULL DEFAULT 0")
                    }
                }

                val MIGRATION_4_5 = object : Migration(4, 5) {
                    override fun migrate(db: SupportSQLiteDatabase) {
                        db.execSQL("ALTER TABLE products ADD COLUMN kategory TEXT NOT NULL DEFAULT 0")
                    }
                }

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5) // Добавили миграцию 4 -> 5
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}

