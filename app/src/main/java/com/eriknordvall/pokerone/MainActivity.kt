package com.eriknordvall.pokerone

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var numberOfDraws = 3
    lateinit var numberOfTriesTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberOfTriesTextView = findViewById(R.id.numberOfDrawsTextView)
        setTextWithNumber(numberOfDraws)
    }

    private fun setTextWithNumber(number: Int) {
        numberOfTriesTextView.text = "Number of tries: $number"
    }
    fun startGame(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("numberOfDraws", numberOfDraws)
        startActivity(intent)
    }

    fun addButtonPressed(view: View) {
        numberOfDraws++
        if (numberOfDraws >= 10) {
            numberOfDraws = 10
        }
        setTextWithNumber(numberOfDraws)
    }

    fun minusButtonPressed(view: View) {
        numberOfDraws--
        if (numberOfDraws < 1) {
            numberOfDraws = 1
        }
        setTextWithNumber(numberOfDraws)
    }
}