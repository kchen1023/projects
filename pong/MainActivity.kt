package com.example.project5

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import java.util.Timer

class MainActivity : AppCompatActivity() {
    private lateinit var game : Pong
    private lateinit var gameView : GameView

    private lateinit var detector: GestureDetector

    private lateinit var pool: SoundPool
    private var ballHitId = 0

    var gameTimer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenWidth: Int = resources.displayMetrics.widthPixels
        val screenHeight: Int = resources.displayMetrics.heightPixels

        val statusBarHeightId: Int =
            resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight: Int = resources.getDimensionPixelSize(statusBarHeightId)

        gameView = GameView(this, screenWidth, screenHeight - statusBarHeight)
        game = gameView.getGame()

        setContentView(gameView)

        // event handling
        val th = TouchHandler()
        detector = GestureDetector(this, th)
        detector.setOnDoubleTapListener(th)

        // sound
        val builder: SoundPool.Builder = SoundPool.Builder()
        pool = builder.build()
        ballHitId = pool.load(this, R.raw.boop, 1)
    }

    fun playSound(soundId: Int) {
        pool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    fun updateModel() {
        if (game.getGameStarted()) {
            game.moveBall()
            game.ballHitWall()
            if (game.ballHitPaddle()) {
                playSound(ballHitId)
                game.bounceBall()
            }

            if (game.isBallOffScreen()) {
                game.setPreferences(this)
                game.setGameStarted(false)
                game.setShowScores(true)
                game.reset(this)
                gameTimer.cancel()
            }
        }
    }

    fun updateView() {
        gameView.postInvalidate()
    }

    fun movePaddle(e : MotionEvent) {
        val x : Float = e.x
        game.setPaddleX(x)
        game.moveRect(x)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            detector.onTouchEvent(event)
        }

        if (event!!.action == MotionEvent.ACTION_DOWN && !game.getGameStarted()) {
            game.setGameStarted(true)
            game.setScore(0)
            game.setShowScores(false)
            gameTimer = Timer()
            val task = GameTimerTask(this)
            gameTimer.schedule(task, 0L, GameView.DELTA_TIME.toLong())
        }

        return super.onTouchEvent(event)
    }

    // event handling
    inner class TouchHandler: GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            movePaddle(e2)
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }
}