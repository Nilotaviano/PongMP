import javafx.scene.canvas.GraphicsContext

/**
 * Created by nilot on 14/03/2016.
 */
class Ball(var xPos: Double, var yPos: Double, var xSpd: Double, var ySpd: Double) : IUpdateable, IDrawable
{
    val radius = Constants.myWorldSize / 200

    override fun update(interval: Double) {
        val xMovement = xSpd * interval
        val yMovement = ySpd * interval

        incrementXPos(xMovement)
        incrementYPos(yMovement)
    }

    private fun incrementYPos(yMovement: Double) {

        if(yPos + yMovement >= Constants.myWorldSize)
        {
            ySpd = -ySpd
            yPos = 2 * Constants.myWorldSize - (yPos + yMovement)
        }
        else if(yPos + yMovement <= 0)
        {
            ySpd = -ySpd
            yPos = yMovement - yPos
        }
        else
            yPos += yMovement
    }

    private fun incrementXPos(xMovement: Double) {

        if(xPos + xMovement >= Constants.myWorldSize)
        {
            xSpd = -xSpd
            xPos = 2 * Constants.myWorldSize - (xPos + xMovement)
        }
        else if(xPos + xMovement <= 0)
        {
            xSpd = -xSpd
            xPos = xMovement - xPos
        }
        else
            xPos += xMovement
    }

    override fun draw(gc: GraphicsContext) {
        gc.fillRoundRect(xPos, yPos, radius, radius, 50.0, 25.0)
    }
}
