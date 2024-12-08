package com.example.employeemanagementgraphql.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Set;

@Entity
public class Employee {

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String grade;
    @Enumerated(EnumType.STRING)
    private Subjects subjects;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private Set<Attendence> attendences;

    public Employee() {}

    public Employee(Integer age, Set<Attendence> attendences, String grade, Integer id, String name, Subjects subjects) {
        this.age = age;
        this.attendences = attendences;
        this.grade = grade;
        this.id = id;
        this.name = name;
        this.subjects = subjects;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Attendence> getAttendences() {
        return attendences;
    }

    public void setAttendences(Set<Attendence> attendences) {
        this.attendences = attendences;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", subjects=" + subjects +
                ", attendences=" + attendences +
                '}';
    }
}
