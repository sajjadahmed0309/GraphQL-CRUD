package com.example.employeemanagementgraphql;

import com.example.employeemanagementgraphql.entities.Attendence;
import com.example.employeemanagementgraphql.entities.Employee;
import com.example.employeemanagementgraphql.entities.Subjects;
import com.example.employeemanagementgraphql.repositories.AttendenceRepository;
import com.example.employeemanagementgraphql.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EmployeeManagementGraphQlApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendenceRepository attendenceRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementGraphQlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createEmployeesAndAttendences(10, 5);
    }

    private void createEmployeesAndAttendences(int numberOfEmployees, int days) {
        Set<Employee> employees = createEmployees(numberOfEmployees);
        addAttendances(employees, days);
    }

    private Set<Employee> createEmployees(int numberOfEmployees) {
        Set<Employee> employees = new HashSet<>();
        SecureRandom random = new SecureRandom();
        for (int i = 1; i <= numberOfEmployees; i++) {
            int age = 20 + random.nextInt(40);
            String grade = "Grade" + (1 + random.nextInt(5));
            String name = "Employee" + i;
            Subjects subject = Subjects.values()[random.nextInt(Subjects.values().length)];
            Employee employee = new Employee(age, new HashSet<>(), grade, i, name, subject);
            employees.add(employee);
            employeeRepository.save(employee);
        }
        return employees;
    }

    private void addAttendances(Set<Employee> employees, int days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        SecureRandom random = new SecureRandom();
        int attendanceId = 1;
        for (Employee employee : employees) {
            Set<Attendence> attendences = new HashSet<>();
            for (int day = 0; day < days; day++) {
                LocalDateTime date = LocalDateTime.now().minusDays(day);
                boolean present = random.nextBoolean();
                Attendence attendence = new Attendence(date.format(formatter), employee, attendanceId++, present);
                attendences.add(attendence);
                attendenceRepository.save(attendence);
            }
            employee.setAttendences(attendences);
            employeeRepository.save(employee);
        }
    }
}

