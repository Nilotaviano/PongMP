package pong

import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode
import pong.rmi.IRemoteBoundary

class Paddle(override var bounds: IRemoteBoundary) : IUpdateable, IDrawable, ICollidable
{
    val maxSpd = 50

    override fun update(interval: Double) {
      val movement = maxSpd * interval
        if((InputManager.input.contains(KeyCode.LEFT) || InputManager.input.contains(KeyCode.A))
                && bounds.x - movement >= 0)
            bounds.x -= movement
        if((InputManager.input.contains(KeyCode.RIGHT) || InputManager.input.contains(KeyCode.D))
                && bounds.x + movement + bounds.width <= Constants.windowWidth)
            bounds.x += movement
    }

    override fun draw(gc: GraphicsContext) {
        gc.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 0.0, 0.0)
    }

    override fun collidesWith(other: ICollidable): Boolean = bounds.intersectsWith(other.bounds)
}
