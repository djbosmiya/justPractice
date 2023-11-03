package com.example.hrminiapp;

public class Employee {
    private int id, salary;
    private String name, desig, dept, emailId;

    public int getId() { return id; }

    public int getSalary() { return salary; }

    public String getName() { return name; }

    public String getDesig() { return desig; }

    public String getDept() { return dept; }

    public String getEmailId() { return emailId; }

    public void setId(int id) { this.id = id; }

    public void setSalary(int salary) { this.salary = salary; }

    public void setName(String name) { this.name = name; }

    public void setDesig(String desig) { this.desig = desig; }

    public void setDept(String dept) { this.dept = dept; }

    public void setEmailId(String emailId) { this.emailId = emailId; }
}
