/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

/**
 *
 * @author nassimhammadi
 */
public class User {
    
    private int id_employee;
    private String password;
    private String last_name;
    private String first_name;
    private String email;
    private Boolean is_manager;
    
    /**
     * Blank Constructor
     */
    public User() {
    }
    
    /**
     * 
     * @param id
     * @param pswd
     * @param ln
     * @param fn
     * @param mail
     * @param manager
     * Constructor
     */
    public User(int id, String pswd, String ln, String fn, String mail, Boolean manager ){
        this.id_employee = id;
        this.password = pswd;
        this.last_name = ln;
        this.first_name = fn;
        this.email = mail;
        this.is_manager = manager;
    }

    /**
     * 
     * @return
     * Return id_employee
     */
    public int getId_employee() {
        return id_employee;
    }

    /**
     * @param id_employee
     * Change id_employee
     */
    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    /**
     * 
     * @return
     * Return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     * Change password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return
     * return last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * 
     * @param last_name
     * Change last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * 
     * @return
     * Return first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * 
     * @param first_name
     * Change first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * 
     * @return
     * Return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     * Change email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     * Return the statues of the user
     */
    public Boolean getIs_manager() {
        return is_manager;
    }

    /**
     * 
     * @param is_manager
     * Change the statues of the user
     */
    public void setIs_manager(Boolean is_manager) {
        this.is_manager = is_manager;
    }
    
    /**
     * 
     * @return the right format for a label
     */
    public String toStringLabel(){
    	return id_employee+". "+last_name+" "+first_name;
    	
    }
    
}
