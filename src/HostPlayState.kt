import javafx.stage.Stage
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket

/**
 * Created by nilot on 16/03/2016.
 */
class HostPlayState(val socketAddress: InetSocketAddress) : PlayState() {
    lateinit var serverSocket: ServerSocket
    lateinit var connection: Socket
    lateinit var output: ObjectOutputStream
    lateinit var input: ObjectInputStream
    var message: String = ""
    override fun start(primaryStage: Stage) {
        try {
            serverSocket = ServerSocket(socketAddress.port)

            connection = serverSocket.accept()
            output = ObjectOutputStream(connection!!.getOutputStream())
            input = ObjectInputStream(connection!!.getInputStream())
            sendMessage("Connection successful")

            ConnectionLoop()
        } finally {
            try {
                input.close()
                output.close()
                serverSocket.close()
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
        }

        super.start(primaryStage)
    }

    private fun ConnectionLoop() {
        do {
            try {
                message = input.readObject() as String
                println("client>" + message)
                if (message == "bye")
                    sendMessage("bye")
            } catch (classnot: ClassNotFoundException) {
                System.err.println("Data received in unknown format")
            }

        } while (message != "bye")
    }

    internal fun sendMessage(msg: String) {
        try {
            output.writeObject(msg)
            output.flush()
            println("server>" + msg)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }

}