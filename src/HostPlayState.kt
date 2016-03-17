import javafx.stage.Stage
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by nilot on 16/03/2016.
 */
class HostPlayState(val socketAddress: InetSocketAddress) : PlayState() {
    lateinit var serverSocket: ServerSocket
    lateinit var connection: Socket
    lateinit var output: ObjectOutputStream
    lateinit var input: ObjectInputStream

    init {
        myPaddle = Paddle(Constants.windowWidth / 2, Constants.windowHeight * 0.95)
        opponentPaddle = Paddle(Constants.windowWidth / 2, Constants.windowHeight * 0.05)

            serverSocket = ServerSocket(socketAddress.port)

            connection = serverSocket.accept()
        output = ObjectOutputStream(connection.outputStream)
        input = ObjectInputStream(connection.inputStream)
        sendString("Connection successful")

        DoHandShake()
    }

    override fun start(primaryStage: Stage) {
        Timer("schedule", true).schedule(1000, 10) {
            try {
                buildAndSendMessage()
            } catch(e: Exception) {
                System.err.println("Exception: ${e.toString()}")
            }
        }

        Timer("schedule", true).schedule(1000, 10) {
            try {
                receiveMessage()
            } catch(e: Exception) {
                System.err.println("Exception: ${e.toString()}")
            }
        }
        primaryStage.title = "PongMP - Host"

        super.start(primaryStage)
    }

    private fun DoHandShake() {
        var message: String = ""

        do {
            try {
                message = input.readObject() as String
                println("client>" + message)
            } catch (classNot: ClassNotFoundException) {
                System.err.println("Data received in unknown format")
            }

        } while (message != "bye")
    }

    private fun sendString(msg: String) {
        try {
            output.writeObject(msg)
            output.flush()
            println("server>" + msg)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    private fun buildAndSendMessage() {
        var message = Message(myPaddle.bounds, opponentPaddle.bounds, ball.bounds, ball.xSpd, ball.ySpd)
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

            updateOpponentPaddleBounds(message.clientPaddleBoundary)
        } catch (classNot: ClassNotFoundException) {
            System.err.println("Data received in unknown format")
        }
    }

    fun updateOpponentPaddleBounds(bounds: Boundary) {
        opponentPaddle.x = bounds.x
    }
}