package com.example.essstore.favourite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [favouriteProduct::class], version = 1, exportSchema = false)
abstract class favouriteProductDatabase: RoomDatabase(){
    abstract fun favouriteProductDAO(): favouriteProductDAO
    companion object{
        @Volatile
        private var INSTANCE: favouriteProductDatabase? = null

        fun getDatabase(context: Context) : favouriteProductDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    favouriteProductDatabase::class.java,
                    "favourite"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}