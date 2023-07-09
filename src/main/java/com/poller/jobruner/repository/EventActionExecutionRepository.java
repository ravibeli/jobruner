package com.poller.jobruner.repository;

import com.poller.jobruner.entity.EventActionExecution;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventActionExecutionRepository extends PagingAndSortingRepository<EventActionExecution, String> {
    List<EventActionExecution> findByStatus(String status);
}

