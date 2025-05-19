package com.FleetX.model;


public class UserModel {
    
	private int id;
    private String fname;
    private String lname;
    private String uName;
    private String dob; 
    private String email;
    private String phone;
    private String password;
    private String role;


    public UserModel() {
		// TODO Auto-generated constructor stub
	}
    public UserModel(String uName, String password) {
        this.uName = uName;
        this.password = password;
    }
    
	public UserModel(String fname, String lname, String uName, String email, String phone) {
		this.fname = fname;
		this.lname = lname;
		this.uName = uName;
		this.email = email;
		this.phone = phone;
	}

    public UserModel(String fname, String lname, String uName, String dob, String email, String phone,
                     String password, String role) {
        this.fname = fname;
        this.lname = lname;
        this.uName = uName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public UserModel(int id, String fname, String lname, String uName, String dob, String email, String phone,
                     String password, String role) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.uName = uName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getDob() {  
        return dob;
    }

    public void setDob(String dob) {  
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
