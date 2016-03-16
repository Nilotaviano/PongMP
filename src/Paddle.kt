import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode

/**
 * Created by nilot on 14/03/2016.
 */
class Paddle(var xPos: Double, val yPos: Double) : IUpdateable, IDrawable
{
    val maxSpd = 0.05
    val width = Constants.myWorldSize / 40
    val height = Constants.myWorldSize / 200

    // TODO: 4real
    override fun update(interval: Double) {
      val movement = maxSpd * interval
        if((InputManager.input.contains(KeyCode.LEFT) || InputManager.input.contains(KeyCode.A))
          && xPos - movement - width/2 >= 0)
            xPos -= movement
        if((InputManager.input.contains(KeyCode.RIGHT) || InputManager.input.contains(KeyCode.D))
          && xPos + movement + width/2 <= Constants.myWorldSize)
            xPos += movement
    }

    override fun draw(gc: GraphicsContext) {
        gc.fillRoundRect(xPos, yPos, width, height, 0.0, 0.0)
    }
}
