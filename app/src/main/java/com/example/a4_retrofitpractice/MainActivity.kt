package com.example.a4_retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a4_retrofitpractice.databinding.ActivityMainBinding
import com.example.a4_retrofitpractice.model.ResponseMoviesList
import com.example.a4_retrofitpractice.server.ApiClient
import com.example.a4_retrofitpractice.server.ApiServices
import com.example.a4_retrofitpractice.utils.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val moviesAdapter by lazy { MoviesAdapter() }
    private val api:ApiServices by lazy {
        ApiClient().getClient().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            progressBar.visibility = View.VISIBLE

            //call api
            val call = api.getMoviesList(2)

            //callBack
            call.enqueue(object :Callback<ResponseMoviesList>{
                override fun onResponse(call: Call<ResponseMoviesList>,response: Response<ResponseMoviesList>) {
                    progressBar.visibility = View.GONE
                   if (response.isSuccessful){
                       response.body()?.let { itBody->
                           itBody.data?.let { itData->
                               if (itData.isNotEmpty()){
                                   recyclerView.apply {
                                       layoutManager = LinearLayoutManager(this@MainActivity)
                                       adapter = moviesAdapter
                                   }
                                   moviesAdapter.differ.submitList(itData)
                               }
                           }
                       }
                   }
                }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Log.i("fail","${t.message}")
                }
            })
        }
    }
}