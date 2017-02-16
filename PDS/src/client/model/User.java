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
    
    public User() {
    }
    
    public User(int id, String pswd, String ln, String fn, String mail, Boolean manager ){
        this.id_employee = id;
        this.password = pswd;
        this.last_name = ln;
        this.first_name = fn;
        this.email = mail;
        this.is_manager = manager;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(Boolean is_manager) {
        this.is_manager = is_manager;
    }
    
}
