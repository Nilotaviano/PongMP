package pong

import javafx.application.Application
import javafx.stage.Stage

class Main : Application() {

    override fun start(theStage: Stage) {
        MainMenuState().start(theStage)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}