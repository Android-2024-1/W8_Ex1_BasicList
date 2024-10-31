package com.example.basiclist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var editNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các view
                editNumber = findViewById(R.id.editNumber)
                radioGroup = findViewById(R.id.radioGroup)
                btnShow = findViewById(R.id.btnShow)
                listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        tvError.text = ""
        val input = editNumber.text.toString()

        if (input.isEmpty()) {
            tvError.text = "Vui lòng nhập số!"
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            tvError.text = "Vui lòng nhập số nguyên dương hợp lệ!"
            return
        }

        val numbers = when (radioGroup.checkedRadioButtonId) {
            R.id.radioEven -> getEvenNumbers(n)
            R.id.radioOdd -> getOddNumbers(n)
            R.id.radioSquare -> getSquareNumbers(n)
            else -> {
                tvError.text = "Vui lòng chọn loại số!"
                return
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listView.adapter = adapter
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { num ->
            val sqrt = sqrt(num.toDouble()).toInt()
            sqrt * sqrt == num
        }
    }
}