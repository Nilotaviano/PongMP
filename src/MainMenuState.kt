import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.stage.Stage
import java.net.Inet4Address
import java.net.InetSocketAddress
import java.net.UnknownHostException

@Suppress("NAME_SHADOWING")
class MainMenuState : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = "PongMP"
        primaryStage.isResizable = false

        val grid = GridPane()
        grid.alignment = Pos.CENTER
        grid.hgap = 10.0
        grid.vgap = 10.0
        grid.padding = Insets(25.0, 25.0, 25.0, 25.0)

        val scene = Scene(grid, Constants.windowWidth, Constants.windowHeight)
        primaryStage.scene = scene
        primaryStage.sizeToScene()

        val sceneTitle = Text("Welcome")
        sceneTitle.font = Font.font("Tahoma", FontWeight.NORMAL, 20.0)
        grid.add(sceneTitle, 0, 0, 2, 1)

        val ipAddress = Label("IP Address:")
        grid.add(ipAddress, 0, 1)

        val ipAddressField = TextField()
        grid.add(ipAddressField, 1, 1)

        val port = Label("Port:")
        grid.add(port, 0, 2)

        val portField = TextField()
        grid.add(portField, 1, 2)

        val connectButton = Button("Connect")
        val hostButton = Button("Host")
        val hbBtn = HBox(10.0)
        hbBtn.alignment = Pos.BOTTOM_RIGHT
        hbBtn.children.add(connectButton)
        hbBtn.children.add(hostButton)
        grid.add(hbBtn, 1, 4)

        val actionTarget = Text()
        grid.add(actionTarget, 1, 6)

        connectButton.onAction = EventHandler<ActionEvent> {
            try {
                val port: Int = portField.text.toInt()
                val ip = ipAddressField.text
                val address = Inet4Address.getByName(ip)

                ClientPlayState(InetSocketAddress(address, port)).start(primaryStage)
            } catch(e: NumberFormatException) {
                actionTarget.fill = Color.FIREBRICK
                actionTarget.text = "Port must be numeric"
            } catch(e: UnknownHostException) {
                actionTarget.fill = Color.FIREBRICK
                actionTarget.text = "Can't find host"
            } catch(e: Exception) {
                actionTarget.fill = Color.FIREBRICK
                actionTarget.text = "Unknown error: ${e.toString()}"
            }
        }

        hostButton.onAction = EventHandler<ActionEvent> {
            try {
                val port: Int = portField.text.toInt()
                val ip = ipAddressField.text
                val address = Inet4Address.getByName(ip)

                HostPlayState(InetSocketAddress(address, port)).start(primaryStage)
            } catch(e: NumberFormatException) {
                actionTarget.fill = Color.FIREBRICK
                actionTarget.text = "Port must be numeric"
            } catch(e: UnknownHostException) {
                actionTarget.fill = Color.FIREBRICK
                actionTarget.text = "Can't find host"
            } catch(e: Exception) {
                actionTarget.fill = Color.FIREBRICK
                actionTarget.text = "Unknown error: ${e.toString()}"
            }
        }

        primaryStage.show()
    }
}
