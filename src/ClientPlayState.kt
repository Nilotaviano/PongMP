import javafx.stage.Stage
import java.net.InetSocketAddress

/**
 * Created by nilot on 16/03/2016.
 */
class ClientPlayState(val ipAddress: InetSocketAddress) : PlayState() {
    override fun start(primaryStage: Stage) {
        super.start(primaryStage)
    }
}