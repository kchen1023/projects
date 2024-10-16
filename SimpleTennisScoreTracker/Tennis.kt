package com.example.tennisscoretracker

class Tennis {
    private lateinit var p1_name : String
    private lateinit var p2_name : String
    private var player1_set_score: String = "0"
    private var player1_score: String = "0"
    private var player2_set_score: String = "0"
    private var player2_score: String = "0"
    private var winner: String = ""

    constructor(p1: String, p2: String) {
        p1_name = p1
        p2_name = p2
    }

    // if player 1 scores
    fun player1Scores() {
        if (player1_score == "AD" && player2_score != "AD") {
            player1_set_score = (player1_set_score.toInt() + 1).toString()
            player1_score = "0"
            player2_score = "0"
            checkWinner()
        } else if (player2_score == "AD") {
            player2_score = "40"
        }
        else {
            if (player1_score == "0") {
                player1_score = "15"
            } else if (player1_score == "15") {
                player1_score = "30"
            } else if (player1_score == "30") {
                player1_score = "40"
            } else if (player1_score == "40" && player2_score != "40") {
                player1_set_score = (player1_set_score.toInt() + 1).toString()
                player2_score = "0"
                player1_score = "0"
                checkWinner()
            } else if (player1_score == "40" && player2_score == "40") {
                player1_score = "AD"
            }

            checkWinner()
        }
    }

    // if player 2 scores
    fun player2Scores() {
        if (player2_score == "AD" && player1_score != "AD") {
            player2_set_score = (player2_set_score.toInt() + 1).toString()
            player1_score = "0"
            player2_score = "0"
            checkWinner()
        } else if (player1_score == "AD") {
            player1_score = "40"
        }
        else {
            if (player2_score == "0") {
                player2_score = "15"
            } else if (player2_score == "15") {
                player2_score = "30"
            } else if (player2_score == "30") {
                player2_score = "40"
            } else if (player2_score == "40" && player1_score != "40") {
                player2_set_score = (player2_set_score.toInt() + 1).toString()
                player1_score = "0"
                player2_score = "0"
                checkWinner()
            } else if (player2_score == "40" && player1_score == "40") {
                player2_score = "AD"
            }

            checkWinner()
        }
    }

    fun checkWinner(){
        var p1SetScore = player1_set_score.toInt()
        var p2SetScore = player2_set_score.toInt()
        if (p1SetScore >= 4 && p1SetScore - p2SetScore >= 2) {
            winner = "Player 1 Wins!"
        } else if (p2SetScore>= 4 && p2SetScore - p1SetScore >= 2) {
            winner = "Player 2 Wins!"
        }
    }

    // getters ====================================================================================
    fun getP1Score() : String {
        return player1_score
    }

    fun getP1SetScore() : String {
        return player1_set_score
    }

    fun getP2Score() : String {
        return player2_score
    }

    fun getP2SetScore() : String {
        return player2_set_score
    }

    fun getP1Name() : String {
        return p1_name
    }

    fun getP2Name() : String {
        return p2_name
    }

    fun getWinner() : String {
        return winner
    }

    // reset game
    fun reset() {
        player1_score = "0"
        player1_set_score = "0"
        player2_score = "0"
        player2_set_score = "0"
        winner = ""
    }
}