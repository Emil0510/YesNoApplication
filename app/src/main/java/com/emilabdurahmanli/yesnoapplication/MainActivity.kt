package com.emilabdurahmanli.yesnoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.emilabdurahmanli.yesnoapplication.databinding.ActivityMainBinding
import com.emilabdurahmanli.yesnoapplication.view_model.MainActivityViewModel
import com.emilabdurahmanli.yesnoapplication.view_model.State

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]



        binding.yesButton.setOnClickListener {
            reset()
            viewModel.getAnswer(this, "yes")
        }

        binding.noButton.setOnClickListener {
            reset()
            viewModel.getAnswer(this, "no")
        }


        viewModel.observeState().observe(this, Observer {state ->
            when(state){
                State.SUCCESS -> Glide.with(this).load(viewModel.result.value?.image).into(binding.gifImageView)
                State.ERROR -> binding.textView.setText("You Lost")
                else -> {}
            }
        })


    }
    fun reset(){
        binding.textView.setText("")
        binding.gifImageView.setImageResource(0)
    }
}