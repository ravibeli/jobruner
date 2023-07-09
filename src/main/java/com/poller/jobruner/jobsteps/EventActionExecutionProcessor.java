package com.poller.jobruner.jobsteps;

import com.poller.jobruner.entity.EventActionExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventActionExecutionProcessor implements ItemProcessor<List<EventActionExecution>, List<EventActionExecution>> {

    @Override
    public List<EventActionExecution> process(List<EventActionExecution> items) throws InterruptedException {
        // Implement logic to update the status of each ExecutionStatus record
        for (EventActionExecution status : items) {
            // Update the status as required (e.g., call callback status API)
            Thread.sleep(3000);
            status.setStatus("COMPLETED");
        }
        return items;
    }
}
