package com.example.demo2.scheduled;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Component
public class TaskSchedulerExample {

    private final TaskScheduler taskScheduler;

    public TaskSchedulerExample(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

//    @PostConstruct
//    public void init() {
//        taskScheduler.scheduleAtFixedRate(
//                () -> System.out.println("Task executed"),
//                Duration.ofSeconds(5)
//        );
//    }

}