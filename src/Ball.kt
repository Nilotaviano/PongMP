import javafx.scene.canvas.GraphicsContext

class Ball(var x: Double, var y: Double, var xSpd: Double, var ySpd: Double) : IUpdateable, IDrawable, ICollidable
{
    val width = Constants.windowWidth / 100
    val height = Constants.windowHeight / 100

    override val bounds: Boundary
        get() = Boundary(x, y, width, height)

    override fun update(interval: Double) {
        val xMovement = xSpd * interval
        val yMovement = ySpd * interval

        incrementXPos(xMovement)
        incrementYPos(yMovement)
    }

    private fun incrementYPos(yMovement: Double) {

        if (y + yMovement >= Constants.windowHeight)
        {
            ySpd = -ySpd
            y = 2 * Constants.windowHeight - (y + yMovement)
        } else if (y - yMovement - height <= 0)
        {
            ySpd = -ySpd
            y = -yMovement - y
        }
        else
            y += yMovement
    }

    private fun incrementXPos(xMovement: Double) {

        if (x + xMovement >= Constants.windowWidth)
        {
            xSpd = -xSpd
            x = 2 * Constants.windowWidth - (x + xMovement)
        } else if (x - xMovement - width <= 0)
        {
            xSpd = -xSpd
            x = -xMovement - x
        }
        else
            x += xMovement
    }

    override fun draw(gc: GraphicsContext) {
        gc.fillRoundRect(x, y, width, height, 50.0, 25.0)
    }

    override fun collidesWith(other: ICollidable): Boolean = bounds.intersectsWith(other.bounds)
}
