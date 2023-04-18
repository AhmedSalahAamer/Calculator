package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var tvInput : TextView?=null

    //to make sure that only one dot added to the numeric 
    var lastDot : Boolean = false

    // to make sure that the number is add
    var lastNumeric : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvInput = findViewById(R.id.tvInput)

    }
    fun setDigit(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        tvInput?.text=""
        lastDot = false
       lastNumeric = false
    }

    fun onDecimalPoint(view: View){
        if (lastNumeric &&  !lastDot){
            tvInput?.append((view as Button).text)
            lastDot =  true
            lastNumeric = false
        }
    }

    // Add the Operator to the String
    fun onOperator(view: View){
        if (lastNumeric && !isOpearatorAdded(tvInput?.text.toString())){
            tvInput?.append((view as Button).text)
            lastDot=false
            lastNumeric = false
        }
    }

    // to check the negative and the operators
    private fun isOpearatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
                      value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")

        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var value = tvInput?.text.toString()
            var prefix = ""

            try {
                if (value.startsWith("-")){
                    prefix = "-"
                    value = value.substring(1)
                }
                if(value.contains("-")){

                    var splitValue = value.split("-")
                    var first = splitValue[0]
                    var secound = splitValue[1]

                    if (prefix.isNotBlank())
                        first = prefix + first

                    tvInput?.text=removeZero((first.toDouble() - secound.toDouble()).toString())

                }else if(value.contains("+")){

                    var splitValue = value.split("+")
                    var first = splitValue[0]
                    var secound = splitValue[1]

                    if (prefix.isNotBlank())
                        first = prefix + first

                    tvInput?.text=removeZero((first.toDouble() + secound.toDouble()).toString())

                }else if(value.contains("*")){

                    var splitValue = value.split("*")
                    var first = splitValue[0]
                    var secound = splitValue[1]

                    if (prefix.isNotBlank())
                        first = prefix + first

                    tvInput?.text=removeZero((first.toDouble() * secound.toDouble()).toString())

                }else if(value.contains("/")){

                    var splitValue = value.split("/")
                    var first = splitValue[0]
                    var secound = splitValue[1]

                    if (prefix.isNotBlank())
                        first = prefix + first

                    tvInput?.text=removeZero((first.toDouble() / secound.toDouble()).toString())

                }

            }catch (error:ArithmeticException){
                error.printStackTrace()
            }
        }
    }

    private fun removeZero(result:String):String{
        var value =result
        if (value.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }

}