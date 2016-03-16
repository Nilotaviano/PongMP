import javafx.stage.Stage
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.net.Socket

/**
 * Created by nilot on 16/03/2016.
 */
class ClientPlayState(val socketAddress: InetSocketAddress) : PlayState() {
    lateinit var requestSocket: Socket
    lateinit var output: ObjectOutputStream
    lateinit var input: ObjectInputStream
    var message: String = ""

    override fun start(primaryStage: Stage) {
        try {
            requestSocket = Socket(socketAddress.address, socketAddress.port)

            output = ObjectOutputStream(requestSocket!!.outputStream)
            output.flush()
            input = ObjectInputStream(requestSocket!!.inputStream)

            ConnectionLoop()

        } finally {
            try {
                input.close()
                output.close()
                requestSocket.close()
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
                println("server>" + message)
                sendMessage("Hi my server")
                message = "bye"
                sendMessage(message)
            } catch (classNot: ClassNotFoundException) {
                System.err.println("data received in unknown format")
            }

        } while (message != "bye")
    }

    internal fun sendMessage(msg: String) {
        try {
            output.writeObject(msg)
            output.flush()
            println("client>" + msg)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }

}