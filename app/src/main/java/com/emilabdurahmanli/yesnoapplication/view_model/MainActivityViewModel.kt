package com.emilabdurahmanli.yesnoapplication.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emilabdurahmanli.yesnoapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.emilabdurahmanli.yesnoapplication.model.Result
enum class State{
    SUCCESS, ERROR
}
class MainActivityViewModel : ViewModel() {

    var result = MutableLiveData<Result?>()
    var state = MutableLiveData<State?>()

    fun getAnswer(context : Context, answer : String) {

        val call: Call<Result?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getAnswer()
        call?.enqueue(object : Callback<Result?> {
            override fun onResponse(call: Call<Result?>?, response: Response<Result?>) {
                val result: Result? = response.body()
                this@MainActivityViewModel.result.postValue(result)
                if(answer.equals(result?.answer)){
                    this@MainActivityViewModel.state.postValue(State.SUCCESS)
                }else{
                    this@MainActivityViewModel.state.postValue(State.ERROR)
                }
            }
            override fun onFailure(call: Call<Result?>?, t: Throwable?) {
                Toast.makeText(context, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })



    }

    fun observeResult() : LiveData<Result?>{
        return result
    }

    fun observeState() : LiveData<State?>{
        return state
    }



}