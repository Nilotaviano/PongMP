package pong

import pong.rmi.IRemoteBoundary

interface ICollidable {
    var bounds: IRemoteBoundary
    fun collidesWith(other: ICollidable): Boolean
}