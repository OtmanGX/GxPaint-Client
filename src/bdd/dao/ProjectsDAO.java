/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd.dao;

import bdd.entities.Project;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author otmangx
 */
public interface ProjectsDAO {
    public void create(Project pr) throws SQLException;
    public List<Project> getAllProjects() throws SQLException;
    public List<Project> getAllProjectsByUserId() throws SQLException;
    public Project getProjectByKey(String key) throws SQLException;
    public void update(Project pr) throws SQLException;
    public void delete(Integer id) throws SQLException;
    public Vector getData(Integer id) throws SQLException, IOException, ClassNotFoundException;
    public void setData(Project pr) throws IOException, SQLException;
    public String getKey(Project pr) throws SQLException, NoSuchAlgorithmException;
}
