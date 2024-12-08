package com.example.employeemanagementgraphql.controllers;

import com.example.employeemanagementgraphql.entities.Attendence;
import com.example.employeemanagementgraphql.entities.Employee;
import com.example.employeemanagementgraphql.entities.Subjects;
import com.example.employeemanagementgraphql.repositories.AttendenceRepository;
import com.example.employeemanagementgraphql.repositories.EmployeeRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final AttendenceRepository attendenceRepository;

    public EmployeeController(EmployeeRepository employeeRepository, AttendenceRepository attendenceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendenceRepository = attendenceRepository;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<Employee> employees() {
        return employeeRepository.findAll();
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    Optional<Employee> employee(@Argument Integer id) {
        return employeeRepository.findById(id);
    }

    @SchemaMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    Window<Attendence> attendences(Employee employee, ScrollSubrange subrange) {
        ScrollPosition scrollPosition = subrange.position().orElse(ScrollPosition.offset());
        Limit limit = Limit.of(subrange.count().orElse(10));
        Sort sort = Sort.by("date").ascending();
        return attendenceRepository.findByEmployeeId(employee.getId(), scrollPosition, limit, sort);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Attendence updateAttendence(@Argument Integer employeeId, @Argument String date, @Argument Boolean present) {
        Optional<Attendence> optionalAttendence = attendenceRepository.findByEmployeeIdAndDate(employeeId, date);
        if (optionalAttendence.isPresent()) {
            Attendence attendence = optionalAttendence.get();
            if (present != null) {
                attendence.setPresent(present);
            }
            return attendenceRepository.save(attendence);
        }
        return null;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Employee createEmployee(@Argument Integer id, @Argument String name, @Argument Integer age, @Argument String grade, @Argument Subjects subjects) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setGrade(grade);
        employee.setSubjects(subjects);
        employeeRepository.save(employee);
        return employeeRepository.save(employee);
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Employee updateEmployee(@Argument Integer id, @Argument String name, @Argument Integer age, @Argument String grade, @Argument Subjects subjects) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(name);
            employee.setAge(age);
            employee.setGrade(grade);
            employee.setSubjects(subjects);

            return employeeRepository.save(employee);
        }
        return null;
    }

}



