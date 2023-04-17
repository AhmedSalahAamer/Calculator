package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var tvInput : TextView
    var lastDot : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvInput = findViewById(R.id.tvInput)

    }
    fun setDigit(view:View){
        tvInput.append((view as Button).text)

    }

    fun onClear(view: View){
        tvInput.text=""
        lastDot = true
    }

    fun onDecimalPoint(view: View){
        if (lastDot){
            tvInput.append((view as Button).text)
            lastDot =  false
        }
    }

}