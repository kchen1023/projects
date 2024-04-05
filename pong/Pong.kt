package com.example.project5

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Point
import android.graphics.Rect

class Pong {
    // ball position
    private var ballX = 0
    private var ballY = 0
    private var ballCoords : Point? = null
    var ballOffScreen: Boolean = false
    // speed
    private var ballSpeedX = 0
    private var ballSpeedY = 0
    // size
    private var ballRadius = 0
    // paddle position
    private var paddlePosX = 0.0f
    // size
    private var paddleRect : Rect? = null
    // time
    private var deltaTime = 0 // ms
    // screen
    private var screenWidth = 0
    private var screenHeight = 0
    // score
    private var score = 0
    private var bestScore = 0
    // status of game
    private var gameStarted : Boolean = false
    private var showScores : Boolean = false

    constructor(
        context: Context,
        newBallX: Int, newBallY: Int, newBallRadius: Int,
        newBallSpeedX: Int, newBallSpeedY: Int,
        newPaddleRect: Rect) {

        setBallX(newBallX)
        setBallY(newBallY)
        ballCoords = Point(newBallX, newBallY)
        setBallRadius(newBallRadius)
        setBallSpeedX(newBallSpeedX)
        setBallSpeedY(newBallSpeedY)
        setPaddleRect(newPaddleRect)
    }

    // methods to move paddle
    fun moveRect(x : Float) {
        paddleRect = Rect(
            (x - 120).toInt(), // left
            (screenHeight / 2 + 590).toInt(), // top
            (x + 120).toInt(), // right
            (screenHeight / 2  + 610).toInt() // bottom
        )
    }

    // methods to move ball
    fun moveBall() {
        ballX += ballSpeedX
        ballY += ballSpeedY
        ballCoords = Point(ballX, ballY)
    }

    // methods to detect if ball has hit wall
    fun ballHitWall() {
        if (ballCoords!!.x - ballRadius <= 0) ballSpeedX *= -1
        if (ballCoords!!.x + ballRadius >= screenWidth) ballSpeedX *= -1
        if (ballCoords!!.y - ballRadius <= 0) ballSpeedY *= -1
    }

    // methods to detect if ball has hit paddle
    fun ballHitPaddle() : Boolean { // use intersect
        val ballRect = Rect(
            ballCoords!!.x - ballRadius,
            ballCoords!!.y - ballRadius,
            ballCoords!!.x + ballRadius,
            ballCoords!!.y + ballRadius)

        return (Rect.intersects(ballRect, paddleRect!!))
    }

    // if ball hit paddle, bounce
    fun bounceBall() {
        if (ballCoords!!.y + ballRadius >= paddleRect!!.top) {
            ballSpeedY *= -1
            score += 1
        }
    }

    fun isBallOffScreen() : Boolean {
        return ballCoords!!.y - ballRadius >= screenHeight
    }

    fun reset(context : Context) {
        if (score > bestScore) {
            bestScore = score
        }
        ballOffScreen = false
        gameStarted = false
        ballY = screenHeight / 2 - 800
        ballCoords = Point(ballX, ballY)
    }

    // getters and setters =========================================================================
    fun getShowScores() : Boolean {
        return showScores
    }

    fun setShowScores(x : Boolean) {
        showScores = x
    }

    fun setGameStarted(started : Boolean) {
        gameStarted = started
    }

    fun getGameStarted() : Boolean {
        return gameStarted
    }

    fun setPaddleRect(newPaddleRect: Rect) {
        paddleRect = newPaddleRect
    }

    fun setScreenWidth(newScreenWidth: Int) {
        screenWidth = newScreenWidth
    }

    fun setScreenHeight(newScreenHeight: Int) {
        screenHeight = newScreenHeight
    }

    fun setDeltaTime(newDeltaTime : Int) {
        deltaTime = newDeltaTime
    }

    fun setBallX(newBallX: Int) {
        ballX = newBallX
    }

    fun setBallY(newBallY: Int) {
        ballY = newBallY
    }

    fun setBallRadius(newBallRadius: Int) {
        ballRadius = newBallRadius
    }

    fun setBallSpeedX(newBallSpeedX: Int) {
        ballSpeedX = newBallSpeedX
    }

    fun setBallSpeedY(newBallSpeedY: Int) {
        ballSpeedY = newBallSpeedY
    }

    fun getBallRadius() : Int {
        return ballRadius
    }

    fun getBallX() : Int {
        return ballX
    }

    fun getBallY() : Int {
        return ballY
    }

    fun setPaddleX(x: Float) {
        paddlePosX = x
    }

    fun getPaddleX(): Float {
        return paddlePosX
    }

    fun getScore() : Int {
        return score
    }

    fun getBestScore() : Int {
        return bestScore
    }

    fun setScore(newScore : Int) {
        score = newScore
    }

    // ==============

    fun setPreferences(context: Context) {
        var pref : SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences",
                Context.MODE_PRIVATE )
        var edit : SharedPreferences.Editor = pref.edit()

        edit.putInt(PREFERENCE_BEST_SCORE, bestScore)

        edit.commit()
    }

    companion object{
        private const val PREFERENCE_BEST_SCORE : String = "bestScore"
    }

}