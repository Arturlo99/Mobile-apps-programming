package com.example.a2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Switch
import android.widget.TextView
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var costInput: EditText? = null
    private var tipInput: EditText? = null
    private var serviceSwitch: Switch? = null
    private var calculateButton: Button? = null
    private var foodRatingBar: RatingBar? = null
    private var totalCostTextView: TextView? = null
    private var tipCostTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        costInput = findViewById(R.id.costInput)
        tipInput = findViewById(R.id.tipInput)
        serviceSwitch = findViewById(R.id.serviceSwitch)
        totalCostTextView = findViewById(R.id.totalCostTextView)
        foodRatingBar = findViewById(R.id.foodRatingBar)
        calculateButton = findViewById(R.id.calculateButton)
        tipCostTextView = findViewById(R.id.tipCostTextView)

        calculateButton!!.setOnClickListener {
            val orderCost = costInput!!.text.toString().toFloat()
            val tipPercentage = tipInput!!.text.toString().toFloat() / 100
            var serviceQualityFactor = 1f;
            val isServiceGood = serviceSwitch!!.isChecked
            val foodRatingFactor = foodRatingBar!!.rating
            if (isServiceGood) {
                serviceQualityFactor = 1.25f
            }
            val totalCost =
                orderCost + (orderCost * tipPercentage * serviceQualityFactor * (1 + foodRatingFactor / 10))
            val tipCost = totalCost - orderCost
            val resultText = "$totalCost zł"
            val tipText = "$tipCost zł"
            totalCostTextView!!.text = resultText
            tipCostTextView!!.text = tipText
        }
    }
}