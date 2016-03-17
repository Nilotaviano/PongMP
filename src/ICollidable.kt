interface ICollidable {
    val bounds: Boundary
    fun collidesWith(other: ICollidable): Boolean
}