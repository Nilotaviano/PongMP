package pong

import pong.rmi.IRemoteBoundary
import java.rmi.server.UnicastRemoteObject

class Boundary(var _x: Double, var _y: Double, var _width: Double, var _height: Double) : UnicastRemoteObject(), IRemoteBoundary {

    override fun intersectsWithSide(other: IRemoteBoundary): Boolean {
        return (this.y > other.y && this.y + this.height < other.y + other.height) &&
                ((this.x + this.width > other.x && this.x + this.width < other.x + other.width) ||
                        (this.x < other.x + other.width && this.x > other.x))
    }

    override fun intersectsWithTopOrBottom(other: IRemoteBoundary): Boolean {
        return (this.x > other.x && this.x + this.width < other.x + other.width) &&
                ((this.y > other.y && this.y < this.y) ||
                        (this.y + this.height > other.y && this.y + this.height < other.y + other.height))
    }

    override fun intersectsWithCorner(other: IRemoteBoundary): Boolean {
        return false //TODO
    }

    override fun intersectsWith(other: IRemoteBoundary): Boolean {
        return intersectsWithSide(other) || intersectsWithTopOrBottom(other) || intersectsWithCorner(other)
    }

    override fun getX(): Double = _x

    override fun getY(): Double = _y

    override fun getWidth(): Double = _width

    override fun getHeight(): Double = _height

    override fun setX(value: Double) {
        _x = value
    }

    override fun setY(value: Double) {
        _y = value
    }

    override fun setWidth(value: Double) {
        _width = value
    }

    override fun setHeight(value: Double) {
        _height = value
    }
}