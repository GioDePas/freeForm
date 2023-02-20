package com.freeForm.controller;

import com.freeForm.entity.Worker;
import com.freeForm.service.WorkerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
@AllArgsConstructor
public class WorkerController {
    private final WorkerService workerService;

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public Worker getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.createWorker(worker);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public Worker updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        return workerService.updateWorker(id, worker);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
    }
}
