package com.eriknordvall.pokerone

class Card(var intValue: Int, var intSuit: Int, var selected: Boolean) {

    val stringValue: String
        get() {
            return when (intValue) {
                1 -> "a"
                2 -> "2"
                3 -> "3"
                4 -> "4"
                5 -> "5"
                6 -> "6"
                7 -> "7"
                8 -> "8"
                9 -> "9"
                10 -> "t"
                11 -> "j"
                12 -> "q"
                13 -> "k"
                else -> {
                    ""
                }
            }
        }

    val stringSuit: String
        get() {
            return when (intSuit) {
                0 -> "d"
                1 -> "s"
                2 -> "h"
                3 -> "c"
                else -> {
                    ""
                }
            }
        }

    fun imageText() : String {
        return "card_${stringValue}${stringSuit}"
    }


}