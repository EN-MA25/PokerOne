package com.eriknordvall.pokerone

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

class GameActivity : AppCompatActivity() {

    var cardSelected : BooleanArray = booleanArrayOf(false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    fun cardPressed(card: View) {
        Log.d("!!!", "Card ${card.tag} ${card.top} ${card.bottom}, ${card.paddingTop} ${card.paddingBottom}, ${card.marginTop} ${card.marginBottom}")

        val tag: Int = card.tag.toString().toInt()

        if (cardSelected[tag]) {
            card.offsetTopAndBottom(64)
            cardSelected[tag] = false
        } else {
            card.offsetTopAndBottom(-64)
            cardSelected[tag] = true
        }



    }
}