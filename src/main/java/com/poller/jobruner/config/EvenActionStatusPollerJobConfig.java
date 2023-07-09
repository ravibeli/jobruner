package com.poller.jobruner.config;

import com.poller.jobruner.entity.EventActionExecution;
import com.poller.jobruner.jobsteps.EventActionExecutionProcessor;
import com.poller.jobruner.jobsteps.EventActionExecutionReader;
import com.poller.jobruner.jobsteps.EventActionExecutionWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing(modular = true)
@ConditionalOnProperty(name = "sca.event.job.enabled", havingValue = "true")
public class EvenActionStatusPollerJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    EventActionExecutionReader reader;

    @Autowired
    EventActionExecutionProcessor processor;

    @Autowired
    EventActionExecutionWriter writer;

    @Bean
    public Step processEventActionExecutionStep() {
        return stepBuilderFactory.get("processEventActionExecutionStep")
                .<List<EventActionExecution>, List<EventActionExecution>>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job eventActionExecutionJob() {
        return jobBuilderFactory.get("eventActionExecutionJob")
                .start(processEventActionExecutionStep())
                .build();
    }
}

