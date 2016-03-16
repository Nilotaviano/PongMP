import java.io.Serializable

class Message(val hostPaddleBoundary: Boundary, val clietPaddleBoundary: Boundary,
              val ballBoundary: Boundary, val ballXSpeed: Double, val ballYSpeed: Double) : Serializable {}
