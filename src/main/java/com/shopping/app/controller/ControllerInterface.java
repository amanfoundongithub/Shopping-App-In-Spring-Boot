package com.shopping.app.controller;

import com.shopping.app.exception.CreateException;
import com.shopping.app.exception.DeleteException;
import com.shopping.app.exception.ResetException;
import com.shopping.app.exception.SearchException;
import com.shopping.app.exception.UpdateException;

import java.util.List;

/**
 * Interface for controller 
 * 
 * @author amanfoundongithub
 */
public interface ControllerInterface<T> {

    /**
     * Resets entire repository
     * 
     */
    public void reset() throws ResetException;  
    
    /**
     * Creates and stores an object in database 
     * 
     * @param object: the object to be stored in database
     * @return ID of the created object
     * @throws CreateException
     * If error occurs during object creation
     */
    public String create(T object) throws CreateException;

    /**
     * Updates an object in database
     * 
     * @param object: the object with fields to be updated
     * @return the status of update
     * @throws UpdateException 
     * If error occurs during object updation
     */
    public boolean update(T object) throws UpdateException;

    /**
     * Deletes an object in database
     * 
     * @param object: the object with fields to be deleted
     * @return the status of deletion
     * @throws DeleteException
     * If error occurs during deletion
     */
    public boolean delete(T object) throws DeleteException;

    /**
     * Search by Mongo ID of the object 
     * @param id
     * @return Object
     * @throws SearchException
     */
    public T searchByID(String id) throws SearchException;

    /**
     * Fetches all the objects from database 
     * 
     * @return List of all objects 
     * @throws SearchException
     */
    public List<T> getAllObjects() throws SearchException;  



}
