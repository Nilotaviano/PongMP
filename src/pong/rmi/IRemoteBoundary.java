package pong.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteBoundary extends Remote {
    double getX() throws RemoteException;

    void setX(double value) throws RemoteException;

    double getY() throws RemoteException;

    void setY(double value) throws RemoteException;

    double getWidth() throws RemoteException;

    void setWidth(double value) throws RemoteException;

    double getHeight() throws RemoteException;

    void setHeight(double value) throws RemoteException;

    boolean intersectsWithSide(IRemoteBoundary other) throws RemoteException;

    boolean intersectsWithTopOrBottom(IRemoteBoundary other) throws RemoteException;

    boolean intersectsWithCorner(IRemoteBoundary other) throws RemoteException;

    boolean intersectsWith(IRemoteBoundary other) throws RemoteException;
}
