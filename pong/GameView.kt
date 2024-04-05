package com.example.project5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import kotlin.random.Random

class GameView : View {
    private lateinit var game : Pong
    private var paint : Paint = Paint()

    private lateinit var paddleRect : Rect // boundary for paddle
    private var screenWidthMiddle = (resources.displayMetrics.widthPixels / 2).toFloat()
    private var screenHeightMiddle = (resources.displayMetrics.heightPixels / 2).toFloat()
    private var screenBottom = (screenHeightMiddle + 600).toFloat()

    constructor(context : Context, width : Int, height : Int) : super(context) {
        paint.color = Color.rgb(200, 110, 110)
        paint.isAntiAlias = true
        paint.strokeWidth = 20.0f

        paddleRect = Rect(
            (screenWidthMiddle - 120).toInt(), // left
            (screenBottom - 10).toInt(), // top
            (screenWidthMiddle + 120).toInt(), // right
            (screenBottom + 10).toInt() // bottom
        )

        val randomX = Random.nextInt(100, resources.displayMetrics.widthPixels - 100)
        game = Pong(
            context,
            randomX, // newBallX, changes X position
            resources.displayMetrics.heightPixels / 2 - 800,
            30,
            if (Random.nextBoolean()) 35 else -35, // newBallSpeedX
            35,
            paddleRect
        )

        game.setScreenWidth(resources.displayMetrics.widthPixels)
        game.setScreenHeight(resources.displayMetrics.heightPixels)
        game.setDeltaTime(DELTA_TIME)
        game.setPaddleX(screenWidthMiddle)
    }

    fun getGame() : Pong {
        return game
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw ball
        canvas.drawCircle(
            game.getBallX().toFloat(),
            game.getBallY().toFloat(),
            game.getBallRadius().toFloat(),
            paint
        )

        // draw paddle
        canvas.drawLine(game.getPaddleX() - 120, screenBottom, game.getPaddleX() + 120, screenBottom, paint)

        if (game.getShowScores()) {
            paint.textSize = 100f

            var scoreMessage = "Score: ${game.getScore()}"
            canvas.drawText(scoreMessage, screenWidthMiddle - 170, screenHeightMiddle - 100, paint)

            var bestScoreMessage = "Best Score: ${game.getBestScore()}"
            canvas.drawText(bestScoreMessage, screenWidthMiddle - 270, screenHeightMiddle, paint)
        }
    }

    companion object {
        val DELTA_TIME : Int = 100
    }
}