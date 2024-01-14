package com.example.calculator

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
lateinit var input: EditText
    lateinit var output: EditText
    lateinit var C: Button
    lateinit var button_root: Button
    lateinit var percent:Button
    lateinit var button_0: Button
    lateinit var button_1: Button
    lateinit var button_2: Button
    lateinit var button_3: Button
    lateinit var button_4: Button
    lateinit var button_5: Button
    lateinit var button_6: Button
    lateinit var button_7: Button
    lateinit var button_8: Button
    lateinit var button_9: Button
    lateinit var button_dot: Button
    lateinit var button_division: Button
    lateinit var button_multiply: Button
    lateinit var button_subtraction: Button
    lateinit var button_addition: Button
    lateinit var button_equals: Button
    lateinit var remove: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        input = findViewById(R.id.input)
        output = findViewById(R.id.output)
        C = findViewById(R.id.C)
        button_0 = findViewById(R.id.zero)
        button_1 = findViewById(R.id.one)
        button_2 = findViewById(R.id.two)
        button_3 = findViewById(R.id.three)
        button_4 = findViewById(R.id.four)
        button_5 = findViewById(R.id.five)
        button_6 = findViewById(R.id.six)
        button_7 = findViewById(R.id.seven)
        button_8 = findViewById(R.id.eight)
        button_9 = findViewById(R.id.nine)
        button_dot = findViewById(R.id.dot)
        button_division = findViewById(R.id.div)
        button_multiply = findViewById(R.id.mul)
        button_subtraction = findViewById(R.id.minus)
        button_addition = findViewById(R.id.plus)
        button_equals = findViewById(R.id.equal)
        button_root = findViewById(R.id.root)
        remove = findViewById(R.id.rem)
        percent = findViewById(R.id.per)

        C.setOnClickListener {
            input.text = Editable.Factory.getInstance().newEditable("")
            output.text = Editable.Factory.getInstance().newEditable("")
        }
        button_root.setOnClickListener {
            input.text=Editable.Factory.getInstance().newEditable("root( , )")
            Toast.makeText(this, "root(N,X) = N√x", Toast.LENGTH_LONG).show()
        }
        remove.setOnClickListener {
            val length = input.text.length
            if (length > 0) {
                input.text = Editable.Factory.getInstance().newEditable(input.text.subSequence(0, length - 1))
            }
        }
        button_0.setOnClickListener {
            input.text = addToInputText("0")
        }
        button_1.setOnClickListener {
            input.text = addToInputText("1")
        }
        button_2.setOnClickListener {
            input.text = addToInputText("2")
        }
        button_3.setOnClickListener {
            input.text = addToInputText("3")
        }
        button_4.setOnClickListener {
            input.text = addToInputText("4")
        }
        button_5.setOnClickListener {
            input.text = addToInputText("5")
        }
        button_6.setOnClickListener {
            input.text = addToInputText("6")
        }
        button_7.setOnClickListener {
            input.text = addToInputText("7")
        }
        button_8.setOnClickListener {
            input.text = addToInputText("8")
        }
        button_9.setOnClickListener {
            input.text = addToInputText("9")
        }
        button_dot.setOnClickListener {
            input.text = addToInputText(".")
        }
        button_division.setOnClickListener {
            input.text = addToInputText("÷") // ALT + 0247
        }
        button_multiply.setOnClickListener {
            input.text = addToInputText("×") // ALT + 0215
        }

        button_subtraction.setOnClickListener {
            input.text = addToInputText("-")
        }
        button_addition.setOnClickListener {
            input.text = addToInputText("+")
        }
        percent.setOnClickListener {
            input.text = addToInputText("%")
        }
        button_equals.setOnClickListener {
            showResult()
        }
    }
    private fun addToInputText(buttonValue: String): Editable {
        val currentInput = input.text
        return Editable.Factory.getInstance().newEditable(currentInput).append(buttonValue)
    }

    private fun getInputExpression(): String {
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        expression = expression.replace(Regex("%"),"/100")
        return expression
    }
    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                output.text = Editable.Factory.getInstance().newEditable("")
            } else {
                // Show Result
                output.text = Editable.Factory.getInstance().newEditable(DecimalFormat("0.######").format(result))
            }
        } catch (e: Exception) {
            // Show Error Message
            output.text = Editable.Factory.getInstance().newEditable("")
        }
    }
}