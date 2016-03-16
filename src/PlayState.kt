/**
 * Created by nilot on 15/03/2016.
 */

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.stage.Stage

class PlayState : Application() {

    override fun start(primaryStage: Stage) {
        val root = Group()
        primaryStage.scene.root = root

        val canvas = Canvas(Constants.windowWidth, Constants.windowHeight)
        root.children.setAll(canvas)

        InputManager.initialize(primaryStage.scene)

        val gc = canvas.graphicsContext2D

        val ball = Ball(50.0, 50.0, 25.0, 25.0)
        val paddle = Paddle(Constants.windowWidth / 2, Constants.windowHeight * 0.05)
        object : AnimationTimer() {
            var previousNanoTime = System.nanoTime()

            override fun handle(currentNanoTime: Long) {
                val nanoTimeElapsed = (currentNanoTime - previousNanoTime) / 100000000.0
                previousNanoTime = System.nanoTime()
                gc.clearRect(0.0, 0.0, canvas.width, canvas.height)
                ball.update(nanoTimeElapsed)
                ball.draw(gc)
                paddle.update(nanoTimeElapsed)
                paddle.draw(gc)

                // TODO: Proper collision treatment
                if (ball.collidesWith(paddle))
                    ball.ySpd = -ball.ySpd
            }
        }.start()

    }
}
