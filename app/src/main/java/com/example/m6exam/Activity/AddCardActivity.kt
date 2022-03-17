package com.example.m6exam.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.m6exam.Model.CardItem
import com.example.m6exam.Model.CardRoom
import com.example.m6exam.R
import com.example.pinterest.Database.AppDatabase

class AddCardActivity : AppCompatActivity() {
    lateinit var tv_cardNumber:TextView
    lateinit var tv_cardHolder:TextView
    lateinit var tv_cardDate:TextView

    lateinit var et_cardNumber:EditText
    lateinit var et_cardDate:EditText
    lateinit var et_cardCVV:EditText
    lateinit var et_cardHolder:EditText

    lateinit var button: Button

    var cardNumber:String = ""
    var cardHolder:String = ""
    var cardDate:String = ""
    var cardCVV:String = ""
    var rnds = (0..100).random()

    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        initViews()
    }

    private fun initViews() {
        val iv_exit = findViewById<ImageView>(R.id.iv_leave)
        iv_exit.setOnClickListener {
            finish()
        }
        tv_cardNumber = findViewById(R.id.tv_cardNumber)
        tv_cardHolder = findViewById(R.id.tv_cardHolder)
        tv_cardDate = findViewById(R.id.tv_cardDate)
        et_cardNumber = findViewById(R.id.et_cardNumber)
        et_cardDate = findViewById(R.id.et_cardDate)
        et_cardCVV = findViewById(R.id.et_cardCVV)
        et_cardHolder = findViewById(R.id.et_cardHolder)
        button = findViewById(R.id.button)

        et_cardNumber.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                tv_cardNumber.text = et_cardNumber.text
            }

        })
        et_cardHolder.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                tv_cardHolder.text = et_cardHolder.text
            }

        })
        et_cardDate.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                tv_cardDate.text = et_cardDate.text
            }

        })

        button.setOnClickListener {
            back()
        }

        appDatabase= AppDatabase.getInstance(this)
    }

    fun back(){
        val intent = Intent()

        cardNumber = et_cardNumber.text.toString()
        cardDate = et_cardDate.text.toString()
        cardHolder = et_cardHolder.text.toString()
        cardCVV = et_cardCVV.text.toString()

        intent.putExtra("number",cardNumber)
        intent.putExtra("date",cardDate)
        intent.putExtra("holder",cardHolder)
        intent.putExtra("id",rnds)
        intent.putExtra("cvv",cardCVV)

        setResult(RESULT_OK,intent)
        finish()

        val card = CardItem(cardCVV,cardDate,cardNumber,cardHolder)
        appDatabase.cardDao().savePhoto(card)

    }
}