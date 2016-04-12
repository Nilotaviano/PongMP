package pong

import javafx.stage.Stage
import java.rmi.registry.LocateRegistry

class HostPlayState(port: Int) : PlayState() {

    init {
        var registry = LocateRegistry.createRegistry(port)
        val hostPaddleBounds = Boundary(Constants.windowWidth / 2, Constants.windowHeight * 0.95, Constants.windowWidth / 10, Constants.windowHeight / 50)
        val clientPaddleBounds = Boundary(Constants.windowWidth / 2, Constants.windowHeight * 0.05, Constants.windowWidth / 10, Constants.windowHeight / 50)
        val ballBounds = Boundary(50.0, 50.0, Constants.windowWidth / 100, Constants.windowWidth / 100)

        val connectionString = "${Constants.rmiServer}/"
        registry.bind(connectionString + Constants.hostPaddleBounds, hostPaddleBounds)
        registry.bind(connectionString + Constants.clientPaddleBounds, clientPaddleBounds)
        registry.bind(connectionString + Constants.ballBounds, ballBounds)

        myPaddle = Paddle(hostPaddleBounds)
        opponentPaddle = Paddle(clientPaddleBounds)
        ball = Ball(ballBounds, 25.0, 25.0)
    }

    override fun update(nanoTimeElapsed: Double) {
        myPaddle.update(nanoTimeElapsed)
        ball.update(nanoTimeElapsed)

        // TODO: Proper collision treatment
        if (ball.collidesWith(myPaddle) || ball.collidesWith(opponentPaddle))
            ball.ySpd = -ball.ySpd
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "PongMP - Host"

        super.start(primaryStage)
    }
}