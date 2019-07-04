package bdd.rmi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author otmangx
 */
public class RMIInvocationException extends RuntimeException {
    public RMIInvocationException() {
        super();
    }

    public RMIInvocationException(String message) {
        super(message);
    }

    public RMIInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}