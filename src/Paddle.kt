import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode

class Paddle(var x: Double, val y: Double) : IUpdateable, IDrawable, ICollidable
{
    val maxSpd = 50
    val width = Constants.windowWidth / 10
    val height = Constants.windowHeight / 100

    override val bounds: Boundary
        get() = Boundary(x, y, width, height)

    override fun update(interval: Double) {
      val movement = maxSpd * interval
        if((InputManager.input.contains(KeyCode.LEFT) || InputManager.input.contains(KeyCode.A))
                && x - movement >= 0)
            x -= movement
        if((InputManager.input.contains(KeyCode.RIGHT) || InputManager.input.contains(KeyCode.D))
                && x + movement + width <= Constants.windowWidth)
            x += movement
    }

    override fun draw(gc: GraphicsContext) {
        gc.fillRoundRect(x, y, width, height, 0.0, 0.0)
    }

    override fun collidesWith(other: ICollidable): Boolean = bounds.intersectsWith(other.bounds)
}
