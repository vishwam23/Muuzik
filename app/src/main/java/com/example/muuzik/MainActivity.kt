package com.example.muuzik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView:RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerView=findViewById(R.id.recyclerView)
        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    val retrofitData=retrofitBuilder.getData("eminem")
//    searchfor()
        retrofitData.enqueue(object : Callback<MyData?>{
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                 val dataList=response.body()?.data!!
//                val textView=findViewById<TextView>(R.id.hellotext)
//                textView.text=dataList.toString()
                myAdapter= MyAdapter(this@MainActivity,dataList )
                myRecyclerView.adapter=myAdapter
                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                Log.d("TAG:onResponse","onResponse: "+ response.body())
            }
            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("TAG : onFailure", "onFailure: " + t.message)
            }
        })
    }
//    private fun searchfor() {
//    val search=findViewById<SearchView>(R.id.search)
//        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                val retrofitData=retrofitBuilder.getData(query)
//                return true
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//    return true          }
//        })

//    }


}