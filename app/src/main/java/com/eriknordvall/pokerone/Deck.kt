package com.eriknordvall.pokerone

import android.util.Log

class Deck {
    var cards: MutableList<Card> = mutableListOf()
    init {
        for (cardSuit in 0..3) {
            for (cardValue in 1..13) {
                cards.add(Card(cardValue, cardSuit))
            }

        }
        cards.shuffle()
    }

    fun logOrder() {
        for (card in cards) {
            Log.d("CARD", card.imageText())
        }
    }
}