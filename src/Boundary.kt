/**
 * Created by nilot on 16/03/2016.
 */
class Boundary(val x: Double, val y: Double, val width: Double, val height: Double) {
    fun intersectsWithSide(other: Boundary): Boolean {
        return (this.y > other.y && this.y + this.height < other.y + other.height) &&
                ((this.x + this.width > other.x && this.x + this.width < other.x + other.width) ||
                        (this.x < other.x + other.width && this.x > other.x))
    }

    fun intersectsWithTopOrBottom(other: Boundary): Boolean {
        return (this.x > other.x && this.x + this.width < other.x + other.width) &&
                ((this.y > other.y && this.y < this.y) ||
                        (this.y + this.height > other.y && this.y + this.height < other.y + other.height))
    }

    fun intersectsWithCorner(other: Boundary): Boolean {
        return false //TODO
    }

    fun intersectsWith(other: Boundary): Boolean {
        return intersectsWithSide(other) || intersectsWithTopOrBottom(other) || intersectsWithCorner(other)
    }
}