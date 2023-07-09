package com.poller.jobruner.controller;

import com.poller.jobruner.entity.EventActionExecution;
import com.poller.jobruner.repository.EventActionExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/event-action-executions")
public class EventActionExecutionController {

    private final EventActionExecutionRepository repository;

    @Autowired
    public EventActionExecutionController(EventActionExecutionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<EventActionExecution> getAllEventActionExecutions() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventActionExecution> getEventActionExecutionById(@PathVariable String id) {
        Optional<EventActionExecution> execution = repository.findById(id);
        return execution.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventActionExecution> createEventActionExecution(@RequestBody EventActionExecution execution) {
        EventActionExecution savedExecution = repository.save(execution);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExecution);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventActionExecution> updateEventActionExecution(
            @PathVariable String id,
            @RequestBody EventActionExecution execution
    ) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        execution.setId(id);
        EventActionExecution updatedExecution = repository.save(execution);
        return ResponseEntity.ok(updatedExecution);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventActionExecution(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
