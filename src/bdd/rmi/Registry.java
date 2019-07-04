/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd.rmi;

//import sun.plugin.dom.exception.InvalidStateException;

import bdd.rmi.RMIHandler;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;

/**
 * @author Alain Defrance
 */
public class Registry {
    private Socket server;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;


    public Registry connect(String host, int port) throws Exception {
        if (server != null) {
            throw new Exception("Socket is already connected");
        }

        try {
            server = new Socket(host, port);
            ois = new ObjectInputStream(server.getInputStream());
            oos = new ObjectOutputStream(server.getOutputStream());
            
        } catch (Exception e) {
            throw new RMIInvocationException(e.getMessage(), e);
        }

        return this;
    }
    
    public <T> T get(String name, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{clazz},
                new RMIHandler(name, ois, oos)
        );
    }

}