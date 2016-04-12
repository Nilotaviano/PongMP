package pong

import javafx.stage.Stage
import pong.rmi.IRemoteBoundary
import java.net.InetSocketAddress
import java.rmi.Naming

class ClientPlayState(val socketAddress: InetSocketAddress) : PlayState() {

    init {
        val connectionString = "rmi://${socketAddress.hostName}:${socketAddress.port}/${Constants.rmiServer}/"

        val hostPaddleBounds = Naming.lookup(connectionString + Constants.hostPaddleBounds) as IRemoteBoundary
        val clientPaddleBounds = Naming.lookup(connectionString + Constants.clientPaddleBounds) as IRemoteBoundary
        val ballBounds = Naming.lookup(connectionString + Constants.ballBounds) as IRemoteBoundary

        myPaddle = Paddle(clientPaddleBounds)
        opponentPaddle = Paddle(hostPaddleBounds)
        ball = Ball(ballBounds, 25.0, 25.0)
    }

    override fun update(nanoTimeElapsed: Double) {
        myPaddle.update(nanoTimeElapsed)
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "PongMP - Client"

        super.start(primaryStage)
    }
}