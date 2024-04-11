package com.headfirst.rmi;

import java.rmi.*;

public interface MyRemote extends Remote{
    public String sayHello() throws RemoteException;
}