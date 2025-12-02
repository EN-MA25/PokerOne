package com.eriknordvall.pokerone

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

class GameActivity : AppCompatActivity() {

    //var cardsSelected : BooleanArray = booleanArrayOf(false, false, false, false, false)
    var deck: Deck = Deck()

    lateinit var continueButton: Button
    lateinit var currentCards : Array<Card>
    lateinit var imageViews : Array<ImageView>
    lateinit var handTextView : TextView


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
        handTextView = findViewById(R.id.handTextView)

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

        setHandTextViewText()

    }

    fun setHandTextViewText() {
        val handValue = checkCurrentHand()
        val hand = handValueToString(handValue)
        handTextView.text = "You got ${hand}\nChoose the cards\nyou would like to switch"
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
        setHandTextViewText()
    }

    fun getResIdFromString(string: String) : Int {
        return getResources().getIdentifier(string, "drawable", packageName)
    }

    fun checkCurrentHand() : Int {

        var handValue = 0


        val valueArray : List<Int> = intArrayOf(currentCards[0].intValue, currentCards[1].intValue, currentCards[2].intValue, currentCards[3].intValue, currentCards[4].intValue).sorted()
        val valueSet : Set<Int> = valueArray.toSet()

        val suitArray : List<Int> = intArrayOf(currentCards[0].intSuit, currentCards[1].intSuit, currentCards[2].intSuit, currentCards[3].intSuit, currentCards[4].intSuit).sorted()
        val suitSet : Set<Int> = suitArray.toSet()

        if (valueSet.size == 2) {
            // Four of a kind or Full House
            if (valueArray[1] == valueArray[3]) {
                // Four of a kind
                handValue = 7
            } else {
                // Full House
                handValue = 6
            }

        } else if (valueSet.size == 3) {
            // Three of a kind or 2 pairs
            if (valueArray[0] == valueArray[2] || valueArray[1] == valueArray[3] || valueArray[2] == valueArray[4]) {
                // Three of a kind
                handValue = 3
            } else {
                // 2 pairs
                handValue = 2
            }
        } else if (valueSet.size == 4) {
            // One pair
            handValue = 1
        } else if (suitSet.size == 1) {
            // Flush or strait flush
            if (valueArray[4]-valueArray[0] == 4) {
                // Straight flush but not highest
                handValue = 8
            } else if (valueArray[0] == 1 && valueArray.sum() == 47) {
                // Royal straight flush
                handValue = 9
            } else {
                // Flush
                handValue = 5
            }
        } else if (valueArray[4]-valueArray[0] == 4) {
            // Straight but not highest
            handValue = 4
        } else if (valueArray[0] == 1 && valueArray.sum() == 47) {
            // The highest straight
            handValue = 4
        }

        return handValue
    }

    fun handValueToString(handValue: Int) : String {
        return when (handValue) {
            0 -> "High Card"
            1 -> "a pair"
            2 -> "2 pairs"
            3 -> "Three of a kind"
            4 -> "a Straight"
            5 -> "a Flush"
            6 -> "a Full House"
            7 -> "Four of a kind"
            8 -> "a Straight Flush"
            9 -> "a Royal Straight Flush"
            else -> ""
        }
    }
}