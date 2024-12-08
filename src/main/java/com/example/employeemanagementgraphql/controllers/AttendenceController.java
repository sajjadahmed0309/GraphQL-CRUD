package com.example.employeemanagementgraphql.controllers;

import com.example.employeemanagementgraphql.entities.Attendence;
import com.example.employeemanagementgraphql.entities.Employee;
import com.example.employeemanagementgraphql.repositories.AttendenceRepository;
import com.example.employeemanagementgraphql.repositories.EmployeeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Controller
public class AttendenceController {

    private final EmployeeRepository employeeRepository;
    private final AttendenceRepository attendenceRepository;

    public AttendenceController(EmployeeRepository employeeRepository, AttendenceRepository attendenceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendenceRepository = attendenceRepository;
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<Attendence> attendences() {
        return attendenceRepository.findAll();
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    Optional<Attendence> attendence(@Argument Integer id) {
        return attendenceRepository.findById(id);
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Attendence createAttendence(@Argument Integer employeeId, @Argument String date, @Argument Boolean present)
    {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Attendence attendence = new Attendence();
            attendence.setId(new SecureRandom().nextInt(50,1000));
            attendence.setEmployee(optionalEmployee.get());
            attendence.setDate(date);
            attendence.setPresent(present);
            return attendenceRepository.save(attendence);
        }
        else
            return null;

    }
}
