package com.example.simplecalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var etInput: EditText

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInput = findViewById(R.id.etInput)

        val buttons = listOf<Button>(
            findViewById(R.id.btnZero),
            findViewById(R.id.btnOne),
            findViewById(R.id.btnTwo),
            findViewById(R.id.btnThree),
            findViewById(R.id.btnFour),
            findViewById(R.id.btnFive),
            findViewById(R.id.btnSix),
            findViewById(R.id.btnSeven),
            findViewById(R.id.btnEight),
            findViewById(R.id.btnNine),
            findViewById(R.id.btnDecimal),
            findViewById(R.id.btnPlus),
            findViewById(R.id.btnMinus),
            findViewById(R.id.btnMultiply),
            findViewById(R.id.btnDivide),
            findViewById(R.id.btnClear),
            findViewById(R.id.btnDivide),
            findViewById(R.id.btnMultiply),
            findViewById(R.id.btnDelete),
            findViewById(R.id.btnEquals)
        )
        for (button in buttons) {
            button.setOnClickListener {
                val buttonText = button.text.toString()
                val inputText = etInput.text.toString()

                when (buttonText) {
                    "=" -> {
                        val result = evaluateExpression(inputText)
                        etInput.setText(result)
                    }
                    "C" -> etInput.setText("")
                    "DEL" -> {
                        if (inputText.isNotEmpty()) {
                            etInput.setText(inputText.substring(0, inputText.length - 1))
                        }
                    }
                    else -> etInput.setText(inputText + buttonText)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()
        val inputText = etInput.text.toString()

        when (buttonText) {
            "=" -> {
                val result = evaluateExpression(inputText)
                etInput.setText(result)
            }
            "C" -> etInput.setText("")
            "DEL" -> {
                if (inputText.isNotEmpty()) {
                    etInput.setText(inputText.substring(0, inputText.length - 1))
                }
            }
            else -> etInput.setText(inputText + buttonText)
        }
    }

    private fun evaluateExpression(expression: String): String {
        return try {
            val result = ExpressionBuilder(expression).build().evaluate()
            result.toString()
        } catch (e: Exception) {
            "Error"
        }
    }
}