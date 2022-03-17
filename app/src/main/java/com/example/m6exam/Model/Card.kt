package com.example.m6exam.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Card(

	@field:SerializedName("Card")
	val card: List<CardItem?>? = null
)

@Entity(tableName = "cards")
data class CardItem(

	@field:SerializedName("cvv")
	val cvv: String? = null,

	@field:SerializedName("expire_date")
	val expireDate: String? = null,

	@field:SerializedName("card_number")
	val cardNumber: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@PrimaryKey(autoGenerate = true)
	@field:SerializedName("id")
	val id: Int = 0
):Serializable
