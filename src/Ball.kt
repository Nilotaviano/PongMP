/**
 * Created by nilot on 14/03/2016.
 */
class Ball(var xPos:Float,var yPos:Float,var xSpd:Float,var ySpd:Float) : IUpdateable, IDrawable
{
    val size  = 0.05f

    override fun update(interval:Int) {
        val xMovement = xSpd * interval
        val yMovement = ySpd * interval

        incrementXPos(xMovement)
        incrementYPos(yMovement)
    }

    private fun incrementYPos(yMovement:Float) {

        if(yPos + yMovement > Constants.myWorldSize)
        {
            ySpd = -ySpd
            yPos = 2 * Constants.myWorldSize - (yPos + yMovement)
        }
        else if(yPos + yMovement < -Constants.myWorldSize)
        {
            ySpd = -ySpd
            yPos = 2 * -Constants.myWorldSize + (yPos + yMovement)
        }
        else
            yPos += yMovement
    }

    private fun incrementXPos(xMovement:Float) {

        if(xPos + xMovement > Constants.myWorldSize)
        {
            xSpd = -xSpd
            xPos = 2 * Constants.myWorldSize - (xPos + xMovement)
        }
        else if(yPos + xMovement < -Constants.myWorldSize)
        {
            xSpd = -xSpd
            xPos = 2 * -Constants.myWorldSize + (xPos + xMovement)
        }
        else
            xPos += xMovement
    }

    override fun draw() {
        throw UnsupportedOperationException()
    }
}