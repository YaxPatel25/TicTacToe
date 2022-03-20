package com.example.tictactoe

import android.content.SharedPreferences
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Yash_GameModel {
    var movesPlayed = Array(10) {i -> ""}
    var whoseTurn = Constanst.X
    var lastPlayed = ""
    var whoWon = ""
    var numberOfMovesPlayed = 0         //To finish the game
    var orderOfMovesPlayed = ""

    //ini winning combinations 2D array
    val winningCombination : Array<IntArray> = arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(4,5,6),
            intArrayOf(7,8,9),
            intArrayOf(1,4,7),
            intArrayOf(2,5,8),
            intArrayOf(3,6,9),
            intArrayOf(1,5,9),
            intArrayOf(3,5,7),
            )

    //Change X to O
    fun processTouch(num: Int) : String{
        orderOfMovesPlayed += num.toString() + ","

        numberOfMovesPlayed++

        val playingNow = whoseTurn
        movesPlayed[num] = whoseTurn
        lastPlayed = whoseTurn

        if(whoseTurn == Constanst.X){
            whoseTurn = Constanst.O
        }else{
            whoseTurn = Constanst.X
        }

        return playingNow       //we should return otherwise it won't update
    }

    fun isGameFinished() : Boolean{
        if(numberOfMovesPlayed<5){
            return false
        }

        //otherwise iterate over winningCombinstion
        //check if any array element in winningCombinations
        //has lastPlayed in all 3 positions

        for(item in winningCombination){
            val positions = item as IntArray

            if(movesPlayed[positions[0]] == lastPlayed
                && movesPlayed[positions[1]] == lastPlayed
                && movesPlayed[positions[2]] == lastPlayed){
                //game over
                whoWon = lastPlayed
//                saveGame()
                return true
            }
        }

        //For draw condition
        if(numberOfMovesPlayed == 9){
//            saveGame()
            return true
        }

        return false
    }

    // To save the game data
    fun saveGame(sharedPref: SharedPreferences){
        var numberOfGamesPlayed = sharedPref.getInt(Constanst.NUMBER_OF_GAMES_PLAYED,0)
        numberOfGamesPlayed++

        with(sharedPref.edit()){
            putInt(Constanst.NUMBER_OF_GAMES_PLAYED, numberOfGamesPlayed)
            putString(Constanst.GAME_RESULT + numberOfGamesPlayed, whoWon)

            val dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("D/m/y H:m:ss"))
            putString(Constanst.DATE_TIME + numberOfGamesPlayed,dateTime)

            putString(Constanst.ORDER_OF_MOVES + numberOfGamesPlayed, orderOfMovesPlayed)

            apply()

        }
    }

    class Constanst{
        companion object{
            val X = "X"
            val O = "O"
            val NUMBER_OF_GAMES_PLAYED = "numberOfGamesPlayed"
            val GAME_RESULT = "gameResult"
            val DATE_TIME = "dateTime"
            val ORDER_OF_MOVES = "orderOfMoves"

        }
    }
}