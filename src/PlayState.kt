/**
 * Created by nilot on 15/03/2016.
 */

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.stage.Stage

abstract class PlayState : Application() {

    internal val ball = Ball(50.0, 50.0, 25.0, 25.0)
    lateinit internal var myPaddle: Paddle
    lateinit internal var opponentPaddle: Paddle

    override fun start(primaryStage: Stage) {
        val root = Group()
        primaryStage.scene.root = root

        val canvas = Canvas(Constants.windowWidth, Constants.windowHeight)
        root.children.setAll(canvas)

        InputManager.initialize(primaryStage.scene)

        val gc = canvas.graphicsContext2D

        object : AnimationTimer() {
            var previousNanoTime = System.nanoTime()

            override fun handle(currentNanoTime: Long) {
                val nanoTimeElapsed = (currentNanoTime - previousNanoTime) / 100000000.0
                previousNanoTime = System.nanoTime()
                gc.clearRect(0.0, 0.0, canvas.width, canvas.height)
                ball.update(nanoTimeElapsed)
                ball.draw(gc)
                myPaddle.update(nanoTimeElapsed)
                myPaddle.draw(gc)
                opponentPaddle.draw(gc)

                // TODO: Proper collision treatment
                if (ball.collidesWith(myPaddle) || ball.collidesWith(opponentPaddle))
                    ball.ySpd = -ball.ySpd
            }
        }.start()

    }
}
