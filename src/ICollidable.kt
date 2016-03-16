/**
 * Created by nilot on 16/03/2016.
 */
interface ICollidable {
    val bounds: Boundary
    fun collidesWith(other: ICollidable): Boolean
}