import java.io.Serializable

class Message(val hostPaddleBoundary: Boundary, val clientPaddleBoundary: Boundary,
              val ballBoundary: Boundary, val ballXSpeed: Double, val ballYSpeed: Double) : Serializable {}
