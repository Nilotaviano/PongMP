import javafx.event.EventHandler
import java.util.*
import javafx.scene.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

/**
 * Created by nilot on 14/03/2016.
 */
object InputManager {
    private var _input: HashSet<KeyCode> = HashSet()

    var input:HashSet<KeyCode>
        get() = _input
        private set(value) {
            _input = value
        }

    fun initialize(theScene:Scene) {
        theScene.addEventFilter(KeyEvent.KEY_PRESSED, {
            event ->
                print("pressed")
                val code = event.code;
                _input.add(code);
        })

        theScene.addEventFilter(KeyEvent.KEY_RELEASED, {
            event ->
                print("released")
                val code = event.code;
                _input.remove(code);
        })
    }
}