package com.nreveco.sprintboot.repository;

import com.nreveco.sprintboot.model.Tasks;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    /**
     * Todo crud tasks database methods.
     */

    Optional<Tasks> findByDescription(String description);
}
