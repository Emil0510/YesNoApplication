package com.emilabdurahmanli.yesnoapplication.api


import com.emilabdurahmanli.yesnoapplication.model.Result
import retrofit2.Call
import retrofit2.http.GET




interface Api {

    @GET("api")
    fun getAnswer(): Call<Result?>?

}