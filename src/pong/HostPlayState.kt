package pong

import javafx.stage.Stage
import java.rmi.Naming

class HostPlayState() : PlayState() {

    init {
        val hostPaddleBounds = Boundary(Constants.windowWidth / 2, Constants.windowHeight * 0.95, Constants.windowWidth / 10, Constants.windowHeight / 100)
        val clientPaddleBounds = Boundary(Constants.windowWidth / 2, Constants.windowHeight * 0.05, Constants.windowWidth / 10, Constants.windowHeight / 100)
        val ballBounds = Boundary(50.0, 50.0, Constants.windowWidth / 100, Constants.windowWidth / 100)

        val connectionString = "RMIServer/"

        Naming.rebind(connectionString + "hostPaddleBounds", hostPaddleBounds)
        Naming.rebind(connectionString + "clientPaddleBounds", clientPaddleBounds)
        Naming.rebind(connectionString + "ballBounds", ballBounds)

        myPaddle = Paddle(hostPaddleBounds)
        opponentPaddle = Paddle(clientPaddleBounds)
        ball = Ball(ballBounds, 25.0, 25.0)
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "PongMP - Host"

        super.start(primaryStage)
    }

}