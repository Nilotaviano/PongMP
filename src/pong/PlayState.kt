package pong

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.stage.Stage

abstract class PlayState : Application() {

    lateinit internal var ball: Ball
    lateinit internal var myPaddle: Paddle
    lateinit internal var opponentPaddle: Paddle

    override fun start(primaryStage: Stage) {
        val root = Group()
        primaryStage.scene.root = root

        val canvas = Canvas(Constants.Companion.windowWidth, Constants.Companion.windowHeight)
        root.children.setAll(canvas)

        InputManager.initialize(primaryStage.scene)

        val gc = canvas.graphicsContext2D

        object : AnimationTimer() {
            var previousNanoTime = System.nanoTime()

            override fun handle(currentNanoTime: Long) {
                val nanoTimeElapsed = (currentNanoTime - previousNanoTime) / 100000000.0
                previousNanoTime = System.nanoTime()
                gc.clearRect(0.0, 0.0, canvas.width, canvas.height)
                ball.draw(gc)
                myPaddle.draw(gc)
                opponentPaddle.draw(gc)

                update(nanoTimeElapsed)
            }


        }.start()
    }

    internal abstract fun update(nanoTimeElapsed: Double)
}
