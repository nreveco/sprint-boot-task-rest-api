package com.nreveco.sprintboot.repository;

import com.nreveco.sprintboot.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    /**
     * Todo crud tasks database methods.
     */
}
