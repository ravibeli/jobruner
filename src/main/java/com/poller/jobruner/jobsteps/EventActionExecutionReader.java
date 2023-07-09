package com.poller.jobruner.jobsteps;

import com.poller.jobruner.entity.EventActionExecution;
import com.poller.jobruner.repository.EventActionExecutionRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventActionExecutionReader implements ItemReader<List<EventActionExecution>> {
    @Autowired
    EventActionExecutionRepository repository;

    @Override
    public List<EventActionExecution> read() throws Exception {
        // Implement the logic to fetch 5 records at a time from the table
        // You can use pagination or any other mechanism to achieve this
        // Return null when no more records are available
        Thread.sleep(3000);
        List<EventActionExecution> executionList = repository.findByStatus(Status.IN_PROGRESS.name());
        return CollectionUtils.isEmpty(executionList) ? new ArrayList<>() : executionList;
    }
}
