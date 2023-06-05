package com.freeForm.task.repo;

import com.freeForm.task.dao.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
