import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.*
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent
import javafx.stage.*

class Main : Application() {

    override fun start(theStage: Stage) {
        theStage.title = "Canvas Example"

        val root = Group()
        val theScene = Scene(root)
        theStage.scene = theScene

        val canvas = Canvas(400.0, 200.0)
        root.children.add(canvas);

        theStage.show()
        InputManager.initialize(theStage.scene)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}