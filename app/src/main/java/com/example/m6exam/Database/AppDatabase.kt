package com.example.pinterest.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.m6exam.Model.CardItem
import com.example.m6exam.Model.CardRoom

@Database(entities = [CardItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object{
        private var instance:AppDatabase?=null

        fun getInstance(context: Context):AppDatabase{
            if (instance==null){
                instance= Room.databaseBuilder(context,AppDatabase::class.java,"photos.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}