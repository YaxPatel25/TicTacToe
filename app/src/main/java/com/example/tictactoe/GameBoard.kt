package com.example.tictactoe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game_board.*

class GameBoard : AppCompatActivity() {

    var gameModel = Yash_GameModel()
    var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_board)

        setTitle("New Game") // change karyu title page nu .....

        textView.text = gameModel.whoseTurn + "'s Turn"
    }

    fun squareTouched(square: View){
        val id = square.id

        if((square as Button).text.length > 0 || gameOver) {
            return
        }
        var squareIndex = 0
        when(id){
            R.id.button1 -> {squareIndex = 1}
            R.id.button2 -> {squareIndex = 2}
            R.id.button3 -> {squareIndex = 3}
            R.id.button4 -> {squareIndex = 4}
            R.id.button5 -> {squareIndex = 5}
            R.id.button6 -> {squareIndex = 6}
            R.id.button7 -> {squareIndex = 7}
            R.id.button8 -> {squareIndex = 8}
            R.id.button9 -> {squareIndex = 9}
        }

        val movePlayed = gameModel.processTouch(squareIndex)

        //update button with type casting
        (square as Button).text = movePlayed

        //Gameover Recognition
        gameOver = gameModel.isGameFinished()

        if(gameOver) {
                gameModel.saveGame(this.getPreferences(Context.MODE_PRIVATE))
                // update winner data by textview
                if(gameModel.whoWon.length > 0) {
                    textView.text = "Game Over - " + gameModel.lastPlayed + " Won!"
                }else{
                    textView.text = "Draw!"
                }
        }else{
            textView.text = gameModel.whoseTurn + "'s Turn"
        }
    }
}