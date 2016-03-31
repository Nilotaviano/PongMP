package pong

import javafx.scene.canvas.GraphicsContext

interface IDrawable {
    fun draw(gc: GraphicsContext)
}