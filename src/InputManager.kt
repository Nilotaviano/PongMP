import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import java.util.*

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
        theScene.addEventFilter(KeyEvent.KEY_PRESSED, { event -> _input.add(event.code) })

        theScene.addEventFilter(KeyEvent.KEY_RELEASED, { event -> _input.remove(event.code) })
    }
}