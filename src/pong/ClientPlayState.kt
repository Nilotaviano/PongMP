package pong

import javafx.stage.Stage
import pong.rmi.IRemoteBoundary
import java.net.InetSocketAddress
import java.rmi.Naming

class ClientPlayState(val socketAddress: InetSocketAddress) : PlayState() {

    init {
        val connectionString = "rmi://${socketAddress.hostName}/RMIServer/"

        val hostPaddleBounds = Naming.lookup(connectionString + "hostPaddleBounds") as IRemoteBoundary
        val clientPaddleBounds = Naming.lookup(connectionString + "clientPaddleBounds") as IRemoteBoundary
        val ballBounds = Naming.lookup(connectionString + "ballBounds") as IRemoteBoundary

        myPaddle = Paddle(hostPaddleBounds)
        opponentPaddle = Paddle(clientPaddleBounds)
        ball = Ball(ballBounds, 25.0, 25.0)
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "PongMP - Client"

        super.start(primaryStage)
    }


}