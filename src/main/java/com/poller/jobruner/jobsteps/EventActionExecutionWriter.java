package com.poller.jobruner.jobsteps;

import com.poller.jobruner.entity.EventActionExecution;
import com.poller.jobruner.repository.EventActionExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventActionExecutionWriter implements ItemWriter<List<EventActionExecution>> {

    @Autowired
    EventActionExecutionRepository repository; // your repository to access the database


    @Override
    public void write(List<? extends List<EventActionExecution>> items) {
        // Implement logic to save the updated ExecutionStatus records
        for (List<EventActionExecution> statuses : items) {
            repository.saveAll(statuses);
        }
    }
}
