package com.example.pinterest.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.m6exam.Model.CardItem
import com.example.m6exam.Model.CardRoom

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePhoto(card:CardItem)

    @Query("SELECT * FROM cards")
    fun getAllPhotos():List<CardItem>
}
