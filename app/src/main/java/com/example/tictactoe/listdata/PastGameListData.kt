package com.example.tictactoe.listdata

import android.content.SharedPreferences
import com.example.tictactoe.Yash_GameModel
import java.util.ArrayList
import java.util.HashMap
import com.example.tictactoe.Yash_GameModel.Constanst

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PastGameListData {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<PastGame> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PastGame> = HashMap()

    private var COUNT = 0

//    init {
//        // Add some sample items.
//        for (i in 1..COUNT) {
//            addItem(createDummyItem(i))
//        }
//    }

    fun loadPastGameData(sharedPref: SharedPreferences){
        COUNT = sharedPref.getInt(Yash_GameModel.Constanst.NUMBER_OF_GAMES_PLAYED,0)

        for(i in 1..3){
            val gameResult = sharedPref.getString(Yash_GameModel.Constanst.GAME_RESULT + i, "ERROR")
            val dateTime = sharedPref.getString(Yash_GameModel.Constanst.DATE_TIME + i, "ERROR")
            val orderOfMoves = sharedPref.getString(Yash_GameModel.Constanst.ORDER_OF_MOVES,"")

            val pastGame = PastGame("Game "+i, gameResult!!, dateTime!!, orderOfMoves!!)
            addItem(pastGame)1
        }
    }

    private fun addItem(item: PastGame) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

//    private fun createDummyItem(position: Int): PastGame {
//        return PastGame(position.toString(), "Item " + position, makeDetails(position))
//    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Time satmp for game: ").append(position)
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class PastGame(val id: String, val who_won: String, val timestamp: String, val orderOfMoves: String) {
        override fun toString(): String = who_won
    }
}
