package com.freeForm.worker.repo;

import com.freeForm.worker.dao.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
