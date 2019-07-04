/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otmangx
 */

public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Date date;
    private Vector data;
    private int userId;

    public Project(Integer id) {
        this.id = id;
    }
    
    public Project(String name) {
        this.name = name;
    }
    
    public Project(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vector getData() {
        return this.data;
    }

    public void setData(Vector obj) throws IOException {
        this.data = obj;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    @Override
    public Object clone() {
        Project pr = new Project(this.id);
        pr.setName(this.name);
        pr.setDate(this.date);
        try {
            if (data!=null)
                pr.setData((Vector)this.data.clone());
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
        
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bdd.entities.Projects[ id=" + id + " ]";
    }
    
}
