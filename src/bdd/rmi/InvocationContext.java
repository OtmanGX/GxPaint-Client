package bdd.rmi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 * @author Alain Defrance
 */
public class InvocationContext implements Serializable {
    private Object[] args;
    private String method;
    private String name;

    public InvocationContext() {
    }

    public InvocationContext(String name, Object[] args, String method) {
        this.name = name;
        this.args = args;
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}