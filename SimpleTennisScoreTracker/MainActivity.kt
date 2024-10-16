package com.example.tennisscoretracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tennisGame : Tennis

    private lateinit var p1 : Button
    private lateinit var p2 : Button

    private lateinit var p1SetScore : TextView
    private lateinit var p1Score : TextView
    private lateinit var p2SetScore : TextView
    private lateinit var p2Score : TextView

    private lateinit var winner : TextView
    private lateinit var newGame : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize game
        tennisGame = Tennis("P1", "P2")

        // initialize players
        p1 = findViewById(R.id.player1)
        p1.text = tennisGame.getP1Name()

        p2 = findViewById(R.id.player2)
        p2.text = tennisGame.getP2Name()

        // initialize scores
        p1SetScore = findViewById(R.id.player1_curr_set_score)
        p1SetScore.text = tennisGame.getP1SetScore()
        p1Score = findViewById(R.id.player1_curr_score)
        p1Score.text = tennisGame.getP1Score()

        p2SetScore = findViewById(R.id.player2_curr_set_score)
        p2SetScore.text = tennisGame.getP2SetScore()
        p2Score = findViewById(R.id.player2_curr_score)
        p2Score.text = tennisGame.getP2Score()

        // make winner widget invisible until winner is chosen
        winner = findViewById(R.id.winner)
        winner.visibility = View.INVISIBLE

        // new game
        newGame = findViewById(R.id.newGame)
    }

    fun player1(v : View) {
        // calculate
        tennisGame.player1Scores()

        // update scores
        p1SetScore.text = tennisGame.getP1SetScore()
        p1Score.text = tennisGame.getP1Score()
        p2SetScore.text = tennisGame.getP2SetScore()
        p2Score.text = tennisGame.getP2Score()

        //checks for winner
        if (tennisGame.getWinner() != "") {
            winner.text = tennisGame.getWinner()
            p1.isEnabled = false
            p2.isEnabled = false
            winner.visibility = View.VISIBLE
        }
    }

    fun player2(v : View) {
        // calculate
        tennisGame.player2Scores()

        // update scores
        p1SetScore.text = tennisGame.getP1SetScore()
        p1Score.text = tennisGame.getP1Score()
        p2SetScore.text = tennisGame.getP2SetScore()
        p2Score.text = tennisGame.getP2Score()

        // checks for winners
        if (tennisGame.getWinner() != "") {
            winner.text = tennisGame.getWinner()
            p1.isEnabled = false
            p2.isEnabled = false
            winner.visibility = View.VISIBLE
        }
    }

    fun playAgain(v : View) {
        tennisGame.reset()

        // initialize scores
        p1SetScore = findViewById(R.id.player1_curr_set_score)
        p1SetScore.text = tennisGame.getP1SetScore()
        p1Score = findViewById(R.id.player1_curr_score)
        p1Score.text = tennisGame.getP1Score()

        p2SetScore = findViewById(R.id.player2_curr_set_score)
        p2SetScore.text = tennisGame.getP2SetScore()
        p2Score = findViewById(R.id.player2_curr_score)
        p2Score.text = tennisGame.getP2Score()

        // make winner widget invisible until winner is chosen
        winner = findViewById(R.id.winner)
        winner.visibility = View.INVISIBLE

        p1.isEnabled = true
        p2.isEnabled = true
    }
}