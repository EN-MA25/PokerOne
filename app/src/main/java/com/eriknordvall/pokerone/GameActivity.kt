package com.eriknordvall.pokerone

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

class GameActivity : AppCompatActivity() {

    //var cardsSelected : BooleanArray = booleanArrayOf(false, false, false, false, false)
    var deck: Deck = Deck()

    lateinit var continueButton: Button
    lateinit var currentCards : Array<Card>
    lateinit var imageViews : Array<ImageView>


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

        continueButton = findViewById(R.id.continueButton)

        val cardImageView1 = findViewById<ImageView>(R.id.card1)
        val cardImageView2 = findViewById<ImageView>(R.id.card2)
        val cardImageView3 = findViewById<ImageView>(R.id.card3)
        val cardImageView4 = findViewById<ImageView>(R.id.card4)
        val cardImageView5 = findViewById<ImageView>(R.id.card5)
        imageViews = arrayOf(cardImageView1, cardImageView2, cardImageView3, cardImageView4, cardImageView5)

        val currentCard1 = deck.cards.removeFirst()
        val currentCard2 = deck.cards.removeFirst()
        val currentCard3 = deck.cards.removeFirst()
        val currentCard4 = deck.cards.removeFirst()
        val currentCard5 = deck.cards.removeFirst()
        currentCards = arrayOf(currentCard1, currentCard2, currentCard3, currentCard4, currentCard5)

        for (i in 0..<currentCards.size) {
            val resId = getResIdFromString(currentCards[i].imageText())
            imageViews[i].setImageResource(resId)
        }

    }

    fun cardPressed(imageView: View) {
        Log.d("!!!", "Card ${imageView.tag} ${imageView.top} ${imageView.bottom}, ${imageView.paddingTop} ${imageView.paddingBottom}, ${imageView.marginTop} ${imageView.marginBottom}")

        val index: Int = imageView.tag.toString().toInt()
        val card = currentCards[index]

        if (card.selected) {
            imageView.offsetTopAndBottom(64)
            card.selected = false
        } else {
            imageView.offsetTopAndBottom(-64)
            card.selected = true
        }

        var text = getString(R.string.happy_with_my_cards)
        for (card in currentCards) {
            if (card.selected) {
                text = getString(R.string.switch_cards)
                break
            }
        }
        continueButton.text = text

    }

    fun continueButtonPressed(button: View) {
        for (i in 0..<currentCards.size) {
            val currentCard = currentCards[i]

            if (currentCard.selected) {
                val card = deck.cards.removeFirst()
                val imageView = imageViews[i]
                imageView.setImageResource(getResIdFromString(card.imageText()))
                currentCards[i] = card
                imageView.offsetTopAndBottom(64)
            }
        }
        continueButton.text = getString(R.string.happy_with_my_cards)
    }

    fun getResIdFromString(string: String) : Int {
        return getResources().getIdentifier(string, "drawable", getPackageName())
    }

    /*fun checkCurrentHand() : String {

    }*/
}