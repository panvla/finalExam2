package com.vladimirpandurov.finalExam2B.repository;

import com.vladimirpandurov.finalExam2B.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE "
            + "(:taskName IS NULL OR t.name like %:taskName%) AND "
            + "(:sprintId IS NULL OR t.sprint.id = :sprintId)"
    )
    Page<Task> search(@Param("taskName") String taskName,
                      @Param("sprintId") Long sprintId, Pageable pageRequest);



}
