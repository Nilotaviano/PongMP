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

        primaryStage.width = Constants.myWorldSize
        primaryStage.height = Constants.myWorldSize

        val canvas = Canvas(Constants.myWorldSize, Constants.myWorldSize)
        root.children.setAll(canvas);

        InputManager.initialize(primaryStage.scene)

        val gc = canvas.graphicsContext2D

        val ball = Ball(50.0, 50.0, 0.05, 0.05)
        object : AnimationTimer() {
            val startNanoTime = System.nanoTime()

            override fun handle(currentNanoTime: Long) {
                val t: Double = (currentNanoTime - startNanoTime) / 1000000000.0;
                gc.clearRect(0, 0, Constants.myWorldSize, Constants.myWorldSize);
                ball.update(t)
                ball.draw(gc)
            }
        }.start()

    }
}
