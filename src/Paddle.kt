import javafx.scene.input.KeyCode

/**
 * Created by nilot on 14/03/2016.
 */
class Paddle (var xPos:Float,val yPos:Float) : IUpdateable, IDrawable
{
    val size  = 0.05f
    val maxSpd = 0.05f

    // TODO: 4real
    override fun update(interval:Int) {
        if(InputManager.input.contains(KeyCode.LEFT) || InputManager.input.contains(KeyCode.A))
            xPos -= maxSpd * interval
        if(InputManager.input.contains(KeyCode.RIGHT) || InputManager.input.contains(KeyCode.D))
            xPos += maxSpd * interval
    }

    override fun draw() {
        throw UnsupportedOperationException()
    }
}
