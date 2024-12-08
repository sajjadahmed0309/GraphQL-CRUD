package com.example.employeemanagementgraphql.repositories;

import com.example.employeemanagementgraphql.entities.Attendence;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AttendenceRepository extends ListCrudRepository<Attendence, Integer> {

    Window<Attendence> findByEmployeeId(Integer id, ScrollPosition position, Limit limit, Sort sort);

    Optional<Attendence> findByEmployeeIdAndDate(Integer employeeId, String date);
}

