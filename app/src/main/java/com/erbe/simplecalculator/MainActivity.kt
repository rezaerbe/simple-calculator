package com.erbe.simplecalculator

import CalculatorEngine
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val engine by lazy {
        CalculatorEngine()
    }

    private val operands = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SimpleCalculator)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerButtonClick()
        textResult.isSelected = true
    }

    /**
     * Set [View.OnClickListener] on all the views
     */
    private fun registerButtonClick() {
        (layoutButtonHolder as ViewGroup).children.forEach {
            it.setOnClickListener(this)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        // Calculator is in invalid state so reset before doing any calculations
        if (engine.isInInvalidState) {
            resetCalculator()
        }

        // Every button must have a tag
        if (v == null || v.tag == null) {
            return
        }
        when (val tag = v.tag.toString()) {
            "del" -> {
                // Delete last character from input
                val oldValue = readInputValue()
                if (oldValue.isNotEmpty()) {
                    editTextInput.setText(oldValue.substring(0, oldValue.lastIndex))
                }
            }
            "C" -> resetCalculator()
            in operands -> {
                // Handle numerical values
                // Two decimals in a single number is not allowed. Handle invalid inputs
                if (readInputValue().contains(".") && tag == ".") {
                    return
                }
                editTextInput.setText(readInputValue() + tag)
            }
            else -> {
                // Handle operator buttons and equal button

                // Calculate
                val result = engine.calculate(readInputValue())
                // Set result
                textResult.text = result
                // Reset input field
                editTextInput.setText("")

                if (tag != "=") {
                    // if input is not "=", then it must be operators (like +, -, ^, etc)
                    engine.operator = tag[0]
                    textOperator.text = tag
                }
            }
        }
    }

    private fun resetCalculator() {
        engine.clear()
        editTextInput.setText("")
        textOperator.text = engine.operator.toString()
        textResult.text = "0"
    }

    private fun readInputValue() = editTextInput.text.toString()
}