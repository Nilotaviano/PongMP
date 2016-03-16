/**
 * Created by nilot on 15/03/2016.
 */

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

class MainMenuState : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = "JavaFX Welcome"

        val grid = GridPane()
        grid.alignment = Pos.CENTER
        grid.hgap = 10.0
        grid.vgap = 10.0
        grid.padding = Insets(25.0, 25.0, 25.0, 25.0)

        val scene = Scene(grid, 300.0, 275.0)
        primaryStage.scene = scene

        val scenetitle = Text("Welcome")
        scenetitle.font = Font.font("Tahoma", FontWeight.NORMAL, 20.0)
        grid.add(scenetitle, 0, 0, 2, 1)

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
            actionTarget.fill = Color.FIREBRICK
            actionTarget.text = "Connect button pressed"

            PlayState().start(primaryStage)
        }

        hostButton.onAction = EventHandler<ActionEvent> {
            actionTarget.fill = Color.FIREBRICK
            actionTarget.text = "Host button pressed"

            PlayState().start(primaryStage)
        }

        primaryStage.show()
    }
}
