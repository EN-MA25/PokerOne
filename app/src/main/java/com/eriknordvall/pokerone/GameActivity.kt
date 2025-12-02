package com.eriknordvall.pokerone

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

class GameActivity : AppCompatActivity() {

    var cardSelected : BooleanArray = booleanArrayOf(false, false, false, false, false)
    var deck: Deck = Deck()
    lateinit var card1: ImageView
    lateinit var card2: ImageView
    lateinit var card3: ImageView
    lateinit var card4: ImageView
    lateinit var card5: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        deck.logOrder()
        card1 = findViewById(R.id.card1)
        card2 = findViewById(R.id.card2)
        card3 = findViewById(R.id.card3)
        card4 = findViewById(R.id.card4)
        card5 = findViewById(R.id.card5)

        val id1 = getResources().getIdentifier(deck.cards[0].imageText(), "drawable", getPackageName())
        val id2 = getResources().getIdentifier(deck.cards[1].imageText(), "drawable", getPackageName())
        val id3 = getResources().getIdentifier(deck.cards[2].imageText(), "drawable", getPackageName())
        val id4 = getResources().getIdentifier(deck.cards[3].imageText(), "drawable", getPackageName())
        val id5 = getResources().getIdentifier(deck.cards[4].imageText(), "drawable", getPackageName())
        card1.setImageResource(id1)
        card2.setImageResource(id2)
        card3.setImageResource(id3)
        card4.setImageResource(id4)
        card5.setImageResource(id5)




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