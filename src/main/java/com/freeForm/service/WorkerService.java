package com.freeForm.service;

import com.freeForm.entity.Worker;
import com.freeForm.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    public Worker createWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, Worker worker) {
        Worker currentWorker = workerRepository.findById(id).orElse(null);
        if (currentWorker == null) {
            throw new RuntimeException("Worker with id " + id + " not found");
        }
        if (worker.getFirstname() != null) {
            currentWorker.setFirstname(worker.getFirstname());
        }
        if (worker.getLastname() != null) {
            currentWorker.setLastname(worker.getLastname());
        }
        if (worker.getAge() != null) {
            currentWorker.setAge(worker.getAge());
        }
        return workerRepository.save(currentWorker);
    }

    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }
}
