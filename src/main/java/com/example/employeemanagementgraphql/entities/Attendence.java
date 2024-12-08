package com.example.employeemanagementgraphql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Attendence {
    @Id
    private Integer id;
    private Boolean present;
    private String date;

    @ManyToOne
    private Employee employee;

    public Attendence() {
    }

    public Attendence(String date, Employee employee, Integer id, Boolean present) {
        this.date = date;
        this.employee = employee;
        this.id = id;
        this.present = present;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "Attendence{" +
                "date=" + date +
                ", id=" + id +
                ", present=" + present +
                ", employee=" + employee +
                '}';
    }
}
