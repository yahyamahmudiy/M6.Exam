package com.example.m6exam.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cards")
data class CardRoom(
	val cvv: String? = null,
	val expireDate: String? = null,
	val cardNumber: String? = null,
	val fullname: String? = null,

	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
):Serializable
