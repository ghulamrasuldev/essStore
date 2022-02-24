package com.example.essstore.userInfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [userLoginResponse::class], version = 3, exportSchema = false)
abstract class userLoginDatabase: RoomDatabase(){
    abstract fun userLoginDAO(): userLoginDAO
    companion object{
        @Volatile
        private var INSTANCE: userLoginDatabase? = null

        fun getDatabase(context: Context) : userLoginDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    userLoginDatabase::class.java,
                    "userLoginInfo"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}