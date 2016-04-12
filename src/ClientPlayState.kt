import javafx.stage.Stage
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*
import kotlin.concurrent.schedule

class ClientPlayState(val socketAddress: InetSocketAddress) : PlayState() {
    lateinit var requestSocket: Socket
    lateinit var output: ObjectOutputStream
    lateinit var input: ObjectInputStream

    init {
        myPaddle = Paddle(Constants.windowWidth / 2, Constants.windowHeight * 0.05)
        opponentPaddle = Paddle(Constants.windowWidth / 2, Constants.windowHeight * 0.95)

            requestSocket = Socket(socketAddress.address, socketAddress.port)

        output = ObjectOutputStream(requestSocket.outputStream)
            output.flush()
        input = ObjectInputStream(requestSocket.inputStream)

        DoHandShake()

    }

    override fun start(primaryStage: Stage) {
        Timer("schedule", true).schedule(1000, 5) {
            try {
                buildAndSendMessage()
            } catch(e: Exception) {
                System.err.println("Exception: ${e.toString()}")
            }
        }

        Timer("schedule", true).schedule(1000, 5) {
            try {
                receiveMessage()
            } catch(e: Exception) {
                System.err.println("Exception: ${e.toString()}")
            }
        }
        primaryStage.title = "PongMP - Client"

        super.start(primaryStage)
    }

    private fun DoHandShake() {
        var message: String = ""

        do {
            try {
                message = input.readObject() as String
                println("server>" + message)
                sendString("Hi my server")
                message = "bye"
                sendString(message)
            } catch (classNot: ClassNotFoundException) {
                System.err.println("data received in unknown format")
            }

        } while (message != "bye")
    }

    private fun sendString(msg: String) {
        try {
            output.writeObject(msg)
            output.flush()
            println("client>" + msg)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }

    private fun buildAndSendMessage() {
        var message = Message(opponentPaddle.bounds, myPaddle.bounds, ball.bounds, ball.xSpd, ball.ySpd)
        try {
            output.writeObject(message)
            output.flush()
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    private fun receiveMessage() {
        var message: Message

        try {
            message = input.readObject() as Message

            updateOpponentPaddleBounds(message.hostPaddleBoundary)
            updateBallBoundaryAndSpeed(message.ballBoundary, message.ballXSpeed, message.ballYSpeed)
        } catch (classNot: ClassNotFoundException) {
            System.err.println("Data received in unknown format")
        }
    }

    fun updateOpponentPaddleBounds(bounds: Boundary) {
        opponentPaddle.x = bounds.x
    }

    fun updateBallBoundaryAndSpeed(bounds: Boundary, xSpd: Double, ySpd: Double) {
        ball.x = bounds.x
        ball.y = bounds.y
        ball.xSpd = xSpd
        ball.ySpd = ySpd
    }
}