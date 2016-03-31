package pong

import javafx.scene.canvas.GraphicsContext
import pong.rmi.IRemoteBoundary

class Ball(override var bounds: IRemoteBoundary, var xSpd: Double, var ySpd: Double) : IUpdateable, IDrawable, ICollidable
{
    override fun update(interval: Double) {
        val xMovement = xSpd * interval
        val yMovement = ySpd * interval

        incrementXPos(xMovement)
        incrementYPos(yMovement)
    }

    private fun incrementYPos(yMovement: Double) {

        if (bounds.y + yMovement >= Constants.windowHeight)
        {
            ySpd = -ySpd
            bounds.y = 2 * Constants.windowHeight - (bounds.y + yMovement)
        } else if (bounds.y - yMovement - bounds.height <= 0)
        {
            ySpd = -ySpd
            bounds.y = -yMovement - bounds.y
        }
        else
            bounds.y += yMovement
    }

    private fun incrementXPos(xMovement: Double) {

        if (bounds.x + xMovement >= Constants.windowWidth)
        {
            xSpd = -xSpd
            bounds.x = 2 * Constants.windowWidth - (bounds.x + xMovement)
        } else if (bounds.x - xMovement - bounds.width <= 0)
        {
            xSpd = -xSpd
            bounds.x = -xMovement - bounds.x
        }
        else
            bounds.x += xMovement
    }

    override fun draw(gc: GraphicsContext) {
        gc.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 50.0, 25.0)
    }

    override fun collidesWith(other: ICollidable): Boolean = bounds.intersectsWith(other.bounds)
}
