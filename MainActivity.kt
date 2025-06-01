package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentInput = ""
    private var currentResult = 0.0
    private var lastOperation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botões numéricos
        listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9
        ).forEach { button ->
            button.setOnClickListener {
                currentInput += (button as Button).text
                updateDisplay()
            }
        }

        // Operações
        binding.btnAdd.setOnClickListener { handleOperation("+") }
        binding.btnSubtract.setOnClickListener { handleOperation("-") }
        binding.btnMultiply.setOnClickListener { handleOperation("*") }
        binding.btnDivide.setOnClickListener { handleOperation("/") }

        binding.btnEquals.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                calculate()
                lastOperation = ""
                currentInput = currentResult.toString()
                updateDisplay()
            }
        }

        binding.btnClear.setOnClickListener {
            currentInput = ""
            currentResult = 0.0
            lastOperation = ""
            updateDisplay()
        }
    }

    private fun handleOperation(operation: String) {
        if (currentInput.isNotEmpty()) {
            calculate()
            lastOperation = operation
            currentInput = ""
            updateDisplay()
        }
    }

    private fun calculate() {
        val number = currentInput.toDouble()
        when (lastOperation) {
            "+" -> currentResult += number
            "-" -> currentResult -= number
            "*" -> currentResult *= number
            "/" -> currentResult /= number
            else -> currentResult = number
        }
    }

    private fun updateDisplay() {
        binding.tvDisplay.text = currentInput.ifEmpty { currentResult.toString() }
    }
}