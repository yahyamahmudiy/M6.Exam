package com.example.m6exam.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m6exam.Adapter.CardAdapter
import com.example.m6exam.Model.CardItem
import com.example.m6exam.Model.CardRoom
import com.example.m6exam.Networking.RetrofitHttp
import com.example.m6exam.R
import com.example.pinterest.Database.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:CardAdapter
    private var list = ArrayList<CardItem>()
    private var listRoom = java.util.ArrayList<CardRoom>()
    lateinit var iv_plus:ImageView
    var cardNumber:String = ""
    var cardHolder:String = ""
    var cardDate:String = ""
    var cardCVV:String = ""
    var cardId:Int = 0

    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        iv_plus = findViewById(R.id.iv_add)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(this,1))
        refreshAdapter(list)
        adapter.notifyDataSetChanged()

        appDatabase= AppDatabase.getInstance(this)

        iv_plus.setOnClickListener {
            openAddCardActivity()
        }

        if (isInternetAvailable()){
            getAllPhotos()
            apiCardListRetrofit()

        }else{
            getAllPhotos()

        }

    }

    fun refreshAdapter(list:ArrayList<CardItem>){
        adapter = CardAdapter(list)
        recyclerView.setAdapter(adapter)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllPhotos() {
        list.clear()
        list.addAll(appDatabase.cardDao().getAllPhotos())
        adapter.notifyDataSetChanged()
    }

    fun apiCardListRetrofit(){
        RetrofitHttp.posterService.listCards().enqueue(object : Callback<ArrayList<CardItem>> {
            override fun onResponse(call: Call<ArrayList<CardItem>>, response: Response<ArrayList<CardItem>>) {
                list.clear()
                list.addAll(response.body()!!)

                refreshAdapter(list)
            }

            override fun onFailure(call: Call<ArrayList<CardItem>>, t: Throwable) {
                Log.d("@@@",t.message.toString())
            }

        })
    }

    fun openAddCardActivity(){
        val intent = Intent(this,AddCardActivity::class.java)
        createLauncher.launch(intent)
    }

    var createLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            cardNumber = data!!.getStringExtra("number")!!
            cardHolder = data.getStringExtra("holder")!!
            cardDate = data.getStringExtra("date")!!
            cardCVV = data.getStringExtra("cvv")!!
            cardId = data.getIntExtra("id",0)
            Log.d("@@@", "Income: {$cardNumber, $cardHolder, $cardDate, $cardCVV, $cardId}")

            apiCardCreateRetrofit()
            getAllPhotos()

        }
    }
    fun apiCardCreateRetrofit(){
        val card = CardItem(cardCVV,cardDate,cardNumber,cardHolder)
        RetrofitHttp.posterService.createCard(card).enqueue(object :
            Callback<CardItem> {
            override fun onResponse(call: Call<CardItem>, response: Response<CardItem>) {
                Log.d("@@@",response.body().toString())
                list.add(0,card)
                apiCardListRetrofit()
            }

            override fun onFailure(call: Call<CardItem>, t: Throwable) {
                Log.d("@@@",t.message.toString())

            }

        })
    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }
}