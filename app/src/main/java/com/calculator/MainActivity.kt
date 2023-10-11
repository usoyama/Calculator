package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.SimpleCursorAdapter.ViewBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.ui.theme.CalculatorTheme
import net.objecthunter.exp4j.ExpressionBuilder

private lateinit var binding: ActivityMainBinding
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn0.setOnClickListener {setTextFields("0")}
        binding.btn1.setOnClickListener {setTextFields("1")}
        binding.btn2.setOnClickListener {setTextFields("2")}
        binding.btn3.setOnClickListener {setTextFields("3")}
        binding.btn4.setOnClickListener {setTextFields("4")}
        binding.btn5.setOnClickListener {setTextFields("5")}
        binding.btn6.setOnClickListener {setTextFields("6")}
        binding.btn7.setOnClickListener {setTextFields("7")}
        binding.btn8.setOnClickListener {setTextFields("8")}
        binding.btn9.setOnClickListener {setTextFields("9")}
        binding.btnMinus.setOnClickListener { setTextFields("-") }
        binding.btnPlus.setOnClickListener { setTextFields("+") }
        binding.btnMult.setOnClickListener { setTextFields("*") }
        binding.btnSlash.setOnClickListener { setTextFields("/") }
        binding.btnLeft.setOnClickListener { setTextFields("(") }
        binding.btnRight.setOnClickListener { setTextFields(")") }
        binding.btnTochka.setOnClickListener { setTextFields(".") }
        binding.btnAc.setOnClickListener {
            binding.mathOperation.text = ""
            binding.resultText.text = ""
        }
        binding.btnBack.setOnClickListener {
            val str =binding.mathOperation.text.toString()
            if(str.isNotEmpty()){
                binding.mathOperation.text = str.substring(0, str.length-1)
            }
            binding.resultText.text = ""
        }

        binding.btnRavno.setOnClickListener{
           try{

               val ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
               val result = ex.evaluate()

               val longRes = result.toLong()
               if(result == longRes.toDouble())
                   binding.resultText.text = longRes.toString()
               else
                   binding.resultText.text = result.toString()
           }
           catch (e:Exception){
               Log.d("Ошибка", "сообщение: ${e.message}")
           }
        }
    }
    fun setTextFields(str: String) {
        if(binding.resultText.text != ""){
            binding.mathOperation.text = binding.resultText.text
            binding.resultText.text = ""
        }
        binding.mathOperation.append(str)
    }
}
