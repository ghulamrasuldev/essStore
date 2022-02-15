package com.example.essstore.cart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [cartProduct::class], version = 1, exportSchema = false)
abstract class cartProductDatabase: RoomDatabase(){
    abstract fun cartProductDAO(): cartProductDAO

    companion object{
        @Volatile
        private var INSTANCE: cartProductDatabase? = null

        fun getDatabase(context: Context) : cartProductDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    cartProductDatabase::class.java,
                    "productsInCart"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}